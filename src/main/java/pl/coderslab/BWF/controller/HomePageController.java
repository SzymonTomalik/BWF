package pl.coderslab.BWF.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.BWF.services.UserService;

@Controller
@RequiredArgsConstructor
public class HomePageController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage(Model model) {
        if (userService.isLogged()) {
            model.addAttribute("isLogged", true);
        }
        return "index";
    }
}
