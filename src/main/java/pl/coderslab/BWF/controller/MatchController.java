package pl.coderslab.BWF.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.BWF.converter.DateConverter;
import pl.coderslab.BWF.converter.SoccerMatchConverter;
import pl.coderslab.BWF.converter.TypeMatchFormConverter;
import pl.coderslab.BWF.entity.BetGroup;
import pl.coderslab.BWF.entity.ScoreType;
import pl.coderslab.BWF.entity.SoccerMatch;
import pl.coderslab.BWF.entity.UserGroupAccount;
import pl.coderslab.BWF.model.Communique;
import pl.coderslab.BWF.model.TypeMatch;
import pl.coderslab.BWF.model.TypeMatchForm;
import pl.coderslab.BWF.services.*;
import pl.coderslab.BWF.webClient.matches.MatchesClient;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;
    private final SoccerMatchConverter converter;
    private final MatchesClient matchesClient;
    private final UserService userService;
    private final UserGroupAccountService ugaService;
    private final BetGroupService betGroupService;
    private final TypeMatchFormConverter typeMatchFormConverter;
    private final ScoreTypeService scoreTypeService;


    @GetMapping("/matchList")
    public String matchList(Model model) {
        List<SoccerMatch> soccerMatchList = converter.convertDtoToMatchEntity(matchesClient.getMatches());
        matchService.updateAllMatchDataBase(soccerMatchList);
        soccerMatchList.sort(Comparator.comparing(SoccerMatch::getMatchDate).reversed());
        model.addAttribute("soccerMatchList", soccerMatchList);
        model.addAttribute("date", DateConverter.getDateNow());
        return "matches/list";
    }
    @GetMapping("/betMatches")
    public String betMatch(Model model, @ModelAttribute("communique") Communique communique, RedirectAttributes redirectAttributes) {
        List<SoccerMatch> matchToBetList = matchService.getActualMatchesToBet();
        if(!matchToBetList.isEmpty()) {
            if (userService.getCurrentLoggedUser().getUserGroupAccount().size() >= 1) {
                List<UserGroupAccount> accountsByUserId = ugaService.findUserGroupAccountsByUserId(userService.getCurrentLoggedUser().getId());
                model.addAttribute("accounts", accountsByUserId);
                List<BetGroup> betGroups = betGroupService.findUserGroups(userService.getCurrentLoggedUser().getId());
                model.addAttribute("betGroups", betGroups);
                model.addAttribute("soccerMatchList", matchToBetList);
                model.addAttribute("date", DateConverter.getDateNow());
                return "matches/betMatches";
            }else {
                communique.setMessage("Aby wytypować mecze musisz należeć do grupy! Wybierz grupę i dołącz!");
                redirectAttributes.addFlashAttribute("communique", communique);
                return "redirect:../betGroup/list";
            }
        }
        return "/matches/noMatchesAvailable";
    }

    @PostMapping("/betMatches")
    public String proceedBetMatches(@Valid @ModelAttribute("typeMatchForm") TypeMatchForm typeMatchForm, BindingResult bindingResult) {
        List<ScoreType> scoreTypes = typeMatchFormConverter.convertToScoreTypeList(typeMatchForm);
        //tutaj chce wyświetlić odpowiednie komunikaty dla ponizszych sytuacji
        for (ScoreType scoreType:scoreTypes) {
            if(!scoreTypeService.isAlreadyTyped(userService.getCurrentLoggedUser().getId(), typeMatchForm.getBetGroupId(),scoreType)){
                scoreTypeService.addScoreTypeToBase(scoreType);
                //tu docelowo nie ma byc komunikatu, jak dodawanie przebieglo pomyślnie ma przerzucac do listy "twoich typów"
                log.info("Type " +scoreType.toString() + " was added to the base");
            }else {
                if(scoreTypeService.isTypeChangeable(scoreType)){
//                    FieldError typeError = new FieldError("typeMatchForm", "typeMatchList["+scoreTypes.indexOf(scoreType)+"].homeTeamResult", "W obecnej grupie mecz ten został już przez Ciebie wytypowany. Możesz jednorazowo zmienić swój typ w menu \"Twoje Typy\"");
//                    bindingResult.addError(typeError);
                    //tymczasowo edycja tutaj
                    //planowo chce wyświetlić komunikat w formularzu że typ już jest w bazie i możesz go edytować w "moich typach"
                    scoreTypeService.editScoreType(scoreType);
                }else {
//                    FieldError finalTypeError = new FieldError("typeMatchForm", "typeMatchList["+scoreTypes.indexOf(scoreType)+"].matchId", "W obecnej grupie mecz ten został już przez Ciebie wytypowany.");
//                    bindingResult.addError(finalTypeError);
                    //planowo chce wyświetlić komunikat w formularzu poniższy komunikat
                    log.info("Ten typ był już wcześniej edytowany. Nie ma możliwości wprowadznia kolejnych zmian");
                }
            }
        }
        if (bindingResult.hasErrors()) {
            return "/matches/betMatches";
        } else {
            return "redirect:../betGroup/list";
        }
    }

    @ModelAttribute("typeMatchForm")
    public TypeMatchForm populateTypeMatches() {
        TypeMatchForm typeMatchForm = new TypeMatchForm();
        List<TypeMatch> typeMatches = new ArrayList<TypeMatch>();
        for(int i=0; i<matchService.getActualMatchesToBet().size(); i++) {
            typeMatches.add(new TypeMatch());
        }
        typeMatchForm.setTypeMatchList(typeMatches);
        return typeMatchForm;
    }


}
