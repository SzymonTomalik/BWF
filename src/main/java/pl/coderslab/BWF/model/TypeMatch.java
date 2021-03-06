package pl.coderslab.BWF.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
@Getter
@Setter
public class TypeMatch {
    @NotNull
    @NumberFormat
    private Long matchId;
    @NotNull
    @NumberFormat
    @PositiveOrZero
    private Integer homeTeamResult;
    @NotNull
    @NumberFormat
    @PositiveOrZero
    private Integer awayTeamResult;

    public TypeMatch(@NotNull Long matchId, @NotNull @PositiveOrZero Integer homeTeamResult, @NotNull @PositiveOrZero Integer awayTeamResult) {
        this.matchId = matchId;
        this.homeTeamResult = homeTeamResult;
        this.awayTeamResult = awayTeamResult;
    }

    public TypeMatch() {
    }

    @Override
    public String toString() {
        return "TypeMatch{" +
                "matchId=" + matchId +
                ", homeTeamResult=" + homeTeamResult +
                ", awayTeamResult=" + awayTeamResult +
                '}';
    }
}
