package pl.coderslab.BWF.controller;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.mindrot.jbcrypt.BCrypt;import org.springframework.security.core.Authentication;import org.springframework.security.core.context.SecurityContextHolder;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.validation.BindingResult;import org.springframework.validation.FieldError;import org.springframework.web.bind.annotation.*;import pl.coderslab.BWF.entity.BetGroup;import pl.coderslab.BWF.entity.User;import pl.coderslab.BWF.entity.UserGroupAccount;import pl.coderslab.BWF.model.FormGroupPassword;import pl.coderslab.BWF.services.BetGroupService;import pl.coderslab.BWF.services.UserGroupAccountService;import pl.coderslab.BWF.services.UserService;import javax.validation.Valid;import java.util.Comparator;import java.util.List;@Controller@RequiredArgsConstructor@Slf4j@RequestMapping("/betGroup")public class BetGroupController {    private final BetGroupService betGroupService;    private final UserGroupAccountService userGroupAccountService;    private final UserService userService;    @GetMapping("/add")    public String newBetGroup(Model model){        model.addAttribute("betGroup", new BetGroup());        return "betGroup/addForm";    }    @PostMapping("/add")    public String processForm(@Valid @ModelAttribute("betGroup") FormGroupPassword formGroupPassword, BindingResult bindingResult){        for (BetGroup group:betGroupService.showBetGroups()             ) {            if (group.getName().equals(formGroupPassword.getName())) {                FieldError groupNameError = new FieldError("betGroup", "name", "Grupa o tej nazwie już istnieje w bazie");                bindingResult.addError(groupNameError);            }        }        if (bindingResult.hasErrors()) {            return "betGroup/addForm";        } else {            BetGroup newBetGroup=new BetGroup();//            log.info(formGroupPassword.getGroupPassword());            newBetGroup.setName(formGroupPassword.getName());            newBetGroup.setGroupPassword(formGroupPassword.getGroupPassword());            betGroupService.addBetGroup(newBetGroup);            return "redirect:/betGroup/list";        }    }    @GetMapping("/list")    public String groupList(Model model){        List<BetGroup> betGroups = betGroupService.showBetGroups();        model.addAttribute("betGroups", betGroups);        return "betGroup/list";    }    @GetMapping("join/{id}")    public String joinForm(@PathVariable Long id, Model model){        model.addAttribute("betGroup", betGroupService.getBetGroup(id));        return "betGroup/joinForm";    }    @PostMapping("join/{id}")    public String proceedJoinForm(@PathVariable Long id, @Valid @ModelAttribute("betGroup") FormGroupPassword formGroupPassword, BindingResult bindingResult){        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//        log.info(betGroupService.getBetGroup(id).getGroupPassword());        User currentLogged = (User) authentication.getPrincipal();        if(!BCrypt.checkpw(formGroupPassword.getGroupPassword(),betGroupService.getBetGroup(id).getGroupPassword())){            FieldError groupPassError = new FieldError("betGroup", "groupPassword", "Nieprawidłowe hasło");            bindingResult.addError(groupPassError);        }        if (userGroupAccountService.findUserGroupAccountsByBetGroupId(id).contains(userGroupAccountService.createUserBetGroupAccount(currentLogged, betGroupService.getBetGroup(id)))){            FieldError groupAccountError = new FieldError("betGroup", "groupPassword", "Już należysz do tej grupy");            bindingResult.addError(groupAccountError);                }        if (bindingResult.hasErrors()) {            return "betGroup/joinForm";        }else {            userGroupAccountService.addUserToBetGroup(currentLogged,betGroupService.getBetGroup(id));            return "redirect:../userList/{id}";        }    }    @GetMapping("/userList/{id}")    public String userGroupList(@PathVariable Long id, Model model) {        List<User> groupUsers = userService.showUserFromGroupSortedByPoints(id);        log.info("Lista użytkowników grupy: "+groupUsers.toString());        List<UserGroupAccount> userGroupAccountList=userGroupAccountService.findUserGroupAccountsByBetGroupId(id);        model.addAttribute("groupUsers",groupUsers);        model.addAttribute("userGroupAccountList",userGroupAccountList);        return "betGroup/userList";    }}