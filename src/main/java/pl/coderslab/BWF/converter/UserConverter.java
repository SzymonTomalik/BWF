package pl.coderslab.BWF.converter;

import org.springframework.stereotype.Component;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.model.RegistrationForm;

@Component
public class UserConverter {
    public User convertRegistrationToUser(RegistrationForm registration) {
        User user = new User();
        user.setLogin(registration.getLogin());
        user.setEmail(registration.getEmail());
        user.setPassword(registration.getPass1());
        return user;
    }
}
