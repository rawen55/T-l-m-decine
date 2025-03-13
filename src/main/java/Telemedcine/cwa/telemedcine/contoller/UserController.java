package Telemedcine.cwa.telemedcine.contoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Telemedcine.cwa.telemedcine.model.User;
import Telemedcine.cwa.telemedcine.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
public ResponseEntity<User> registerUser(@RequestBody User user) {
    try {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(null);
    }
}

@GetMapping("/find")
public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
    User user = userService.findByEmail(email);
    if (user != null) {
        return ResponseEntity.ok(user);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    }

