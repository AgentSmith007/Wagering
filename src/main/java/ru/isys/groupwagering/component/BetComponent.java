package ru.isys.groupwagering.component;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.isys.groupwagering.model.dao.TypicalUserDAO;
import ru.isys.groupwagering.model.dao.UsersBetDAO;
import ru.isys.groupwagering.model.dao.WageringDAO;
import ru.isys.groupwagering.model.dto.BetDTO;
import ru.isys.groupwagering.model.dto.UserWithBetDTO;
import ru.isys.groupwagering.model.dto.WageringStatusDTO;
import ru.isys.groupwagering.model.dto.WageringViewDTO;
import ru.isys.groupwagering.model.entity.TypicalUser;
import ru.isys.groupwagering.model.entity.UsersBet;
import ru.isys.groupwagering.model.entity.Wagering;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class BetComponent {

    @Autowired
    private UsersBetDAO usersBetDAO;
    @Autowired
    private WageringDAO wageringDAO;
    @Autowired
    private TypicalUserDAO typicalUserDAO;

    public BetComponent() {
    }

    private static Logger logger = Logger.getLogger(BetComponent.class.getName());

    /**
     * Here does not defeat from non exist wagering because from
     *
     * @param betDTO
     * @return
     */
    @Transactional
    public UsersBet insertNewBet(BetDTO betDTO) {
        UsersBet result;
        Wagering wagering;
        TypicalUser typicalUser = typicalUserDAO.findByLogin(betDTO.getUserLogin());
        Optional<Wagering> optionalWagering = wageringDAO.findById(Integer.parseInt(betDTO.getWageringId()));

        if (optionalWagering.isPresent()) {
            wagering = optionalWagering.get();
        } else {
            throw new RuntimeException("Wagering does not exist");
        }

        result = usersBetDAO.save(new UsersBet(betDTO.getUsersAnswer(), wagering, typicalUser));

        logger.info(result.toString());

        return result;
    }

    public List<BetDTO> getAll() {
        List<UsersBet> allBets = usersBetDAO.findAll();
        List<BetDTO> allViews = new ArrayList<>();

        for (int i = 0; i < allBets.size(); i++) {
            allViews.add(new BetDTO(allBets.get(i).getUsersAnswer(),
                    String.valueOf(allBets.get(i).getWagering().getId()),
                    allBets.get(i).getTypicalUser().getLogin()));
        }
        return allViews;
    }

    public List<BetDTO> getCurrentWageringBets(int wageringId) throws NotFoundException {
        List<UsersBet> usersBets;
        List<BetDTO> usersBetsDTO = new ArrayList<>();
        Wagering wagering;
        Optional<Wagering> optionalWagering = wageringDAO.findById(wageringId);

        if (optionalWagering.isPresent()) {
            wagering = optionalWagering.get();
        } else {
            throw new NotFoundException("Not Found wagering!");
        }

        usersBets = usersBetDAO.findAllByWagering(wagering);
        for (UsersBet bet : usersBets) {
            usersBetsDTO.add(new BetDTO(bet.getUsersAnswer(), String.valueOf(bet.getWagering().getId()),
                    bet.getTypicalUser().getLogin()));
        }
        return usersBetsDTO;
    }


}
