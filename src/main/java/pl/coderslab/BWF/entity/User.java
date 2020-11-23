package pl.coderslab.BWF.entity;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Login is mandatory")
    private String login;
        @Email
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password= BCrypt.hashpw("",BCrypt.gensalt());
    @OneToMany(mappedBy = "user")
    private List<UserGroupAccount> userGroupAccount;

    public User() {
    }

    public User(@NotBlank(message = "Login is mandatory") String login, @Email @NotBlank(message = "Email is mandatory") String email, @NotBlank(message = "Password is mandatory") String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<pl.coderslab.BWF.entity.UserGroupAccount> getUserGroupAccount() {
        return userGroupAccount;
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
