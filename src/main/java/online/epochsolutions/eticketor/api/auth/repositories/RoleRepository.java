package online.epochsolutions.eticketor.api.auth.repositories;

import online.epochsolutions.eticketor.models.user.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);

    @Override
    void delete(Role entity);
}