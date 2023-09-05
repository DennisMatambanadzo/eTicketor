package online.epochsolutions.eticketor.repositories;


import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.user.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends ListCrudRepository<Event, Long> {
    Optional<Event> findByNameIgnoreCase(String name);

    Optional<Event> findByUserAndNameIgnoreCase(User user, String name);


    @Transactional
    @Modifying
    @Query("update Event e set e.remainingTickets = ?1, e.name = ?2 where e.user = ?3")
    int updateEvent(Long numberOfTickets, String name, User user);

    List<Event> findByUser(User user);

    Optional<Event> findByIdAndUser(Long id, User user);

    long deleteByIdAndUser(Long id, User user);





}