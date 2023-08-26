package online.epochsolutions.eticketor.api.auth.controllers;

import jakarta.validation.Valid;
import online.epochsolutions.eticketor.api.auth.services.UserService;
import online.epochsolutions.eticketor.api.dtos.LoginBody;
import online.epochsolutions.eticketor.api.dtos.LoginResponse;
import online.epochsolutions.eticketor.api.dtos.RegisterBody;
import online.epochsolutions.eticketor.api.dtos.RegisterResponse;
import online.epochsolutions.eticketor.exceptions.EmailFailureException;
import online.epochsolutions.eticketor.exceptions.UserAlreadyExistsException;
import online.epochsolutions.eticketor.exceptions.UserNotVerifiedException;
import online.epochsolutions.eticketor.models.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterBody registerBody) throws EmailFailureException {
        try{
            userService.registerUser(registerBody);
            RegisterResponse response = new RegisterResponse();
            response.setFirstName(registerBody.getFirstName());
            response.setEmail(registerBody.getEmail());
            response.setMessage(registerBody.getFirstName() +", you have been registered with the email address: " + registerBody.getEmail());
            return ResponseEntity.ok(response);
        }catch (UserAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) throws UserNotVerifiedException, EmailFailureException {
        String jwt = null;
        try{
            jwt = userService.loginUser(loginBody);
        } catch(UserNotVerifiedException e){
            var response = new LoginResponse();
            response.setSuccess(false);
            var reason = "USER_NOT_VERIFIED";
            if (e.isNewEmailSent()){
                reason += "EMAIL_RESENT";
            }
            response.setFailureReason(reason);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        } catch (EmailFailureException e ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        if(jwt == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            userService.loginUser(loginBody);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(jwt);
            loginResponse.setSuccess(true);
            return ResponseEntity.ok(loginResponse);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity verifyEmail(@RequestParam String token){
        if (userService.verifyUser(token)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/me")
    public User getLoggedInUserProfile(@AuthenticationPrincipal User user){
        return user;
    }


    @GetMapping("/roleHierarchy")
    public ResponseEntity role(){
        return ResponseEntity.ok().build();
    }
}
