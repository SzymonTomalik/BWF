package pl.coderslab.BWF.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_groups")
public class UserGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @UniqueElements
    private String name;
    @OneToMany(mappedBy = "userGroups")
    private List<UserGroupAccount> userGroupAccountList = new ArrayList<>();

    public UserGroups(@UniqueElements String name, List<UserGroupAccount> usersGroupAccountList) {
        this.name = name;
        this.userGroupAccountList = usersGroupAccountList;
    }

    public UserGroups() {

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
}
