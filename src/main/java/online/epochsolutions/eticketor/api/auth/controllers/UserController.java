package online.epochsolutions.eticketor.api.auth.controllers;

import online.epochsolutions.eticketor.api.auth.services.UserService;
import online.epochsolutions.eticketor.api.dtos.RegisterBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterBody registerBody){
        userService.registerUser(registerBody);
        return ResponseEntity.ok().build();
    }

}
