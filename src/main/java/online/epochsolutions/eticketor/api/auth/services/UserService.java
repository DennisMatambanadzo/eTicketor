package online.epochsolutions.eticketor.api.auth.services;

import online.epochsolutions.eticketor.api.auth.repositories.UserRepository;
import online.epochsolutions.eticketor.api.auth.repositories.VerificationTokenRepository;
import online.epochsolutions.eticketor.api.dtos.LoginBody;
import online.epochsolutions.eticketor.api.dtos.RegisterBody;
import online.epochsolutions.eticketor.exceptions.EmailFailureException;
import online.epochsolutions.eticketor.exceptions.UserAlreadyExistsException;
import online.epochsolutions.eticketor.exceptions.UserNotVerifiedException;
import online.epochsolutions.eticketor.models.user.Role;
import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.models.user.VerificationToken;
import online.epochsolutions.eticketor.services.EmailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;
    private final VerificationTokenRepository verificationTokenRepository;

    private final EmailService emailService;

    public UserService(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService, VerificationTokenRepository verificationTokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.verificationTokenRepository = verificationTokenRepository;

        this.emailService = emailService;
    }

    private VerificationToken createVerificationToken(User user){
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(jwtService.generateJWT(user));
        verificationToken.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        verificationToken.setUser(user);
        user.getVerificationTokens().add(verificationToken);
        return verificationToken;
    }


    public void registerUser(RegisterBody registerBody) throws UserAlreadyExistsException, EmailFailureException {

        checkUser(registerBody);
        User user = new User();
        user.setEmail(registerBody.getEmail());
        user.setFirstName(registerBody.getFirstName());
        user.setLastName(registerBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registerBody.getPassword()));
        user.setRole(Role.USER);
        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        userRepository.save(user);

    }

    public void registerHost(RegisterBody registerBody) throws UserAlreadyExistsException, EmailFailureException {


        checkUser(registerBody);
        User user = new User();
        user.setEmail(registerBody.getEmail());
        user.setFirstName(registerBody.getFirstName());
        user.setLastName(registerBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registerBody.getPassword()));
        user.setRole(Role.HOST);
        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        userRepository.save(user);

    }
    public void registerAdmin(RegisterBody registerBody) throws UserAlreadyExistsException, EmailFailureException {


        checkUser(registerBody);
        User user = new User();
        user.setEmail(registerBody.getEmail());
        user.setFirstName(registerBody.getFirstName());
        user.setLastName(registerBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registerBody.getPassword()));
        user.setRole(Role.ADMIN);
        VerificationToken verificationToken = createVerificationToken(user);
        emailService.sendVerificationEmail(verificationToken);
        userRepository.save(user);

    }

    public String loginUser(LoginBody loginBody) throws EmailFailureException, UserNotVerifiedException {
        Optional<User> opUser = userRepository.findByEmailIgnoreCase(loginBody.getEmail());
        if (opUser.isPresent()){
            User user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){
                if (user.isEmailVerified()){
                    return jwtService.generateJWT(user);
                }else {
                    List<VerificationToken> verificationTokens = user.getVerificationTokens();
                    boolean resend = verificationTokens.size() == 0 ||
                            verificationTokens.get(0).getCreatedTimestamp().before(new Timestamp(System.currentTimeMillis() - (60 * 60 * 1000)));
                    if (resend) {
                        VerificationToken verificationToken = createVerificationToken(user);
                        verificationTokenRepository.save(verificationToken);
                        emailService.sendVerificationEmail(verificationToken);
                    }
                    throw new UserNotVerifiedException(resend);

                }
            }
        }
        return null;
    }

    @Transactional
    public boolean verifyUser(String token) {
        Optional<VerificationToken> opToken = verificationTokenRepository.findByToken(token);
        if (opToken.isPresent()){
            VerificationToken verificationToken = opToken.get();
            var user = verificationToken.getUser();
            if (!user.isEmailVerified()){
                user.setEmailVerified(true);
                userRepository.save(user);
                verificationTokenRepository.deleteByUser(user);
                return true;
            }
        }
        return false;
    }

    private void checkUser(RegisterBody registerBody) throws UserAlreadyExistsException {
        if (userRepository.findByEmailIgnoreCase(registerBody.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
    }

    public List<User> getUsers() {
        return userRepository.findByRole(Role.USER);
    }

    public List<User> getHosts() {
        return userRepository.findByRole(Role.HOST);
    }

    public List<User> getAdmins() {
        return userRepository.findByRole(Role.ADMIN);
    }


}
