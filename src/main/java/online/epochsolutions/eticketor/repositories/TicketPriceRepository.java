package online.epochsolutions.eticketor.repositories;

import online.epochsolutions.eticketor.models.TicketPrice;
import org.springframework.data.repository.CrudRepository;

public interface TicketPriceRepository extends CrudRepository<TicketPrice, Long> {
}