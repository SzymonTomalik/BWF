package pl.coderslab.BWF.entity;

import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Login is mandatory")
    @Column(unique = true)
    @Size(max = 40)
    private String login;
    @Email
    @Column(unique = true)
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password = BCrypt.hashpw("", BCrypt.gensalt());
    @OneToMany(targetEntity = UserGroupAccount.class, mappedBy = "user")
    private List<UserGroupAccount> userGroupAccount;

    public User() {
    }

    public User(@NotBlank(message = "Login is mandatory") String login, @Email @NotBlank(message = "Email is mandatory") String email, @NotBlank(message = "Password is mandatory") String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void setUserGroupAccount(List<pl.coderslab.BWF.entity.UserGroupAccount> userGroupAccount) {
        this.userGroupAccount = userGroupAccount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userGroupAccount=" + userGroupAccount +
                '}';
    }
}
