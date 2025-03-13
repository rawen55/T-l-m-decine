package Telemedcine.cwa.telemedcine.contoller;

import java.util.List;

import javax.tools.Diagnostic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Telemedcine.cwa.telemedcine.service.DiagnosticService;

@RestController
@RequestMapping("/api/diagnostics")
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    @Autowired
    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    // Créer un diagnostic pour un rendez-vous
    @SuppressWarnings("rawtypes")
    @PostMapping("/create/{appointmentId}")
    public ResponseEntity<Diagnostic> createDiagnostic(
            @PathVariable Long appointmentId,
            @RequestParam String description,
            @RequestParam String prescription) {
        
        Diagnostic diagnostic = diagnosticService.createDiagnostic(appointmentId, description, prescription);
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnostic);
    }

   
    @SuppressWarnings("rawtypes")
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<Diagnostic>> getDiagnosticsByAppointment(@PathVariable Long appointmentId) {
        List<Diagnostic> diagnostics = diagnosticService.getDiagnosticsByAppointment(appointmentId);
        return ResponseEntity.ok(diagnostics);
    }
}
