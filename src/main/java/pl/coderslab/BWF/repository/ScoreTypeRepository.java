package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.entity.ScoreType;

import java.util.List;

@Repository
public interface ScoreTypeRepository extends JpaRepository<ScoreType, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM score_types JOIN user_group_accounts uga on score_types.user_account_id = uga.id WHERE user_id=? AND bet_group_id=?;")
    public List<ScoreType> findAllByUserIdAndUserBetGroup(Long userId, Long betGroupId);

    @Query(nativeQuery = true, value = "SELECT * FROM score_types JOIN user_group_accounts uga on score_types.user_account_id = uga.id JOIN soccer_matches sm on sm.id = score_types.soccer_match_id WHERE user_account_id=? AND soccer_match_id=?;")
    public ScoreType findByUserAccountIdAndSoccerMatchId(Long userAccount_id, Long soccerMatchId);

    public List<ScoreType> findAllByUserAccountId(Long userAccount_id);

    @Query(nativeQuery = true, value = "SELECT * FROM score_types JOIN user_group_accounts uga on score_types.user_account_id = uga.id JOIN bet_groups bg on bg.id = uga.bet_group_id WHERE bet_group_id=?")
    public List<ScoreType> findAllByBetGroupId(Long betGroupId);

    @Query(nativeQuery = true, value = "SELECT * FROM score_types JOIN user_group_accounts uga on score_types.user_account_id = uga.id JOIN bet_groups bg on bg.id = uga.bet_group_id WHERE bet_group_id=? AND user_id=?;")
    public List<ScoreType> findAllByUserIdAndBetGroupId(Long userId, Long betGroupId);


}
