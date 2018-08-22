package ru.isys.groupwagering.сontroller;

import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.isys.groupwagering.component.TypeOfWageringComponent;
import ru.isys.groupwagering.component.WageringComponent;
import ru.isys.groupwagering.model.dto.WageringDTO;
import ru.isys.groupwagering.model.dto.WageringDataTypeDTO;
import ru.isys.groupwagering.model.dto.WageringFullViewDTO;
import ru.isys.groupwagering.model.dto.WageringStatusDTO;
import ru.isys.groupwagering.model.entity.TypeOfWagering;
import ru.isys.groupwagering.model.entity.TypicalUser;
import ru.isys.groupwagering.model.entity.Wagering;
import ru.isys.groupwagering.model.enums.WageringDataType;
import ru.isys.groupwagering.model.enums.WageringStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/wagering")
public class WageringRestController {
    @Autowired
    private WageringComponent wageringComponent;

    @Autowired
    private TypeOfWageringComponent typeOfWageringComponent;

    private static Logger logger = Logger.getLogger(TypicalUserRestController.class.getName());

    /**
     * Take information from the form and login of author and send it to the component.
     * Conclude protect from unauthorized user
     *
     * @param wageringDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody WageringDTO wageringDTO, Principal principal) {
        logger.info("This");
        logger.info(wageringDTO.getDescription());

        Date date = new Date();
        try {
            HttpStatus resultStatus = HttpStatus.OK;
            Wagering answer = new Wagering();
            answer = wageringComponent.insertNewWagering(wageringDTO, principal.getName(), date.getTime());
            if (answer.getName() != null && (answer.getStatus() == WageringStatus.DRAFT ||
                    answer.getStatus() == WageringStatus.OPEN)) {
                logger.info("Good result");
                resultStatus = HttpStatus.OK;
            } else {
                logger.info("Caught the error");
                resultStatus = HttpStatus.BAD_REQUEST;
            }

            return new ResponseEntity<>(answer, resultStatus);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed insert WAGERING", e);
            return new ResponseEntity<>("Catch the exception", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") int id,
                                    @RequestBody WageringDTO wageringDTO, Principal principal) {
        // logger.info("controller works here: 11111    ");
        logger.info("id not null =   " + id);

        try {
            Wagering wagering = wageringComponent.updateWagering(wageringDTO, id, principal.getName());
            logger.info("Good result ");
            return new ResponseEntity<>(wagering, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update WAGERING", e);
            return new ResponseEntity<>("Catch the exception in update WAGERING", HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/toCancelStatus/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatusToCancelled(@PathVariable("id") int id,
                                                     @RequestParam(value = "reason", required = false) String reason) {
        logger.info("id not null =   " + id);
        try {
            Wagering wagering = wageringComponent.changeStatusToCancelled(id, reason);
            logger.info("Good result ");
            return new ResponseEntity<>(wagering, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Catch the exception in update WAGERING", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/toFinishStatus/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatusToFinished(@PathVariable("id") int id,
                                                     @RequestParam(value = "reason", required = false) String reason) {
        logger.info("id not null =   " + id);
        try {
            Wagering wagering = wageringComponent.changeStatusToFinished(id, reason);
            logger.info("Good result ");
            return new ResponseEntity<>(wagering, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update WAGERING", e);
            return new ResponseEntity<>("Catch the exception in update WAGERING", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@PathVariable("id") int id) {
        logger.info("id not null =   " + id);
        try {
            Wagering wagering = wageringComponent.updateStatus(id);
            logger.info("Good result ");
            return new ResponseEntity<>(wagering, HttpStatus.OK);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to update WAGERING", e);
            return new ResponseEntity<>("Catch the exception in update WAGERING", HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/takeListOfStatuses", method = RequestMethod.GET)
    public ResponseEntity<?> takeListOfStatuses() {
        List<WageringStatus> allStatuses;
        List<WageringStatusDTO> allStatusesWithDescription = new ArrayList<WageringStatusDTO>();
        allStatuses = Arrays.asList(WageringStatus.values());
        logger.info("Statuses:  failed" + allStatuses.toString());

        for (int i = 0; i < allStatuses.size(); i++) {
            allStatusesWithDescription.add(new WageringStatusDTO(allStatuses.get(i), allStatuses.get(i).getDescription()));
        }
        logger.info("Statuses: " + allStatusesWithDescription.toString());
        return new ResponseEntity<>(allStatusesWithDescription, HttpStatus.OK);
    }

    /**
     * @return DTO with data types like DATE, STRING, NUMBER with description
     */
    @RequestMapping(value = "/takeListOfTypes", method = RequestMethod.GET)
    public ResponseEntity<?> takeListOfDataTypes() {
        List<WageringDataType> allTypes;
        List<WageringDataTypeDTO> allStatusesWithDescription = new ArrayList<WageringDataTypeDTO>();

        allTypes = Arrays.asList(WageringDataType.values());
        for (int i = 0; i < allTypes.size(); i++) {
            allStatusesWithDescription.add(new WageringDataTypeDTO(allTypes.get(i), allTypes.get(i).getDescription()));
        }
        logger.info("Statuses: " + allStatusesWithDescription.toString());
        return new ResponseEntity<>(allStatusesWithDescription, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllWagerings", method = RequestMethod.GET)
    public ResponseEntity<?> getAllWagerings() {
        return new ResponseEntity<>(wageringComponent.getAll(), HttpStatus.OK);
    }

    /**
     * @return Wagering types like Half - win half of users, Only One - win only one user, etc.
     */
    @RequestMapping(value = "/takeListOfWageringTypes", method = RequestMethod.GET)
    public ResponseEntity<?> takeListOfWageringTypes() {
        return new ResponseEntity<>(typeOfWageringComponent.getAllTypes(), HttpStatus.OK);
    }
//       Example of good catching the exceptions. Keep it.
//            catch (Exception e){
//            logger.log(Level.SEVERE, "kajbdfsklhas", e);
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }


    /**
     * @return extended information about wagering n JSON format
     * @throws NotFoundException
     */
    @RequestMapping(value = "/getInfoAboutCurrentWagering", method = RequestMethod.GET)
    public ResponseEntity<?> getInfoAboutCurrentWagering(@RequestParam("wageringId") int wageringId) throws NotFoundException {
        return new ResponseEntity<>(wageringComponent.getFullInfo(wageringId), HttpStatus.OK);
    }

    @RequestMapping(value = "/showButtonUpdate", method = RequestMethod.GET)
    public ResponseEntity<?> showButtonUpdate(@RequestParam("wageringId") int wageringId, Principal principal) throws NotFoundException, InterruptedException {

        return new ResponseEntity<>(wageringComponent.showButton(wageringId, principal.getName()));
    }

    @RequestMapping(value = "/chooseStatus", method = RequestMethod.GET)
    public ResponseEntity<?> chooseStatus(@RequestParam("wageringId") int wageringId, Principal principal) throws NotFoundException, InterruptedException {
        HttpStatus resultStatus = HttpStatus.OK;
        return new ResponseEntity<>(wageringComponent.chooseStatus(wageringId), resultStatus);
    }

    @RequestMapping(value = "/paging", method = RequestMethod.GET)
    public ResponseEntity<?> paging() {
        return new ResponseEntity<>(typeOfWageringComponent.getAllTypes(), HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/getAllPagingWagering", method = RequestMethod.GET)
//    public Page<WageringDTO> findBy() {
//        return wageringComponent.findBySearchTermDTO();
//    }
//
//    @RequestMapping(value = "/getAllPagingWagering", method = RequestMethod.GET)
//    public Page<Wagering> findBy() {
//        return wageringComponent.findBySearchTerm();
//    }
}
