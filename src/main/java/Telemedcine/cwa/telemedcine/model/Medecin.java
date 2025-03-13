package Telemedcine.cwa.telemedcine.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("MEDECIN")
public class Medecin extends User {

    @Column(nullable = false)
    private String specialite;

    @Column(nullable = false)
    private String numeroOrdre; // Numéro d'inscription à l'ordre des médecins

  
    public Medecin(String nom, String email, String password,Role role, String specialite, String numeroOrdre) {
        super(nom, email, password, role);
        this.specialite = specialite;
        this.numeroOrdre = numeroOrdre;
    }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public String getNumeroOrdre() { return numeroOrdre; }
    public void setNumeroOrdre(String numeroOrdre) { this.numeroOrdre = numeroOrdre; }
}
