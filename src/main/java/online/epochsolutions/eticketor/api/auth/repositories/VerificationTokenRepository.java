package online.epochsolutions.eticketor.api.auth.repositories;

import online.epochsolutions.eticketor.models.user.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
}