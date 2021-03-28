package pl.coderslab.BWF.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.BWF.entity.BetGroup;
import pl.coderslab.BWF.entity.User;
import pl.coderslab.BWF.entity.UserGroupAccount;
import pl.coderslab.BWF.model.Communique;
import pl.coderslab.BWF.model.FormGroupPassword;
import pl.coderslab.BWF.services.BetGroupService;
import pl.coderslab.BWF.services.ScoreTypeService;
import pl.coderslab.BWF.services.UserGroupAccountService;
import pl.coderslab.BWF.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/betGroup")
public class BetGroupController {
    private final BetGroupService betGroupService;
    private final UserGroupAccountService userGroupAccountService;
    private final UserService userService;
    private final ScoreTypeService scoreTypeService;


    @GetMapping("/add")
    public String newBetGroup(Model model) {
        model.addAttribute("betGroup", new BetGroup());
        return "betGroup/addForm";
    }

    @PostMapping("/add")
    public String processForm(@Valid @ModelAttribute("betGroup") FormGroupPassword formGroupPassword, BindingResult bindingResult) {
        for (BetGroup group : betGroupService.showBetGroups()) {
            if (group.getName().equals(formGroupPassword.getName())) {
                FieldError groupNameError = new FieldError("betGroup", "name", "Grupa o tej nazwie już istnieje w bazie");
                bindingResult.addError(groupNameError);
            }
        }
        if (bindingResult.hasErrors()) {
            return "betGroup/addForm";
        } else {
            BetGroup newBetGroup = new BetGroup();
            newBetGroup.setName(formGroupPassword.getName());
            newBetGroup.setGroupPassword(formGroupPassword.getGroupPassword());
            betGroupService.addBetGroup(newBetGroup);
            return "redirect:/betGroup/list";
        }
    }

    @GetMapping("/list")
    public String groupList(Model model, @ModelAttribute("communique") Communique communique) {
        List<BetGroup> betGroups = betGroupService.showBetGroups();
        model.addAttribute("betGroups", betGroups);
        return "betGroup/list";
    }

    @GetMapping("join/{id}")
    public String joinForm(@PathVariable Long id, Model model) {
        model.addAttribute("betGroup", betGroupService.getBetGroup(id));
        return "betGroup/joinForm";
    }

    @PostMapping("join/{id}")
    public String proceedJoinForm(@PathVariable Long id, @Valid @ModelAttribute("betGroup") FormGroupPassword formGroupPassword, BindingResult bindingResult) {
        if (!BCrypt.checkpw(formGroupPassword.getGroupPassword(), betGroupService.getBetGroup(id).getGroupPassword())) {
            FieldError groupPassError = new FieldError("betGroup", "groupPassword", "Nieprawidłowe hasło");
            bindingResult.addError(groupPassError);
        }
        if (userGroupAccountService.findUserGroupAccountsByBetGroupId(id).contains(userGroupAccountService.createUserBetGroupAccount(userService.getCurrentLoggedUser(), betGroupService.getBetGroup(id)))) {
            FieldError groupAccountError = new FieldError("betGroup", "groupPassword", "Już należysz do tej grupy");
            bindingResult.addError(groupAccountError);
        }
        if (bindingResult.hasErrors()) {
            return "betGroup/joinForm";
        } else {
            userGroupAccountService.addUserToBetGroup(userService.getCurrentLoggedUser(), betGroupService.getBetGroup(id));
            return "redirect:../userList/{id}";
        }
    }

    @GetMapping("/userList/{id}")
    public String userGroupList(@PathVariable Long id, Model model) {
        scoreTypeService.updateAllGroupPointScoreTypes(id);
        List<UserGroupAccount> userGroupAccountList = userGroupAccountService.findUserGroupAccountsByBetGroupId(id);
        for (UserGroupAccount accounts : userGroupAccountList) {
            userGroupAccountService.updateUserAllPointsInGroup(accounts.getUser().getId(), id);
        }

        List<User> groupUsers = userService.showUserFromGroupSortedByPoints(id);
        model.addAttribute("groupUsers", groupUsers);
        model.addAttribute("userGroupAccountList", userGroupAccountList);
        model.addAttribute("betGroup", betGroupService.getBetGroup(id));
        model.addAttribute("user", userService.getCurrentLoggedUser());
        return "betGroup/userList";
    }

    @GetMapping("/user/list")
    public String listOfUserGroups(Model model) {
        List<UserGroupAccount> accounts = userGroupAccountService.findUserGroupAccountsByUserId(userService.getCurrentLoggedUser().getId());
        model.addAttribute("allUserGroups", accounts);
        return "betGroup/usersGroups";
    }
}



