package pl.coderslab.BWF.entity;

import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @Size(min=4, max = 30, message = "Nazwa grupy musi posiadać od {min} do {max} znaków")
    private String name="";
    @NotBlank(message = "Hasło grupy nie może być puste")
    private String groupPassword;
    @OneToMany(targetEntity = UserGroupAccount.class, mappedBy = "betGroup")
    private List<UserGroupAccount> userGroupAccountList = new ArrayList<>();

    public BetGroup(@Size(min = 5, max = 50, message = "Nazwa grupy musi posiadać od {min} do {max} znaków") String name, String groupPassword) {
        this.name = name;
        this.groupPassword = groupPassword;
    }

    public BetGroup() {

    }

    public void setGroupPassword(String groupPassword) {
        this.groupPassword = BCrypt.hashpw(groupPassword, BCrypt.gensalt());
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BetGroup{" +
                "name='" + name + '\'' +
                '}' +
                '\n';
    }
}
