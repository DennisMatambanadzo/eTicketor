package online.epochsolutions.eticketor.api.auth.repositories;

import online.epochsolutions.eticketor.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
