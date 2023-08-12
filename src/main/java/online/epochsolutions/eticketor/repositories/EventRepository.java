package online.epochsolutions.eticketor.repositories;

import online.epochsolutions.eticketor.models.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EventRepository extends ListCrudRepository<Event, Long> {

    Optional<Event> findByNameIgnoreCase(String name);







}