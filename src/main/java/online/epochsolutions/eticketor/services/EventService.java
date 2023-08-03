package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService{
    private EventRepository eventRepository;

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
}
