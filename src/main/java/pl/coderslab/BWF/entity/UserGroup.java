package pl.coderslab.BWF.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_groups")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @UniqueElements
    private String name;
    @OneToMany(targetEntity = UserGroupAccount.class, mappedBy = "userGroup")
    private List<UserGroupAccount> userGroupAccountList = new ArrayList<>();

    public UserGroup(String name, List<UserGroupAccount> usersGroupAccountList) {
        this.name = name;
        this.userGroupAccountList = usersGroupAccountList;
    }

    public UserGroup() {

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
