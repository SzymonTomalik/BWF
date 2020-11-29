package pl.coderslab.BWF.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "soccer_matches")
@Getter
@Setter
@Builder
public class SoccerMatch {
    @Id
    private Long id;
    private String stage;
    private String competitionGroup;
    private String matchResult;

    private int homeTeamId;
    private String homeTeam;
    private int homeTeamScore;

    private int awayTeamId;
    private String awayTeam;
    private int awayTeamScore;

    private String matchDate;

    public SoccerMatch() {

    }

    public SoccerMatch(Long id, String stage, String competitionGroup, String matchResult, int homeTeamId, String homeTeam, int homeTeamScore, int awayTeamId, String awayTeam, int awayTeamScore, String matchDate) {
        this.id = id;
        this.stage = stage;
        this.competitionGroup = competitionGroup;
        this.matchResult = matchResult;
        this.homeTeamId = homeTeamId;
        this.homeTeam = homeTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamId = awayTeamId;
        this.awayTeam = awayTeam;
        this.awayTeamScore = awayTeamScore;
        this.matchDate = matchDate;
    }

    @Override
    public String toString() {
        return "SoccerMatch{" +
                "id=" + id +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", matchResult='" + matchResult + '\'' +
                ", matchDate='" + matchDate + '\'' +
                '}';
    }


}
