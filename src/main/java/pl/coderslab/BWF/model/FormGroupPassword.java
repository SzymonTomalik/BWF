package pl.coderslab.BWF.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormGroupPassword {
    private Long id;
    private String name;
    private String groupPassword;

    public FormGroupPassword(Long id, String name, String groupPassword) {
        this.name = name;
        this.groupPassword = groupPassword;
    }

    public FormGroupPassword() {
    }
}
