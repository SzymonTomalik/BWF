package pl.coderslab.BWF.entity;

import javax.persistence.*;

@Entity
@Table(name = "soccer_matches")
public class SoccerMatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String homeTeam;

    private String awayTeam;

    private String matchResult;

    private String winner;


    private String MatchDate;

    public SoccerMatch(String homeTeam, String awayTeam, String MatchDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.MatchDate = MatchDate;
    }

    public SoccerMatch() {

    }

    public Long getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getMatchResult() {
        return matchResult;
    }

    public String getWinner() {
        return winner;
    }

    public String getMatchDate() {
        return MatchDate;
    }

    public void setMatchResult(String result) {
        this.matchResult = result;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", result='" + matchResult + '\'' +
                ", winner='" + winner + '\'' +
                ", utcDate='" + MatchDate + '\'' +
                '}';
    }
}
