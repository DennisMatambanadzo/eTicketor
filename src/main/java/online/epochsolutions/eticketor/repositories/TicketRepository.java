package online.epochsolutions.eticketor.repositories;

import online.epochsolutions.eticketor.models.Ticket;
import org.springframework.data.repository.ListCrudRepository;

public interface TicketRepository extends ListCrudRepository<Ticket, Long> {

}