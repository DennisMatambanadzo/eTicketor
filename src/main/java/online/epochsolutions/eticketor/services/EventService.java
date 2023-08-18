package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.User;
import online.epochsolutions.eticketor.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService{
    private final EventRepository eventRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void saveEvent(EventBody eventBody,User user){
        Event event = new Event();
        event.setUser(user);
        event.setName(eventBody.getName());
        event.setEventDescription(eventBody.getEventDescription());
        event.setLocation(eventBody.getLocation());
        event.setEndTime(eventBody.getEndTime());
        event.setStartTime(eventBody.getStartTime());
        event.setPrice(eventBody.getPrice());
        event.setNumberOfTickets(eventBody.getNumberOfTickets());
        event.setAgeLimit(eventBody.getAgeLimit());
        eventRepository.save(event);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEvent(Long id) {
        return eventRepository.findById(id);
    }

    public long deleteEvent(Long id) {
        var event = eventRepository.findById(id );
        if (event.isPresent()){
            eventRepository.deleteById(id);
            return 1;
        }else {
            return 0;
        }


    }

    public List<Event> getEventsByUser(User user) {
        return eventRepository.findByUser(user);
    }

    public Event updateEvent(EventBody event, User user,Long id) {
        var opEvent = eventRepository.findByIdAndUser(id,user).get();

        if(Objects.nonNull(event.getName()) && !"".equalsIgnoreCase(event.getName())){
            opEvent.setName(event.getName());
        }

        if(Objects.nonNull(event.getLocation()) && !"".equalsIgnoreCase(event.getLocation())){
            opEvent.setLocation(event.getLocation());
        }

        if(Objects.nonNull(event.getEventDescription()) && !"".equalsIgnoreCase(event.getEventDescription())){
            opEvent.setEventDescription(event.getEventDescription());
        }

        return eventRepository.save(opEvent);
    }
}
