package online.epochsolutions.eticketor.repositories;


import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends ListCrudRepository<Event, Long> {



}