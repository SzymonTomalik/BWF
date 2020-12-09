package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.entity.BetGroup;

import java.util.List;

@Repository
public interface BetGroupRepository extends JpaRepository<BetGroup, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM bet_groups JOIN user_group_accounts uga on bet_groups.id = uga.bet_group_id JOIN users u on u.id = uga.user_id WHERE uga.user_id = ?;")
    public List<BetGroup> findBetGroupsByUserGroupAccountList(Long userId);
}
