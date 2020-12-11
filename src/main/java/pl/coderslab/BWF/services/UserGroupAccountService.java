package pl.coderslab.BWF.services;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Service;import pl.coderslab.BWF.entity.BetGroup;import pl.coderslab.BWF.entity.ScoreType;import pl.coderslab.BWF.entity.User;import pl.coderslab.BWF.entity.UserGroupAccount;import pl.coderslab.BWF.repository.ScoreTypeRepository;import pl.coderslab.BWF.repository.UserGroupAccountRepository;import java.util.List;@Service@Slf4j@RequiredArgsConstructorpublic class UserGroupAccountService {    private final UserGroupAccountRepository userGroupAccountRepository;    private final ScoreTypeRepository scoreTypeRepository;    public void addUserToBetGroup(User user, BetGroup betGroup) {        UserGroupAccount userGroupAccount = new UserGroupAccount();        userGroupAccount.setUser(user);        userGroupAccount.setBetGroup(betGroup);        userGroupAccountRepository.save(userGroupAccount);    }    public UserGroupAccount createUserBetGroupAccount(User user, BetGroup betGroup) {        UserGroupAccount userGroupAccount = new UserGroupAccount();        userGroupAccount.setUser(user);        userGroupAccount.setBetGroup(betGroup);        return userGroupAccount;    }    public void deleteUserFromBetGroup(User user, BetGroup betGroup) {        userGroupAccountRepository.deleteUserGroupAccountByUserAndBetGroup(user, betGroup);    }    public int countUsersAccountPoints(long userId, long betGroupId) {        List<ScoreType> userScoreTypes = scoreTypeRepository.findAllByUserIdAndUserBetGroup(userId, betGroupId);        UserGroupAccount userGroupAccount = userGroupAccountRepository.findUserGroupAccountByUserIdAndBetGroupId(userId, betGroupId);        for (ScoreType scoreType : userScoreTypes) {            userGroupAccount.setBetsPoints(userGroupAccount.getBetsPoints() + scoreType.getPoints());        }        return userGroupAccount.getBetsPoints();    }    public List<UserGroupAccount> findUserGroupAccountsByBetGroupId(Long betGroupId) {        return userGroupAccountRepository.findAllByBetGroupId(betGroupId);    }    public List<UserGroupAccount> findUserGroupAccountsByUserId(Long userId) {        return userGroupAccountRepository.findAllByUserId(userId);    }    public UserGroupAccount findUserGroupAccountsByUserIdAndBetGroupId(Long userId, Long betGroupId) {        return userGroupAccountRepository.findUserGroupAccountByUserIdAndBetGroupId(userId, betGroupId);    }}