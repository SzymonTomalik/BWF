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
    private UserGroups userGroups;
    private Integer betPoints;
    @OneToMany(targetEntity = ScoreType.class, mappedBy = "userGroupAccountList")
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

    public UserGroups getUserGroup() {
        return userGroups;
    }

    public void setUserGroup(UserGroups userGroups) {
        this.userGroups = userGroups;
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
}
