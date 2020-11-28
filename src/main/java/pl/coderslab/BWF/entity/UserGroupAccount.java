package pl.coderslab.BWF.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_group_accounts")
public class UserGroupAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private BetGroup betGroup;
    private Integer betPoints;
    @OneToMany(targetEntity = ScoreType.class, mappedBy = "userAccount")
    private List<ScoreType> ScoreTypes=new ArrayList<>();

    public UserGroupAccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BetGroup getUserGroup() {
        return betGroup;
    }

    public void setBetGroup(BetGroup betGroup) {
        this.betGroup = betGroup;
    }

    public Integer getBetPoints() {
        return betPoints;
    }

    public void setBetPoints(Integer betPoints) {
        this.betPoints = betPoints;
    }

    public List<ScoreType> getScoreTypes() {
        return ScoreTypes;
    }

    public void setScoreTypes(List<ScoreType> scoreTypes) {
        ScoreTypes = scoreTypes;
    }

    @Override
    public String toString() {
        return "UserGroupAccount{" +
                "userGroup=" + betGroup +
                ", betPoints=" + betPoints +
                '}';
    }
}
