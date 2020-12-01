package pl.coderslab.BWF.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user_group_accounts")
public class UserGroupAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private BetGroup betGroup;
    private Integer betsPoints;
    @OneToMany(targetEntity = ScoreType.class, mappedBy = "userAccount")
    private List<ScoreType> ScoreTypes = new ArrayList<>();

    public UserGroupAccount() {}

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBetGroup(BetGroup betGroup) {
        this.betGroup = betGroup;
    }

    public void setBetsPoints(Integer betPoints) {
        this.betsPoints = betPoints;
    }

    public void setScoreTypes(List<ScoreType> scoreTypes) {
        ScoreTypes = scoreTypes;
    }

    @Override
    public String toString() {
        return "UserGroupAccount{" +
                "userGroup=" + betGroup +
                ", betPoints=" + betsPoints +
                '}';
    }
}
