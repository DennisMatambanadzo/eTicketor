package online.epochsolutions.eticketor.repositories;

import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends ListCrudRepository<Event, Long> {
    @Override
    Optional<Event> findById(Long aLong);

    List<Event> findByUser(User user);


}