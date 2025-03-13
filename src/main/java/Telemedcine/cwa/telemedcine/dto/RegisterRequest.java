package Telemedcine.cwa.telemedcine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String nom;
    private String email;
    private String password;
    private String role; // doit correspondre aux valeurs de l'Enum Role
}
