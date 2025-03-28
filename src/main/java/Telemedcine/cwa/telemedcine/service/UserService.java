package Telemedcine.cwa.telemedcine.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Telemedcine.cwa.telemedcine.dto.UserUpdateDTO;
import Telemedcine.cwa.telemedcine.model.Medecin;
import Telemedcine.cwa.telemedcine.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;  

    @Autowired
    private UserRepository utilisateurRepository;

    // Méthode pour mettre à jour le profil de l'utilisateur avec le JWT
    public Telemedcine.cwa.telemedcine.model.User updateProfile(String jwtToken, UserUpdateDTO updateDTO) {
        // Décoder le JWT pour obtenir l'ID de l'utilisateur
        Long userId = jwtService.getUserIdFromJwt(jwtToken);

        // Récupérer l'utilisateur à partir de la base de données
        Telemedcine.cwa.telemedcine.model.User user = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé !"));

        // Mettre à jour les informations de l'utilisateur
        if (updateDTO.getNom() != null) user.setNom(updateDTO.getNom());
        if (updateDTO.getPrenom() != null) user.setPrenom(updateDTO.getPrenom());
        if (updateDTO.getEmail() != null) user.setEmail(updateDTO.getEmail());

        // Si c'est un médecin, on met à jour la spécialité
        if (user instanceof Medecin && updateDTO.getSpecialite() != null) {
            ((Medecin) user).setSpecialite(updateDTO.getSpecialite());
        }

        // Sauvegarder l'utilisateur mis à jour
        return utilisateurRepository.save(user);
    }
}
