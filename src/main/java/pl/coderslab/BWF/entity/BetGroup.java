package pl.coderslab.BWF.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bet_groups")
public class BetGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @UniqueElements
    private String name;
    @OneToMany(targetEntity = UserGroupAccount.class, mappedBy = "betGroup")
    private List<UserGroupAccount> userGroupAccountList = new ArrayList<>();

    public BetGroup(String name, List<UserGroupAccount> usersGroupAccountList) {
        this.name = name;
        this.userGroupAccountList = usersGroupAccountList;
    }

    public BetGroup() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserGroupAccount> getUserGroupAccountList() {
        return userGroupAccountList;
    }

    public void setUserGroupAccountList(List<UserGroupAccount> userGroupAccountList) {
        this.userGroupAccountList = userGroupAccountList;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BetGroup{" +
                "name='" + name + '\'' +
                '}' +
                '\n';
    }
}
