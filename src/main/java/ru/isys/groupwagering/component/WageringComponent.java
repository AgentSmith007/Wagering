package ru.isys.groupwagering.component;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import ru.isys.groupwagering.model.dao.TypeOfWageringDAO;
import ru.isys.groupwagering.model.dao.TypicalUserDAO;
import ru.isys.groupwagering.model.dao.UsersBetDAO;
import ru.isys.groupwagering.model.dto.*;
import ru.isys.groupwagering.model.entity.TypeOfWagering;
import ru.isys.groupwagering.model.entity.TypicalUser;
import ru.isys.groupwagering.model.entity.UsersBet;
import ru.isys.groupwagering.model.entity.Wagering;
import ru.isys.groupwagering.model.dao.WageringDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.isys.groupwagering.model.enums.WageringDataType;
import ru.isys.groupwagering.model.enums.WageringStatus;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WageringComponent {
    @Autowired
    private WageringDAO wageringDAO;
    @Autowired
    private TypicalUserDAO typicalUserDAO;
    @Autowired
    private TypeOfWageringDAO typeOfWageringDAO;
    @Autowired
    private UsersBetDAO usersBetDAO;

    private static Logger logger = Logger.getLogger(WageringComponent.class.getName());

    // ЗДЕСЬ ЕСТЬ MAGIC NUMBERS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! (или ошибка)

    /**
     * Take information about wagering and its author. Then create new object of Wagering and try to sent it to the database.
     *
     * @param wageringDTO
     * @return Wagering from the datbase
     */
    @Transactional
    public Wagering insertNewWagering(WageringDTO wageringDTO, String authorLogin, long createTime) {

        TypicalUser author = typicalUserDAO.findByLogin(authorLogin);
        logger.info("author: " + author.toString());
        logger.info("wageringDTO: " + wageringDTO.toString());
        TypeOfWagering typeOfWagering = typeOfWageringDAO.findById(Integer.parseInt(wageringDTO
                .getIdOfTypeOfWagering()));

        Wagering wagering = new Wagering(wageringDTO.getName(), wageringDTO.getDescription(),
                wageringDTO.getStatus(), wageringDTO.getPrizeDescription(),
                wageringDTO.getWageringDataType(), "без результата!", createTime, author,
                typeOfWagering);
        return wageringDAO.save(wagering);
    }

    @Transactional
    public Wagering updateStatus(int wageringId) throws NotFoundException {
        Wagering wageringEntity;
        BetComponent betComponent = new BetComponent();
        Optional<Wagering> optionalWagering = wageringDAO.findById(wageringId);
        if (optionalWagering.isPresent()) {
            wageringEntity = optionalWagering.get();
        } else {
            throw new RuntimeException("Runtime exception!!! Entity does not exists! ");
        }

        if (wageringEntity.getStatus() == WageringStatus.DRAFT) {
            wageringEntity.setStatus(WageringStatus.OPEN);
        } else if (wageringEntity.getStatus() == WageringStatus.OPEN // и есть ставки
                ) {
            wageringEntity.setStatus(WageringStatus.DONE);
        }


        return wageringDAO.save(wageringEntity);
    }

    @Transactional
    public Wagering updateWagering(WageringDTO wageringDTO, int wageringId, String userLogin) {

        logger.info("From DTO wageringDataType: " + wageringDTO.toString());

        try {
            Wagering wageringEntity;
            Optional<Wagering> optionalWagering = wageringDAO.findById(wageringId);
            if (optionalWagering.isPresent()) {
                wageringEntity = optionalWagering.get();
            } else {
                throw new RuntimeException("Runtime exception!!! Entity does not exists! ");
            }

            logger.info("Entity from  view: " + wageringEntity.toString());

            if (userLogin.equals(wageringEntity.getTypicalUser().getLogin())) {
                Wagering wagering = new Wagering(wageringEntity.getId(), wageringDTO.getName(),
                        wageringDTO.getDescription(), wageringEntity.getStatus(),
                        wageringDTO.getPrizeDescription(), wageringDTO.getWageringDataType(),
                        wageringEntity.getWageringResult(), wageringEntity.getCreateTime(),
                        wageringEntity.getTypicalUser(),
                        new TypeOfWagering(Integer.parseInt(wageringDTO.getIdOfTypeOfWagering())));
                logger.info("New Entity with lists: " + wagering.toString());

                return wageringDAO.save(wagering);
            } else {
                return wageringEntity;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update WAGERING: does not exist!", e);
            return new Wagering();
        }
    }

    @Transactional
    public Wagering changeStatusToCancelled(int wageringId, String reason) throws NotFoundException {
        Wagering wageringEntity;
        Optional<Wagering> optionalWagering = wageringDAO.findById(wageringId);
        if (optionalWagering.isPresent()) {
            wageringEntity = optionalWagering.get();
        } else {
            throw new RuntimeException("Runtime exception!!! Entity does not exists! ");
        }
        wageringEntity.setWageringResult(reason);
        wageringEntity.setStatus(WageringStatus.CANCELLED);
        return wageringEntity;
    }

    @Transactional
    public Wagering changeStatusToFinished(int wageringId, String reason) throws NotFoundException {
        Wagering wageringEntity;
        Optional<Wagering> optionalWagering = wageringDAO.findById(wageringId);
        if (optionalWagering.isPresent()) {
            wageringEntity = optionalWagering.get();
        } else {
            throw new RuntimeException("Runtime exception!!! Entity does not exists! ");
        }
        wageringEntity.setWageringResult(reason);
        wageringEntity.setStatus(WageringStatus.FINISHED);
        return wageringEntity;
    }

    public List<WageringViewDTO> getAll() {
        List<Wagering> allWagerings = wageringDAO.findAll();
        List<WageringViewDTO> allViews = new ArrayList<>();

        for (int i = 0; i < allWagerings.size(); i++) {
            allViews.add(new WageringViewDTO(allWagerings.get(i).getId(), allWagerings.get(i).getName(), allWagerings.get(i).getDescription(),
                    new WageringStatusDTO(allWagerings.get(i).getStatus(), allWagerings.get(i).getStatus().getDescription()), allWagerings.get(i).getTypicalUser().getLogin()));
        }
        return allViews;
    }

    public HttpStatus showButton(int wageringId, String userLogin) {
        Wagering wageringEntity;
        Optional<Wagering> optionalWagering = wageringDAO.findById(wageringId);
        if (optionalWagering.isPresent()) {
            wageringEntity = optionalWagering.get();
        } else {
            throw new RuntimeException("Runtime exception!!! Entity does not exists! ");
        }
        if (wageringEntity.getTypicalUser().getLogin().equals(userLogin)) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public StatusBetDTO chooseStatus(int wageringId) {
        StatusBetDTO statusBetDTO = new StatusBetDTO(false, false, false, false, false, new WageringStatusDTO());
        Wagering wageringEntity;
        Optional<Wagering> optionalWagering = wageringDAO.findById(wageringId);
        if (optionalWagering.isPresent()) {
            wageringEntity = optionalWagering.get();
        } else {
            throw new RuntimeException("Runtime exception!!! Entity does not exists! ");
        }
        WageringStatus wageringStatus = wageringEntity.getStatus();
        statusBetDTO.getWageringStatusDTO().setWageringStatus(wageringEntity.getStatus());

        if (wageringStatus == WageringStatus.DRAFT) {
            statusBetDTO.setDraft(false);
            statusBetDTO.setOpen(true);
            statusBetDTO.setDone(false);
            statusBetDTO.setFinished(false);
            statusBetDTO.setCancelled(false);
        }

        if (wageringStatus == WageringStatus.OPEN) {
            statusBetDTO.setDraft(false);
            statusBetDTO.setOpen(false);
            statusBetDTO.setDone(true);
            statusBetDTO.setFinished(false);
            statusBetDTO.setCancelled(true);
        }
        if (wageringStatus == WageringStatus.DONE) {
            statusBetDTO.setDraft(false);
            statusBetDTO.setOpen(false);
            statusBetDTO.setDone(false);
            statusBetDTO.setFinished(true);
            statusBetDTO.setCancelled(true);
        }
        if (wageringStatus == WageringStatus.FINISHED) {
            statusBetDTO.setDraft(false);
            statusBetDTO.setOpen(false);
            statusBetDTO.setDone(false);
            statusBetDTO.setFinished(false);
            statusBetDTO.setCancelled(false);
        }


        return statusBetDTO;
    }

    public Wagering getByWageringId(int wageringId) {
        Wagering wagering;
        Optional<Wagering> optionalWagering = wageringDAO.findById(wageringId);
        if (optionalWagering.isPresent()) {
            wagering = optionalWagering.get();
        } else {
            throw new RuntimeException("Runtime exception!!! ");
        }
        return wagering;
    }

    /**
     * We send rules of current type of Wagering, not its id.
     * It is more correct for page wuth full information i think.
     *
     * @param id
     * @return extended information about wagering (with users witch make bet)
     * @throws NotFoundException
     */
    public WageringFullViewDTO getFullInfo(int id) throws RuntimeException {
        WageringFullViewDTO fullInfo;
        Wagering wagering;
        List<UserWithBetDTO> usersWithBet = new ArrayList<>();
        List<UsersBet> usersBets;
        Optional<Wagering> optionalWagering = wageringDAO.findById(id);

        if (optionalWagering.isPresent()) {
            wagering = optionalWagering.get();
        } else {
            throw new RuntimeException("Runtime exception!!! Get Full Info");
        }

        usersBets = usersBetDAO.findAllByWagering(wagering);
        for (UsersBet bet : usersBets) {
            usersWithBet.add(new UserWithBetDTO(bet.getTypicalUser().getLogin(),
                    bet.getTypicalUser().getEmail(), bet.getTypicalUser().getUserName(), bet.getUsersAnswer()));
        }
        fullInfo = new WageringFullViewDTO(wagering.getId(), wagering.getName(), wagering.getDescription(),
                new WageringStatusDTO(wagering.getStatus(), wagering.getStatus().getDescription()),
                wagering.getTypicalUser().getLogin(), wagering.getTypeOfWagering().getRules(),
                wagering.getPrizeDescription(),
                new WageringDataTypeDTO(wagering.getWageringDataType(),
                        wagering.getWageringDataType().getDescription()),
                wagering.getWageringResult(),
                wagering.getCreateTime(), usersWithBet);

        return fullInfo;
    }

    public List<WageringViewDTO> entityToDTO(List<Wagering> wagerings) {
        List<WageringViewDTO> allViewsWagerings = new ArrayList<>();
        for (Wagering wager : wagerings) {
            allViewsWagerings.add(new WageringViewDTO(wager.getId(), wager.getName(), wager.getDescription(),
                    new WageringStatusDTO(wager.getStatus(), wager.getStatus().getDescription()),
                    wager.getTypicalUser().getLogin()));
        }
        return allViewsWagerings;
    }

    public Page<WageringViewDTO> findBySearchTermDTO() {
        Pageable pageRequest = createPageRequest();
        //Obtain search results by invoking the preferred repository method.
        Page<Wagering> allWagerings = wageringDAO.findAll(pageRequest);
        List<WageringViewDTO> allViewsWagerings = new ArrayList<>();

        if (allWagerings.getContent() != null) {
            for (Wagering wager : allWagerings) {
                allViewsWagerings.add(new WageringViewDTO(wager.getId(), wager.getName(), wager.getDescription(),
                        new WageringStatusDTO(wager.getStatus(), wager.getStatus().getDescription()),
                        wager.getTypicalUser().getLogin()));
            }
        } else {
            return null;
        }
//        if (allWagerings.getContent() != null) {
//            //allWagerings.getContent().stream().map());
//        }
        return new PageImpl<WageringViewDTO>(allViewsWagerings);
    }

    public Page<Wagering> findBySearchTerm() {
        Pageable pageRequest = createPageRequest();
        //Obtain search results by invoking the preferred repository method.
        Page<Wagering> searchResultPage = wageringDAO.findAll(pageRequest);
        return searchResultPage;
    }


    private Pageable createPageRequest() {
        //Create a new Pageable object here.
        return new PageRequest(0, 2);
    }
}
