package pl.coderslab.BWF.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.BWF.entity.BetGroup;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.services.BetGroupService;
import pl.coderslab.BWF.services.UserGroupAccountService;
import pl.coderslab.BWF.services.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final UserService userService;
    private final BetGroupService betGroupService;
    private final UserGroupAccountService userGroupAccountService;

    //dodawanie usera
    @GetMapping("/add")
    @ResponseBody
    public String addUser() {
        User user = new User();
        user.setEmail("test@test.pl");
        user.setLogin("Test");
        user.setPassword("pass");
        userService.addUser(user);
        return "Id dodanego usera to:" + user.getId();
    }

    //uzupełnienie bazy userami
    @GetMapping("/addUsers")
    @ResponseBody
    public String addUsers() {
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setEmail(i + "useremail" + i + "@email.com");
            user.setLogin(i + "user" + i);
            user.setPassword("pass");
            userService.addUser(user);
        }
        return userService.showUsers() + "\n" + "Użytkownicy zostali dodani";

    }

    //pobieranie usera
    @GetMapping("/get/{id}")
    @ResponseBody
    public String getUser(@PathVariable Long id) {
        User user = userService.get(id).get();
        return user.toString();
    }

    //dodawanie grupy
    @GetMapping("/add/group")
    @ResponseBody
    public String addGroup() {
        BetGroup betGroup = new BetGroup();
        betGroup.setName("Nowa Grupa Typerów");
        betGroupService.addBetGroup(betGroup);
        return "Id dodanej grupy to:" + betGroup.getId();
    }

    // uzupełnienie bazy grupami
    @GetMapping("/add/groups")
    @ResponseBody
    public String addGroups() {
        for (int i = 1; i <= 3; i++) {
            BetGroup betGroup = new BetGroup();
            betGroup.setName(("Grupa Typerów") + " " + i);
            betGroupService.addBetGroup(betGroup);
            System.out.println("Id dodanej grupy to:" + betGroup.getId());
        }
        return betGroupService.showBetGroups() + "\n" + "Grupy zostały doadene";

    }

    //wyświetlanie użytkowników z podanej grupy
    @GetMapping("/get/user/group/{id}")
    @ResponseBody
    public String getUsersFromBetGroup(@PathVariable Long id) {
        return userService.showUserFromGroup(id).toString();
    }

    //dodaj uzytkownika do grupy
    @GetMapping("/add/user/{userId}/group/{betGroupId}")
    @ResponseBody
    public String addUserToBetGroup(@PathVariable Long userId, @PathVariable Long betGroupId) {
        User user = userService.getUser(userId);
        BetGroup betGroup = betGroupService.getBetGroup(betGroupId);
        userGroupAccountService.addUserToBetGroup(user, betGroup);
        String added=" was added to ";
        return user.getLogin() + added + betGroup.getName();
    }
}
