package pl.coderslab.BWF.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "soccer_matches")
@Getter
@Setter
public class SoccerMatch {
    @Id
    private Long id;
    private String stage;
    private String group;
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
