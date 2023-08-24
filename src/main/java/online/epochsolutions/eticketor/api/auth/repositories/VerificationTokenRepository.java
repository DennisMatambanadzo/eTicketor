package online.epochsolutions.eticketor.api.auth.repositories;

import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.models.user.VerificationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    void deleteByUser(User user);
    Optional<VerificationToken> findByToken(String token);
}