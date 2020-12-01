package pl.coderslab.BWF.services;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Service;import pl.coderslab.BWF.entity.BetGroup;import pl.coderslab.BWF.entity.ScoreType;import pl.coderslab.BWF.entity.User;import pl.coderslab.BWF.entity.UserGroupAccount;import pl.coderslab.BWF.repository.BetGroupRepository;import pl.coderslab.BWF.repository.ScoreTypeRepository;import pl.coderslab.BWF.repository.UserGroupAccountRepository;import pl.coderslab.BWF.repository.UserRepository;import java.util.List;@Service@Slf4j@RequiredArgsConstructorpublic class UserGroupAccountService {    private final UserGroupAccountRepository userGroupAccountRepository;    private final ScoreTypeRepository scoreTypeRepository;    private final UserRepository userRepository;    private final BetGroupRepository betGroupRepository;    public void addUserToBetGroup(User user, BetGroup betGroup) {        UserGroupAccount userGroupAccount = new UserGroupAccount();        userGroupAccount.setUser(user);        userGroupAccount.setBetGroup(betGroup);        userGroupAccountRepository.save(userGroupAccount);    }    public void deleteUserFromBetGroup(User user, BetGroup betGroup) {        userGroupAccountRepository.deleteUserGroupAccountByUserAndBetGroup(user, betGroup);    }    public int countUsersAccountPoints(long userId, long betGroupId) {        List<ScoreType> userScoreTypes = scoreTypeRepository.findAllByUserIdAndUserGroup(userId, betGroupId);        UserGroupAccount userGroupAccount = userGroupAccountRepository.findUserGroupAccountByUserAndBetGroup(userId, betGroupId);        //jeżeli kupon jest uzytkownika z danej grupy to dodaj punkty kuponu        for (ScoreType scoreType : userScoreTypes) {            userGroupAccount.setBetsPoints(userGroupAccount.getBetsPoints() + scoreType.getPoints());        }        return userGroupAccount.getBetsPoints();    }}