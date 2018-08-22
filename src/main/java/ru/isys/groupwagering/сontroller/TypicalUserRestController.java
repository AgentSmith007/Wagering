package ru.isys.groupwagering.сontroller;

import ru.isys.groupwagering.component.TypicalUserComponent;
import ru.isys.groupwagering.model.entity.TypicalUser;
import ru.isys.groupwagering.model.dto.TypicalUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class TypicalUserRestController {
    @Autowired
    private TypicalUserComponent typicalUserComponent;


    private static Logger logger = Logger.getLogger(TypicalUserRestController.class.getName());


    @RequestMapping(value = "/userRegistration", method = RequestMethod.POST)
    public ResponseEntity<TypicalUser> insert(@RequestBody TypicalUserDTO typicalUserDTO) {
        HttpStatus resultStatus = HttpStatus.OK;
        TypicalUser answer = new TypicalUser();
        // Так надо было сделать?
        if (typicalUserDTO != null) {
            answer = typicalUserComponent.userRegistration(typicalUserDTO.getName(), typicalUserDTO.getEmail(),
                    typicalUserDTO.getLogin(), typicalUserDTO.getPassword());
            // Такая должна быть проверка?
            if (answer.getLogin() != null) {
                logger.info("Good result");
                resultStatus = HttpStatus.OK;
            } else {
                logger.info("Caught the error");
                resultStatus = HttpStatus.BAD_REQUEST;
            }
        }
        // Или тута присобачить возвращение? ЗДЕСЬ ВОПРОС К ИЛЬЕ!!!!
        // TODO: call persistence layer to update
        return new ResponseEntity<>(answer, resultStatus);
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ResponseEntity<?> getUser( Principal principal) {
        return new ResponseEntity<>(typicalUserComponent.userInfo(principal.getName()),HttpStatus.OK);
    }
}

