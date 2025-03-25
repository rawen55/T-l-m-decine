package Telemedcine.cwa.telemedcine.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private DossierMedical dossierMedical;

    public Patient() {
        super(); // Nécessaire pour Hibernate
    }

    public Patient(String nom, String prenom, String email, Role role, String password) {
        super(nom, prenom, email, password, role);
    }

    public DossierMedical getDossierMedical() {
        return dossierMedical;
    }

    public void setDossierMedical(DossierMedical dossierMedical) {
        this.dossierMedical = dossierMedical;
    }

    public void chercherMedecin() {
        // Logique pour chercher un médecin
    }

    public void demanderConsultation() {
        // Logique pour demander une consultation
    }

    public void recevoirNotification() {
        // Logique pour recevoir une notification
    }
}
