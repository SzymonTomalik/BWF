package pl.coderslab.BWF.entity;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.Collection;
import java.util.Objects;

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
    private int points;

    @ManyToOne
    private UserGroupAccount userAccount;

    @ManyToOne
    private SoccerMatch soccerMatch;

    private DateTime createdDate=new DateTime();
    private DateTime changedDate=new DateTime();

    public ScoreType(@PositiveOrZero int typedHomeTeamResult, @PositiveOrZero int typedAwayTeamResult, SoccerMatch soccerMatch, UserGroupAccount userAccount) {
        this.typedHomeTeamResult = typedHomeTeamResult;
        this.typedAwayTeamResult = typedAwayTeamResult;
        this.soccerMatch = soccerMatch;
        this.userAccount = userAccount;
    }

    public ScoreType() {
    }

    @Override
    public String toString() {
        return "ScoreType{" +
                "id=" + id +
                ", typedHomeTeamResult=" + typedHomeTeamResult +
                ", typedAwayTeamResult=" + typedAwayTeamResult +
                ", points=" + points +
                ", userAccount=" + userAccount +
                ", soccerMatch=" + soccerMatch +
                ", created=" + createdDate +
                ", isTypeChanged=" + changedDate +
                '}';
    }

    public Long getSoccerMatchId() {
        return this.soccerMatch.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreType)) return false;
        ScoreType scoreType = (ScoreType) o;
        return typedHomeTeamResult == scoreType.typedHomeTeamResult && typedAwayTeamResult == scoreType.typedAwayTeamResult && userAccount.equals(scoreType.userAccount) && soccerMatch.equals(scoreType.soccerMatch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typedHomeTeamResult, typedAwayTeamResult, userAccount, soccerMatch);
    }
}
