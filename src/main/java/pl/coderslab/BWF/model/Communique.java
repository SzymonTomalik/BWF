package pl.coderslab.BWF.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Communique {
    private String message;

    public Communique(String communique) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
