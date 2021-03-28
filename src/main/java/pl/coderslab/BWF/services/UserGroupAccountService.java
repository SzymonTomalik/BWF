package pl.coderslab.BWF.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.coderslab.BWF.entity.BetGroup;
import pl.coderslab.BWF.entity.ScoreType;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.entity.UserGroupAccount;
import pl.coderslab.BWF.repository.ScoreTypeRepository;
import pl.coderslab.BWF.repository.UserGroupAccountRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserGroupAccountService {
    private final UserGroupAccountRepository userGroupAccountRepository;
    private final ScoreTypeRepository scoreTypeRepository;


    public void addUserToBetGroup(User user, BetGroup betGroup) {
        UserGroupAccount userGroupAccount = new UserGroupAccount();
        userGroupAccount.setUser(user);
        userGroupAccount.setBetGroup(betGroup);
        userGroupAccountRepository.save(userGroupAccount);
        log.info(userGroupAccount.getUser().getLogin()+" was added to "+userGroupAccount.getBetGroup().getName());
    }

    public UserGroupAccount createUserBetGroupAccount(User user, BetGroup betGroup) {
        UserGroupAccount userGroupAccount = new UserGroupAccount();
        userGroupAccount.setUser(user);
        userGroupAccount.setBetGroup(betGroup);
        log.info("User "+userGroupAccount.getUser().getLogin()+ " account in group " +userGroupAccount.getBetGroup().getName() + " is already created");
        return userGroupAccount;
    }
    public void updateUserGroupAccountPoints(UserGroupAccount userGroupAccount){
        UserGroupAccount userGroupAccountToEdit = findUserGroupAccountsByUserIdAndBetGroupId(userGroupAccount.getUser().getId(), userGroupAccount.getBetGroup().getId());
        userGroupAccountToEdit.setBetsPoints(userGroupAccount.getBetsPoints());
        userGroupAccountRepository.save(userGroupAccountToEdit);
    }

    public void deleteUserFromBetGroup(User user, BetGroup betGroup) {
        userGroupAccountRepository.deleteUserGroupAccountByUserAndBetGroup(user, betGroup);
        log.info("User " + user.getLogin() +" was deleted from " + betGroup.getName());
    }

    public void updateUserAllPointsInGroup(Long userId, Long betGroupId) {
        int points=0;
        if(findUserGroupAccountsByUserIdAndBetGroupId(userId, betGroupId)!=null && userGroupAccountRepository.findUserGroupAccountByUserIdAndBetGroupId(userId, betGroupId).getId()!=null) {
            UserGroupAccount userGroupAccount = findUserGroupAccountsByUserIdAndBetGroupId(userId, betGroupId);
            Long userGroupAccountId = userGroupAccountRepository.findUserGroupAccountByUserIdAndBetGroupId(userId, betGroupId).getId();
            List<ScoreType> usersTypesInGroup = scoreTypeRepository.findAllByUserAccountId(userGroupAccountId);
            log.info("ilość typów użytkownika w grupie = " + usersTypesInGroup.size());
            for (ScoreType scoreType : usersTypesInGroup) {
                points += scoreType.getPoints();
            }
            userGroupAccount.setBetsPoints(points);
            updateUserGroupAccountPoints(userGroupAccount);
            log.info("User " + userGroupAccount.getUser().getLogin() + " has " + userGroupAccount.getBetsPoints() + " in group " + userGroupAccount.getBetGroup().getName() + " now.");
        }

    }

    public List<UserGroupAccount> findUserGroupAccountsByBetGroupId(Long betGroupId) {

        return userGroupAccountRepository.findAllByBetGroupId(betGroupId);
    }

    public List<UserGroupAccount> findUserGroupAccountsByUserId(Long userId) {
        return userGroupAccountRepository.findAllByUserId(userId);
    }

    public UserGroupAccount findUserGroupAccountsByUserIdAndBetGroupId(Long userId, Long betGroupId) {
        return userGroupAccountRepository.findUserGroupAccountByUserIdAndBetGroupId(userId, betGroupId);
    }
}