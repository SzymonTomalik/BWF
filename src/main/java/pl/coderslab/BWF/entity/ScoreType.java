package pl.coderslab.BWF.entity;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import pl.coderslab.BWF.converter.DateConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
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

    private String createdDate = DateConverter.getDateNow();
    private String changedDate = DateConverter.getDateNow();

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
        return userAccount.equals(scoreType.userAccount) && soccerMatch.equals(scoreType.soccerMatch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccount, soccerMatch);
    }
}
