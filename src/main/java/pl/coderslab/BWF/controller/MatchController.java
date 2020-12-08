package pl.coderslab.BWF.controller;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import pl.coderslab.BWF.converter.SoccerMatchConverter;import pl.coderslab.BWF.entity.BetGroup;import pl.coderslab.BWF.entity.ScoreType;import pl.coderslab.BWF.entity.SoccerMatch;import pl.coderslab.BWF.converter.DateConverter;import pl.coderslab.BWF.entity.UserGroupAccount;import pl.coderslab.BWF.model.Communique;import pl.coderslab.BWF.services.BetGroupService;import pl.coderslab.BWF.services.MatchService;import pl.coderslab.BWF.services.UserGroupAccountService;import pl.coderslab.BWF.services.UserService;import pl.coderslab.BWF.webClient.matches.MatchesClient;import java.util.Comparator;import java.util.List;@Slf4j@Controller@RequiredArgsConstructor@RequestMapping("/matches")public class MatchController {    private final MatchService matchService;    private final SoccerMatchConverter converter;    private final MatchesClient matchesClient;    private final DateConverter dateConverter;    private final UserService userService;    private final UserGroupAccountService ugaService;    private final BetGroupService betGroupService;    @GetMapping("/matchList")    private String matchList(Model model) {        List<SoccerMatch> soccerMatchList = converter.convertDtoToMatchEntity(matchesClient.getMatches());        matchService.updateAllMatchDataBase(soccerMatchList);        soccerMatchList.sort(Comparator.comparing(SoccerMatch::getMatchDate).reversed());        model.addAttribute("soccerMatchList", soccerMatchList);        model.addAttribute("date", dateConverter.getDateNow());        return "matches/list";    }@GetMapping("/betMatches")    private String betMatch(ScoreType scoreType,Model model, @ModelAttribute("communique") Communique communique, RedirectAttributes redirectAttributes) {        if (userService.getCurrentLoggedUser().getUserGroupAccount().size() >= 1) {            List<UserGroupAccount> accountsByUserId = ugaService.findUserGroupAccountsByUserId(userService.getCurrentLoggedUser().getId());            model.addAttribute("accounts",accountsByUserId);            List<BetGroup> betGroups = betGroupService.showBetGroups();            model.addAttribute("betGroups", betGroups);            model.addAttribute("scoreType", scoreType);            return "matches/betMatches";        }        communique.setMessage("Aby wytypować mecze musisz należeć do grupy! Wybierz grupę i dołącz!");        redirectAttributes.addFlashAttribute("communique", communique);        return "redirect:../betGroup/list";    }}