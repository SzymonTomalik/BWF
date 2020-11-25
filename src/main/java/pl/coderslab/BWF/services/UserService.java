package pl.coderslab.BWF.services;

import org.springframework.stereotype.Service;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> showUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
