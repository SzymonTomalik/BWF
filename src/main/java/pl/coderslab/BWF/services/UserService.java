package pl.coderslab.BWF.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.model.RegistrationForm;
import pl.coderslab.BWF.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<User> showUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> showUserFromGroup(Long id) {
        return userRepository.findUsersByBetGroup(id);
    }
    public List<User> showUserFromGroupSortedByPoints(Long id) {
        return userRepository.findUsersByBetGroupsOrderByBetPoints(id);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User userToEdit, RegistrationForm editedUser) {
        userToEdit.setLogin(editedUser.getLogin());
        userToEdit.setEmail(editedUser.getEmail());
        userToEdit.setPassword(editedUser.getPass1());
        userRepository.save(userToEdit);
        log.info("Users data was changed successfully");
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getCurrentLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
    public boolean isLogged(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !authentication.getPrincipal().equals("anonymousUser");

    }

}
