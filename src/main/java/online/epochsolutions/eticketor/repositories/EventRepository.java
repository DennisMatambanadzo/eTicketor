package online.epochsolutions.eticketor.repositories;

import online.epochsolutions.eticketor.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}