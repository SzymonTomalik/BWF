package pl.coderslab.BWF.webClient.matches.dto;

import lombok.Getter;

@Getter
public class OpenMatchMatchDto {
    private Long id;
    private String utcDate;
    private String stage;
    private String group;
    private OpenMatchScoreDto score;
    private OpenMatchHomeTeamDto homeTeam;
    private OpenMatchAwayTeamDto awayTeam;
}
