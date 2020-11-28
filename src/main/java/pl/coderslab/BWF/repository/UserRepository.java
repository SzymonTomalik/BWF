package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.BWF.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users JOIN user_group_accounts uga on users.id = uga.user_id WHERE bet_group_id =?;")
    public List<User> findUsersByBetGroup(Long id);
}
