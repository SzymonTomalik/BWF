package pl.coderslab.BWF.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "bet_groups")
public class BetGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @Size(max = 80)
    private String name;
    @OneToMany(targetEntity = UserGroupAccount.class, mappedBy = "betGroup")
    private List<UserGroupAccount> userGroupAccountList = new ArrayList<>();

    public BetGroup(String name, List<UserGroupAccount> usersGroupAccountList) {
        this.name = name;
        this.userGroupAccountList = usersGroupAccountList;
    }

    public BetGroup() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserGroupAccountList(List<UserGroupAccount> userGroupAccountList) {
        this.userGroupAccountList = userGroupAccountList;
    }

    @Override
    public String toString() {
        return "BetGroup{" +
                "name='" + name + '\'' +
                '}' +
                '\n';
    }
}
