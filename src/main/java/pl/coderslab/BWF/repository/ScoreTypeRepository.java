package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.entity.ScoreType;

import java.util.List;

@Repository
public interface ScoreTypeRepository extends JpaRepository<ScoreType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM score_types JOIN user_group_accounts uga on score_types.user_account_id = uga.id WHERE user_id=? AND bet_group_id=?;")
    public List<ScoreType> findAllByUserIdAndUserBetGroup(Long userAccountId, Long betGroupId);
}
