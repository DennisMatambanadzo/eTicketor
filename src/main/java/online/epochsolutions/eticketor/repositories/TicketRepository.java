package online.epochsolutions.eticketor.repositories;

import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.models.user.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TicketRepository extends ListCrudRepository<Ticket, Long> {
    List<Ticket> findByUser(User user);

}