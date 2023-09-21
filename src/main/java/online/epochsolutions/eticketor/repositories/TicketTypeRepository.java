package online.epochsolutions.eticketor.repositories;

import online.epochsolutions.eticketor.models.user.TicketType;
import org.springframework.data.repository.CrudRepository;

public interface TicketTypeRepository extends CrudRepository<TicketType, Long> {
}