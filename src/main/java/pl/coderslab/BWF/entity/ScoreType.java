package pl.coderslab.BWF.entity;

import javax.persistence.*;

@Entity
@Table(name = "score_types")
public class ScoreType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typedResult;

    @ManyToOne
    private UserGroupAccount userGroupAccountList;

    @ManyToOne
    private SoccerMatch soccerMatch;

    public ScoreType(String typedResult, SoccerMatch soccerMatch) {
        this.typedResult = typedResult;
        this.soccerMatch = soccerMatch;
    }

    public ScoreType() {
    }

    public Long getId() {
        return id;
    }

    public String getTypedResult() {
        return typedResult;
    }

    public UserGroupAccount getUserGroupAccountList() {
        return userGroupAccountList;
    }

    public SoccerMatch getMatch() {
        return soccerMatch;
    }

    public void setTypedResult(String result) {
        this.typedResult = result;
    }

    public void setUserGroupAccountList(UserGroupAccount userGroupAccount) {
        this.userGroupAccountList = userGroupAccount;
    }

    public void setMatch(SoccerMatch soccerMatch) {
        this.soccerMatch = soccerMatch;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ScoreType{" +
                "id=" + id +
                ", result='" + typedResult + '\'' +
                ", match=" + soccerMatch +
                '}';
    }
}
