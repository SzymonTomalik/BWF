package pl.coderslab.BWF.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.coderslab.BWF.entity.ScoreType;
import pl.coderslab.BWF.model.TypeMatch;
import pl.coderslab.BWF.model.TypeMatchForm;
import pl.coderslab.BWF.services.MatchService;
import pl.coderslab.BWF.services.UserGroupAccountService;
import pl.coderslab.BWF.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TypeMatchFormConverter {
    private final MatchService matchService;
    private final UserGroupAccountService ugaService;
    private final UserService userService;

    public List<ScoreType> convertToScoreTypeList(TypeMatchForm typeMatchForm) {
        List<TypeMatch> typeMatchList = typeMatchForm.getTypeMatchList();
        List<ScoreType> scoreTypeList = new ArrayList<>();

        if (typeMatchList.size() > 0)
            for (TypeMatch typeMatch : typeMatchList) {
                ScoreType scoreType = new ScoreType();
                scoreType.setTypedHomeTeamResult(typeMatch.getHomeTeamResult());
                scoreType.setTypedAwayTeamResult(typeMatch.getAwayTeamResult());
                scoreType.setUserAccount(ugaService.findUserGroupAccountsByUserIdAndBetGroupId(userService.getCurrentLoggedUser().getId(), typeMatchForm.getBetGroupId()));
                scoreType.setSoccerMatch(matchService.getMatch(typeMatch.getMatchId()));
                scoreTypeList.add(scoreType);
            }
        return scoreTypeList;
    }

    public ScoreType convertToScoreType(TypeMatch typeMatch) {
        ScoreType scoreType = new ScoreType();
        scoreType.setTypedHomeTeamResult(typeMatch.getHomeTeamResult());
        scoreType.setTypedAwayTeamResult(typeMatch.getAwayTeamResult());
        scoreType.setSoccerMatch(matchService.getMatch(typeMatch.getMatchId()));

        return scoreType;
    }
}
