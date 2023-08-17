package online.epochsolutions.eticketor.api.auth.repositories;

import online.epochsolutions.eticketor.models.VerificationToken;
import org.springframework.data.repository.CrudRepository;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
}