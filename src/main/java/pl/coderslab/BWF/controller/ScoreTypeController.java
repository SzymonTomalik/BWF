package pl.coderslab.BWF.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.BWF.converter.SoccerMatchConverter;
import pl.coderslab.BWF.converter.TypeMatchFormConverter;
import pl.coderslab.BWF.entity.ScoreType;
import pl.coderslab.BWF.model.TypeMatch;
import pl.coderslab.BWF.services.*;
import pl.coderslab.BWF.webClient.matches.MatchesClient;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/scoreTypes")
public class ScoreTypeController {
    private final BetGroupService betGroupService;
    private final UserGroupAccountService userGroupAccountService;
    private final UserService userService;
    private final ScoreTypeService scoreTypeService;
    private final MatchService matchService;
    private final SoccerMatchConverter converter;
    private final MatchesClient matchesClient;
    private final TypeMatchFormConverter scoreTypeConverter;



    @GetMapping("/myTypes/{id}")
    public String userTypes(@PathVariable Long id, Model model) {
        //zakomentować do pokazania edycji typu
        matchService.updateAllMatchDataBase(converter.convertDtoToMatchEntity(matchesClient.getMatches()));

        List<ScoreType> myTypes = scoreTypeService.findAllUserTypesInGroup(userService.getCurrentLoggedUser().getId(), id);
        myTypes.sort(Comparator.comparing(ScoreType::getSoccerMatchId).reversed());
        model.addAttribute("myTypesList", myTypes);
        model.addAttribute("betGroup", betGroupService.getBetGroup(id));
        List<ScoreType> changeableTypes=new ArrayList<>();
        for (ScoreType myType:myTypes) {
            if (scoreTypeService.isTypeChangeable(myType)){
                changeableTypes.add(myType);
            }
        }
        model.addAttribute("changeableTypes", changeableTypes);
        return "scoreTypes/myTypes";
    }

    @GetMapping("/edit/scoreTypeId={scoreTypeId}")
    public String editScoreType(@PathVariable Long scoreTypeId, Model model, @ModelAttribute TypeMatch typeMatch){
        model.addAttribute("scoreToEdit", scoreTypeService.getScoreType(scoreTypeId));
        model.addAttribute("newType", typeMatch);
        return "scoreTypes/edit";
    }
    @PostMapping("/edit/scoreTypeId={scoreTypeId}")
    public String proceedScoreTypeEdits(@PathVariable Long scoreTypeId, @Valid @ModelAttribute ("newType") TypeMatch typeMatch){
        ScoreType newTypeMatch = scoreTypeConverter.convertToScoreType(typeMatch);
        newTypeMatch.setUserAccount(scoreTypeService.getScoreType(scoreTypeId).getUserAccount());
        scoreTypeService.editScoreType(newTypeMatch);
        return "redirect:/scoreTypes/myTypes/"+scoreTypeService.getScoreType(scoreTypeId).getUserAccount().getBetGroup().getId();
    }

}
