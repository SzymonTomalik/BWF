package pl.coderslab.BWF.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.BWF.entity.BetGroup;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.entity.UserGroupAccount;

import java.util.List;

@Repository
public interface UserGroupAccountRepository extends JpaRepository<UserGroupAccount, Long> {
    public void deleteUserGroupAccountByUserAndBetGroup(User user, BetGroup betGroup);
    public UserGroupAccount findUserGroupAccountByUserIdAndBetGroupId(Long userId, Long betGroupId);
    public List<UserGroupAccount>findAllByBetGroupId(Long betGroupId);
    public List<UserGroupAccount>findAllByUserId(Long userId);


}

