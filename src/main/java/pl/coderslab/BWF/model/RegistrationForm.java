package pl.coderslab.BWF.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationForm {
    @NotBlank(message = "Login jest wymagany")
    @Size(min = 3, max = 40)
    private String login;
    @NotBlank(message = "Login jest wymagany")
    @Email
    private String email;
    @NotBlank
    private String pass1;
    @NotBlank(message = "Hasła muszą być takie same")
    @Size(min = 5, message = "Hasło musi posiadać minimum {min} znaków")
    private String pass2;

    public RegistrationForm() {
    }
}
