package pl.coderslab.BWF.controller;import lombok.RequiredArgsConstructor;import lombok.extern.slf4j.Slf4j;import org.jetbrains.annotations.NotNull;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.ModelAttribute;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import pl.coderslab.BWF.converter.SoccerMatchConverter;import pl.coderslab.BWF.entity.BetGroup;import pl.coderslab.BWF.entity.ScoreType;import pl.coderslab.BWF.entity.SoccerMatch;import pl.coderslab.BWF.converter.DateConverter;import pl.coderslab.BWF.entity.UserGroupAccount;import pl.coderslab.BWF.model.Communique;import pl.coderslab.BWF.model.TypeMatchForm;import pl.coderslab.BWF.services.BetGroupService;import pl.coderslab.BWF.services.MatchService;import pl.coderslab.BWF.services.UserGroupAccountService;import pl.coderslab.BWF.services.UserService;import pl.coderslab.BWF.webClient.matches.MatchesClient;import java.util.ArrayList;import java.util.Comparator;import java.util.List;import java.util.function.Function;@Slf4j@Controller@RequiredArgsConstructor@RequestMapping("/matches")public class MatchController {    private final MatchService matchService;    private final SoccerMatchConverter converter;    private final MatchesClient matchesClient;    private final DateConverter dateConverter;    private final UserService userService;    private final UserGroupAccountService ugaService;    private final BetGroupService betGroupService;    @GetMapping("/matchList")    private String matchList(Model model) {        List<SoccerMatch> soccerMatchList = converter.convertDtoToMatchEntity(matchesClient.getMatches());        matchService.updateAllMatchDataBase(soccerMatchList);        soccerMatchList.sort(Comparator.comparing(SoccerMatch::getMatchDate).reversed());        model.addAttribute("soccerMatchList", soccerMatchList);        model.addAttribute("date", dateConverter.getDateNow());        return "matches/list";    }    @GetMapping("/betMatches")    private String betMatch(Model model, @ModelAttribute("communique") Communique communique, RedirectAttributes redirectAttributes, TypeMatchForm typeMatchForm) {        List<SoccerMatch> matchToBetList = matchService.getActualMatchesToBet();        if(!matchToBetList.isEmpty()) {            if (userService.getCurrentLoggedUser().getUserGroupAccount().size() >= 1) {                List<UserGroupAccount> accountsByUserId = ugaService.findUserGroupAccountsByUserId(userService.getCurrentLoggedUser().getId());                model.addAttribute("accounts", accountsByUserId);                List<BetGroup> betGroups = betGroupService.findUserGroups(userService.getCurrentLoggedUser().getId());                model.addAttribute("betGroups", betGroups);                model.addAttribute("soccerMatchList", matchToBetList);                model.addAttribute("typeMatchForm", typeMatchForm);                model.addAttribute("date", dateConverter.getDateNow());                return "matches/betMatches";            }            communique.setMessage("Aby wytypować mecze musisz należeć do grupy! Wybierz grupę i dołącz!");            redirectAttributes.addFlashAttribute("communique", communique);            return "redirect:../betGroup/list";        }        return "/matches/noMatchesAvailable";    }}