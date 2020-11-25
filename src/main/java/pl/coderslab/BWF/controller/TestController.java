package pl.coderslab.BWF.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.services.UserService;

@Controller
@RequestMapping("/test")
public class TestController {
    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
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


}
