package online.epochsolutions.eticketor.repositories;

import online.epochsolutions.eticketor.models.Event;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface EventRepository extends ListCrudRepository<Event, Long> {
    @Override
    Optional<Event> findById(Long aLong);

    Optional<Event> findByNameIgnoreCase(String name);


}