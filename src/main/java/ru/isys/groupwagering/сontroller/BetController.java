package ru.isys.groupwagering.сontroller;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isys.groupwagering.component.BetComponent;
import ru.isys.groupwagering.model.dto.BetDTO;
import ru.isys.groupwagering.model.entity.UsersBet;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/bet")
public class BetController {

    private static Logger logger = Logger.getLogger(TypicalUserRestController.class.getName());

    @Autowired
    private BetComponent betComponent;

    /**
     *
     * @return extended information about wagering n JSON format
     * @throws NotFoundException
     */
    @RequestMapping(value = "/setNewBet", method = RequestMethod.POST)
    public ResponseEntity<?> setNewBet(Principal principal, @RequestBody BetDTO betDTO)  {
        betDTO.setUserLogin(principal.getName());
            return new ResponseEntity<>(betComponent.insertNewBet(betDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/checkRightNewBet", method = RequestMethod.POST)
    public ResponseEntity<?> checkRight(Principal principal, @RequestBody BetDTO betDTO) throws NotFoundException {
        betDTO.setUserLogin(principal.getName());
        List<BetDTO> betDTOList=betComponent.getCurrentWageringBets(Integer.parseInt(betDTO.getWageringId()));
        for(int i=0;i<betDTOList.size();i++){
            if(principal.getName().equals(betDTOList.get(i).getUserLogin())){
                logger.info("U cant do bet again!!!");
                return null;
            }
        }
        return new ResponseEntity<>(betComponent.insertNewBet(betDTO), HttpStatus.OK);
    }


}
