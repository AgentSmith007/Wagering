package ru.isys.groupwagering.component;

import ru.isys.groupwagering.model.entity.TypicalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;


@Component
public class SampleProvider implements AuthenticationProvider {


    @Autowired
    TypicalUserComponent typicalUserComponent = new TypicalUserComponent();

    private TypicalUser authorizedUser = new TypicalUser();
    private static Logger log = Logger.getLogger(SampleProvider.class.getName());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            authorizedUser = typicalUserComponent.userAuthorization(username, password);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("User or password incorrect");
        }
        return new UsernamePasswordAuthenticationToken(authorizedUser.getLogin(), password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}



