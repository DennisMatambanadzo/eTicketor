package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService{
    private final EventRepository eventRepository;


    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void saveEvent(EventBody eventBody){
        Event event = new Event();
        event.setName(eventBody.getName());
        event.setEventDescription(eventBody.getEventDescription());
        event.setLocation(eventBody.getLocation());
        event.setEndTime(eventBody.getEndTime());
        event.setStartTime(eventBody.getStartTime());
        event.setPrice(eventBody.getPrice());
        event.setSlots(eventBody.getSlots());
        event.setAgeLimit(eventBody.getAgeLimit());
        eventRepository.save(event);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEvent(Long id) {
        return eventRepository.findById(id);
    }

    public void deleteEvent(Long id) {
        var findById = eventRepository.findById(id);
        findById.ifPresent(event -> eventRepository.deleteById(id));

    }
}
