package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users JOIN user_group_accounts uga on users.id = uga.user_id WHERE bet_group_id =?;")
    public List<User> findUsersByBetGroup(Long id);

    public User findUserById(Long id);

    public User findUserByLogin(String login);

    @Query(nativeQuery = true, value ="SELECT * FROM users JOIN user_group_accounts uga on users.id = uga.user_id WHERE bet_group_id =? ORDER BY uga.bets_points DESC;")
    public List<User> findUsersByBetGroupsOrderByBetPoints(Long id);

}
