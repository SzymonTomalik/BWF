package pl.coderslab.BWF.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.BWF.converter.UserConverter;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.model.RegistrationForm;
import pl.coderslab.BWF.services.UserService;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/form")
    public String registrationForm(Model model) {
        model.addAttribute("registration", new RegistrationForm());
        return "user/form";
    }

    @PostMapping("/form")
    public String processForm(@Valid @ModelAttribute("registration") RegistrationForm registration, BindingResult bindingResult) {
        if (!registration.getPass1().equals(registration.getPass2())) {
            FieldError passError = new FieldError("registration", "pass2", "Hasła muszą być takie same");
            bindingResult.addError(passError);
        }
        for (User user : userService.showUsers()) {
            if (user.getLogin().equals(registration.getLogin())) {
                FieldError loginError = new FieldError("registration", "login", "Podany login już istnieje w bazie");
                bindingResult.addError(loginError);

            }
            if (user.getEmail().equals(registration.getEmail())) {
                FieldError emailError = new FieldError("registration", "email", "Podany email już istnieje w bazie");
                bindingResult.addError(emailError);
            }
        }

        if (bindingResult.hasErrors()) {
            return "user/form";
        } else {
            userService.addUser(userConverter.convertRegistrationToUser(registration));
            return "index";
        }
    }

    @GetMapping("/account")
    public String editAccount(Model model) {
        model.addAttribute("registration", new RegistrationForm());
        User currentLoggedUser = userService.getCurrentLoggedUser();
        model.addAttribute("user", currentLoggedUser);
        return "user/account";
    }

    @PostMapping("/account")
    public String proceedEditAccount(@Valid @ModelAttribute("registration") RegistrationForm registration, BindingResult bindingResult) {
        if (!registration.getPass1().equals(registration.getPass2())) {
            FieldError passError = new FieldError("registration", "pass2", "Hasła muszą być takie same");
            bindingResult.addError(passError);
        }
        User currentLoggedUser = userService.getCurrentLoggedUser();
        for (User user : userService.showUsers()) {
            if (!currentLoggedUser.getLogin().equals(user.getLogin()) && user.getLogin().equals(registration.getLogin())) {
                FieldError loginError = new FieldError("registration", "login", "Podany login już istnieje w bazie");
                bindingResult.addError(loginError);

            }
            if (!currentLoggedUser.getEmail().equals(user.getEmail()) && user.getEmail().equals(registration.getEmail())) {
                FieldError emailError = new FieldError("registration", "email", "Podany email już istnieje w bazie");
                bindingResult.addError(emailError);
            }
        }

        if (bindingResult.hasErrors()) {
            return "user/account";
        } else {
            userService.updateUser(currentLoggedUser, registration);
            return "redirect:/";
        }
    }
}
