package online.epochsolutions.eticketor.api.auth.services;

import online.epochsolutions.eticketor.api.auth.repositories.UserRepository;
import online.epochsolutions.eticketor.api.auth.repositories.VerificationTokenRepository;
import online.epochsolutions.eticketor.api.dtos.LoginBody;
import online.epochsolutions.eticketor.api.dtos.RegisterBody;
import online.epochsolutions.eticketor.exceptions.UserAlreadyExistsException;
import online.epochsolutions.eticketor.exceptions.UserNotVerifiedException;
import online.epochsolutions.eticketor.models.User;
import online.epochsolutions.eticketor.models.VerificationToken;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private final VerificationTokenRepository verificationTokenRepository;

    public UserService(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService, VerificationTokenRepository verificationTokenRepository) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    private VerificationToken createVerificationToken(User user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateJWT(user));
        verificationToken.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }
    public void registerUser(RegisterBody registerBody) throws UserAlreadyExistsException {

       if (userRepository.findByEmailIgnoreCase(registerBody.getEmail()).isPresent()){
           throw new UserAlreadyExistsException();
       }
        User user = new User();
        user.setEmail(registerBody.getEmail());
        user.setFirstName(registerBody.getFirstName());
        user.setLastName(registerBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registerBody.getPassword()));
        userRepository.save(user);

    }

    public String loginUser(LoginBody loginBody) {
        Optional<User> opUser = userRepository.findByEmailIgnoreCase(loginBody.getEmail());
        if (opUser.isPresent()){
            User user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){
                return jwtService.generateJWT(user);
            }else{
                List<VerificationToken> verificationTokens = user.getVerificationTokens();
                boolean resend = verificationTokens.size() == 0 ||
                        verificationTokens.get(0).getCreatedTimestamp().before(new Timestamp(System.currentTimeMillis() -(60*60*1000)));
                if (resend){
                    VerificationToken verificationToken = createVerificationToken(user);
                    verificationTokenRepository.save(verificationToken);
                }

            }
        }
        return null;
    }
}
