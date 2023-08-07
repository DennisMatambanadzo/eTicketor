package online.epochsolutions.eticketor.api.auth.repositories;

import online.epochsolutions.eticketor.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByEmailIgnoreCase(String email);

    User findByEmail(String email);


}
