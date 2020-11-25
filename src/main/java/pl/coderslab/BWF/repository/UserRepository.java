package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.BWF.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
