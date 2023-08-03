package online.epochsolutions.eticketor.api.auth.services;

import online.epochsolutions.eticketor.api.auth.repositories.UserRepository;
import online.epochsolutions.eticketor.api.dtos.RegisterBody;
import online.epochsolutions.eticketor.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterBody registerBody){
        User user = new User();
        user.setEmail(registerBody.getEmail());
        user.setFirstName(registerBody.getFirstName());
        user.setLastName(registerBody.getLastName());
        user.setPassword(registerBody.getPassword());
        userRepository.save(user);

    }
}
