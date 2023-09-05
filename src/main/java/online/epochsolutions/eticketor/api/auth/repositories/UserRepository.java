package online.epochsolutions.eticketor.api.auth.repositories;

import online.epochsolutions.eticketor.models.user.Role;
import online.epochsolutions.eticketor.models.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByRoleAndFirstNameIgnoreCase(Role role, String firstName);
    Optional<User> findByEmailIgnoreCase(String email);

    List<User> findByRole(Role role);

    User findByEmail(String email);


}
