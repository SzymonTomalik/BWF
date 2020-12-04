package pl.coderslab.BWF.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "score_types")
public class ScoreType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    private int typedHomeTeamResult;
    @PositiveOrZero
    private int typedAwayTeamResult;
    private int points=0;

    @ManyToOne
    private UserGroupAccount userAccount;

    @ManyToOne
    private SoccerMatch soccerMatch;

    public ScoreType(@PositiveOrZero int typedHomeTeamResult, @PositiveOrZero int typedAwayTeamResult, SoccerMatch soccerMatch) {
        this.typedHomeTeamResult = typedHomeTeamResult;
        this.typedAwayTeamResult = typedAwayTeamResult;
        this.soccerMatch = soccerMatch;
    }

    public ScoreType() {
    }

    @Override
    public String toString() {
        return "ScoreType{" +
                "typedHomeTeamResult=" + typedHomeTeamResult +
                ", typedAwayTeamResult=" + typedAwayTeamResult +
                ", userAccount=" + userAccount +
                ", points=" + points +
                ", soccerMatch=" + soccerMatch +
                '}';
    }
}
