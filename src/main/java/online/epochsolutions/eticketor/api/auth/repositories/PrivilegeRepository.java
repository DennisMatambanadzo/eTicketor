package online.epochsolutions.eticketor.api.auth.repositories;

import online.epochsolutions.eticketor.models.user.Privilege;
import org.springframework.data.repository.CrudRepository;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findByName(String name);

    @Override
    void delete(Privilege privilege);
}