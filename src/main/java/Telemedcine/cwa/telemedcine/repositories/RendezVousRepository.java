package Telemedcine.cwa.telemedcine.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import Telemedcine.cwa.telemedcine.model.RendezVous;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByMedecinId(Long medecinId);
    List<RendezVous> findByPatientId(Long patientId);
}
