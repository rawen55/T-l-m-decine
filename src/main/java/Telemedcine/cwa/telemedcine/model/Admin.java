package Telemedcine.cwa.telemedcine.model;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @Column(nullable = false)
    private String niveauAcces; // Exemple : SUPER_ADMIN, MODERATEUR

    public Admin() {}

    public Admin(String nom, String email,Role role, String password, String niveauAcces) {
        super(nom, email, password, role);
        this.niveauAcces = niveauAcces;
    }

    public String getNiveauAcces() { return niveauAcces; }
    public void setNiveauAcces(String niveauAcces) { this.niveauAcces = niveauAcces; }
}
