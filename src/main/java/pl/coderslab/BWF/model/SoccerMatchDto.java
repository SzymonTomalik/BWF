package pl.coderslab.BWF.model;

import lombok.Builder;
import lombok.Getter;
import pl.coderslab.BWF.converter.DateConverter;

@Getter
@Builder
public class SoccerMatchDto {
    private final Long id;
    private final String stage;

    private final int homeTeamId;
    private final String homeTeam;
    private final int homeTeamScore;

    private final int awayTeamId;
    private final String awayTeam;
    private final int awayTeamScore;

    private final String utcDate;
    private final String score;
    private final String group;

    public String getUtcDate() {
        DateConverter dateConverter = new DateConverter();
        return dateConverter.changeDateToPattern(utcDate);
    }

    @Override
    public String toString() {
        return "SoccerMatchDto{" +
                "id=" + id +
                ", utcDate='" + utcDate + '\'' +
                ", score='" + score + '\'' +
                ", stage='" + stage + '\'' +
                ", homeTeamId=" + homeTeamId +
                ", homeTeam='" + homeTeam + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                ", awayTeam='" + awayTeam + '\'' +
                ", awayTeamId=" + awayTeamId +
                ", group='" + group + '\'' +
                '}';
    }
}
