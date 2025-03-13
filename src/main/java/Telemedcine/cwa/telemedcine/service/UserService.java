package Telemedcine.cwa.telemedcine.service;
import java.util.List;

import Telemedcine.cwa.telemedcine.model.*;
import Telemedcine.cwa.telemedcine.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
@Service
public class UserService {
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }
        return userRepository.save(user);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUsers() {
        Patient patient = new Patient("Ali", "ali@example.com", null, "password123", "Dossier N°123");
        Medecin medecin = new Medecin("Dr. Karim", "karim@example.com", "med123", null, "Cardiologue", "12345");
        Admin admin = new Admin("Admin", "admin@example.com", null, "adminpass", "SUPER_ADMIN");

        userRepository.save(patient);
        userRepository.save(medecin);
        userRepository.save(admin);

        System.out.println("Utilisateurs créés avec succès !");
    }
}
