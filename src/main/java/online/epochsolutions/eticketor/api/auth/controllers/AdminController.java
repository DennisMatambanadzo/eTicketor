package online.epochsolutions.eticketor.api.auth.controllers;

import lombok.AllArgsConstructor;
import online.epochsolutions.eticketor.api.auth.services.UserService;
import online.epochsolutions.eticketor.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;

    @GetMapping("/viewUsers")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }


    @GetMapping("/viewHosts")
    public ResponseEntity<List<User>> getHosts(){
        return ResponseEntity.ok(userService.getHosts());
    }
    @GetMapping("/viewAdmins")
    public ResponseEntity<List<User>> getAdmins(){
        return ResponseEntity.ok(userService.getAdmins());
    }

}
