package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.exceptions.UserNotAuthorized;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.user.TicketType;
import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.repositories.EventRepository;
import online.epochsolutions.eticketor.repositories.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService{
    private final EventRepository eventRepository;
    private final TicketTypeRepository ticketTypeRepository;



    public EventService(EventRepository eventRepository, TicketTypeRepository ticketTypeRepository) {
        this.eventRepository = eventRepository;
        this.ticketTypeRepository = ticketTypeRepository;
    }

    public void saveEvent(EventBody eventBody,User user) throws UserNotAuthorized {


        //TODO: Check user roles

        
        Event event = new Event();
        List<TicketType> ticketTypeList = new ArrayList<>();

        event.setName(eventBody.getName());
        event.setLocation(eventBody.getLocation());
        event.setStartTime(eventBody.getStartTime());
        event.setEndTime(eventBody.getEndTime());
        event.setEventDescription(eventBody.getEventDescription());
        event.setAgeLimit(eventBody.getAgeLimit());
        event.setTicketCount(eventBody.getInitialTicketCount());
        event.setUser(user);
        for (TicketType ticketType : eventBody.getTicketType()){
            ticketType.setEvent(event);
            ticketTypeList.add(ticketType);
        }

        eventRepository.save(event);
        ticketTypeRepository.saveAll(ticketTypeList);
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
