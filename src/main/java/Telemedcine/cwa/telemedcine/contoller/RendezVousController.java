package Telemedcine.cwa.telemedcine.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import Telemedcine.cwa.telemedcine.model.RendezVous;
import Telemedcine.cwa.telemedcine.model.StatutRdv;
import Telemedcine.cwa.telemedcine.repositories.RendezVousRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    // ✅ 1️⃣ Le patient demande un rendez-vous
    @PreAuthorize("hasAuthority('PATIENT')")
    @PostMapping("/demander")
    public ResponseEntity<String> demanderRdv(@RequestBody RendezVous rdv) {
        rdv.setStatut(StatutRdv.EN_ATTENTE);
        rendezVousRepository.save(rdv);
        return ResponseEntity.ok("Demande de rendez-vous envoyée");
    }

    // ✅ 2️⃣ Le médecin voit la liste des rendez-vous en attente
    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/medecin/{medecinId}")
    public ResponseEntity<List<RendezVous>> voirDemandes(@PathVariable Long medecinId) {
        List<RendezVous> rdvs = rendezVousRepository.findByMedecinId(medecinId);
        return ResponseEntity.ok(rdvs);
    }

    // ✅ 3️⃣ Accepter un RDV
    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping("/accepter/{id}")
    public ResponseEntity<String> accepterRdv(@PathVariable Long id) {
        Optional<RendezVous> optionalRdv = rendezVousRepository.findById(id);
        if (!optionalRdv.isPresent()) {
            return ResponseEntity.badRequest().body("Rendez-vous introuvable");
        }
        RendezVous rdv = optionalRdv.get();
        rdv.setStatut(StatutRdv.ACCEPTE);
        rendezVousRepository.save(rdv);
        return ResponseEntity.ok("RDV accepté");
    }

    // ✅ 4️⃣ Refuser un RDV
    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping("/refuser/{id}")
    public ResponseEntity<String> refuserRdv(@PathVariable Long id) {
        Optional<RendezVous> optionalRdv = RendezVous.findById(id);
        if (!optionalRdv.isPresent()) {
            return ResponseEntity.badRequest().body("Rendez-vous introuvable");
        }
        RendezVous rdv = optionalRdv.get();
        rdv.setStatut(StatutRdv.REFUSE);
        RendezVous.save(rdv);
        return ResponseEntity.ok("RDV refusé");
    }

    // ✅ 5️⃣ Rédiger un rapport
    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping("/rapport/{id}")
    public ResponseEntity<String> redigerRapport(@PathVariable Long id, @RequestBody String rapport) {
        Optional<RendezVous> optionalRdv = RendezVous.findById(id);
        if (!optionalRdv.isPresent()) {
            return ResponseEntity.badRequest().body("Rendez-vous introuvable");
        }
        RendezVous rdv = optionalRdv.get();

        if (rdv.getStatut() != StatutRdv.ACCEPTE) {
            return ResponseEntity.badRequest().body("Le RDV doit être accepté avant de rédiger un rapport");
        }

        rdv.setStatut(StatutRdv.TERMINE);
        rdv.setRapport(rapport);
        RendezVous.save(rdv);

        return ResponseEntity.ok("Rapport ajouté avec succès");
    }
}
