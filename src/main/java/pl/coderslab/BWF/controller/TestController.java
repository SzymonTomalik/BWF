package pl.coderslab.BWF.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.entity.BetGroup;
import pl.coderslab.BWF.services.BetGroupService;
import pl.coderslab.BWF.services.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
    private final UserService userService;
    private final BetGroupService betGroupService;

    public TestController(UserService userService, BetGroupService betGroupService) {
        this.userService = userService;
        this.betGroupService = betGroupService;
    }

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

    @GetMapping("/get/{id}")
    @ResponseBody
    public String getUser(@PathVariable Long id) {
        User user = userService.get(id).get();
        return user.toString();
    }

    @GetMapping("/add/group")
    @ResponseBody
    public String addGroup() {
        BetGroup betGroup = new BetGroup();
        betGroup.setName("Nowa z kontrolera");
        betGroupService.addGroup(betGroup);
        return "Id dodanego grupy to:" + betGroup.getId();
    }

    @GetMapping("/get/user/group/{id}")
    @ResponseBody
    public String getUsersFromGroup(@PathVariable Long id) {
        return userService.showUserFromGroup(id).toString();
    }


}
