package Telemedcine.cwa.telemedcine.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {

        @Column(nullable = true)
        private String dossierMedical; // Un champ spécifique au patient
    
       
    
        public Patient(String nom, String email,Role role, String password, String dossierMedical) {
            super(nom, email, password, role);
        this.dossierMedical = dossierMedical;
    }
    public String getDossierMedical() { return dossierMedical; }
    public void setDossierMedical(String dossierMedical) { this.dossierMedical = dossierMedical; }
}
