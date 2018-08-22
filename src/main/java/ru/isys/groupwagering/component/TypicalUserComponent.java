package ru.isys.groupwagering.component;

import ru.isys.groupwagering.model.dao.TypicalUserDAO;
import ru.isys.groupwagering.model.dto.UserProfileDTO;
import ru.isys.groupwagering.model.entity.TypicalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Component
public class TypicalUserComponent {
    @Autowired
    private TypicalUserDAO typicalUserDAO;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private static Logger logger = Logger.getLogger(TypicalUserComponent.class.getName());

    /**
     * Take DTO, make Entity, put Entity in database, return Entity from save
     *
     * @param userName
     * @param email
     * @param login
     * @param password
     * @return TypicalUser answer
     */
    @Transactional
    public TypicalUser userRegistration(String userName, String email, String login,
                                        String password) {
        String protectedPassword = bCryptPasswordEncoder.encode(password);
        logger.info("Protected password: " + protectedPassword);
        TypicalUser typicalUser = new TypicalUser(login, protectedPassword,
                email, userName);
        return typicalUserDAO.save(typicalUser); // А смысл????
    }

    public TypicalUser userAuthorization(String login, String password) throws IllegalArgumentException {
        TypicalUser user = new TypicalUser();
        try {
            user = typicalUserDAO.findByLogin(login);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Database exception");
        }
        logger.info("Password from bd: " + user.getPassword() + " Users password: " + password);
        CharSequence test = bCryptPasswordEncoder.encode(password);
        logger.info("NowPassword: " + test);
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException("Incorrect password");
        }
    }

    public UserProfileDTO userInfo(String login) {
        TypicalUser typicalUser = typicalUserDAO.findByLogin(login);
        return new UserProfileDTO(typicalUser.getLogin(), typicalUser.getEmail(), typicalUser.getUserName());
    }

}
