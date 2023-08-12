package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.models.Event;
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

    public void saveEvent(EventBody eventBody){
        Event event = new Event();
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

    public void deleteEvent(Long id) {
        var findById = eventRepository.findById(id);
        findById.ifPresent(event -> eventRepository.deleteById(id));

    }


    public Event updateEvent(Long id, Event event) {
        var opEvent = eventRepository.findById(id).get();

        if (Objects.nonNull(event.getId()) && event.getId() != 0){
            opEvent.setId(event.getId());
        }
        if (Objects.nonNull(event.getName()) && !"".equalsIgnoreCase(event.getName())){
            opEvent.setName(event.getName());
        }

        if (Objects.nonNull(event.getLocation()) && !"".equalsIgnoreCase(event.getLocation())){
            opEvent.setLocation(event.getLocation());
        }

        if (Objects.nonNull(event.getNumberOfTickets()) && event.getNumberOfTickets() != 0){
            opEvent.setNumberOfTickets(event.getNumberOfTickets());
        }

        if (Objects.nonNull(event.getPrice()) && event.getPrice() != 0){
            opEvent.setPrice(event.getPrice());
        }

        if (Objects.nonNull(event.getStartTime())){
            opEvent.setStartTime(event.getStartTime());
        }

        if (Objects.nonNull(event.getEndTime())){
            opEvent.setEndTime(event.getEndTime());
        }

        if (Objects.nonNull(event.getEventDescription()) && !"".equalsIgnoreCase(event.getEventDescription())){
            opEvent.setEventDescription(event.getEventDescription());
        }

        return eventRepository.save(event);

    }
}
