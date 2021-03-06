package pl.coderslab.BWF.converter;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import pl.coderslab.BWF.entity.SoccerMatch;
import pl.coderslab.BWF.model.SoccerMatchDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SoccerMatchConverter {

    public List<SoccerMatch> convertDtoToMatchEntity(List<SoccerMatchDto> dtoList) {
        List<SoccerMatch> soccerMatchList = new ArrayList<>();
        List<SoccerMatchDto> matchDtoList = getWithoutPreliminaries(dtoList);

        for (SoccerMatchDto dtoObject : matchDtoList) {
            SoccerMatch match = SoccerMatch.builder()
                    .id(dtoObject.getId())
                    .stage(dtoObject.getStage())
                    .competitionGroup(dtoObject.getGroup())
                    .matchResult(dtoObject.getScore())
                    .homeTeamId(dtoObject.getHomeTeamId())
                    .homeTeam(dtoObject.getHomeTeam())
                    .homeTeamScore(dtoObject.getHomeTeamScore())
                    .awayTeamId(dtoObject.getAwayTeamId())
                    .awayTeam(dtoObject.getAwayTeam())
                    .awayTeamScore(dtoObject.getAwayTeamScore())
                    .matchDate(dtoObject.getUtcDate())
                    .build();
            soccerMatchList.add(match);
        }
        return soccerMatchList;
    }

    @NotNull
    private List<SoccerMatchDto> getWithoutPreliminaries(List<SoccerMatchDto> dtoList) {
        return dtoList.stream()
                .filter(o -> !o.getStage().contains("PRELIMINARY"))
                .collect(Collectors.toList());
    }

}
