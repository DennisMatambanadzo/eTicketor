package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.exceptions.UserNotAuthorized;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.TicketType;
import online.epochsolutions.eticketor.models.TicketPrice;
import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.repositories.EventRepository;
import online.epochsolutions.eticketor.repositories.TicketPriceRepository;
import online.epochsolutions.eticketor.repositories.TicketTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EventService{
    private final EventRepository eventRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final TicketPriceRepository ticketPriceRepository;



    public EventService(EventRepository eventRepository, TicketTypeRepository ticketTypeRepository, TicketPriceRepository ticketPriceRepository) {
        this.eventRepository = eventRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.ticketPriceRepository = ticketPriceRepository;
    }

    public void saveEvent(EventBody eventBody,User user) throws UserNotAuthorized {


        //TODO: Check user roles

        
        Event event = new Event();
        event.setEventDescription(eventBody.getEventDescription());
        event.setName(eventBody.getName());
        event.setStartTime(eventBody.getStartTime());
        event.setEndTime(eventBody.getEndTime());
        event.setAgeLimit(eventBody.getAgeLimit());
        event.setLocation(eventBody.getLocation());
        event.setUser(user);

        TicketType ticketType = new TicketType();
        ticketType.setEvent(event);
        ticketType.setBronze(eventBody.getBronzeTicket().getCount());
        ticketType.setGold(eventBody.getGoldTicket().getCount());
        ticketType.setSilver(eventBody.getSilverTicket().getCount());

        TicketPrice ticketPrice = new TicketPrice();
        ticketPrice.setEvent(event);
        ticketPrice.setBronze(eventBody.getBronzeTicket().getPrice());
        ticketPrice.setSilver(eventBody.getSilverTicket().getPrice());
        ticketPrice.setGold(eventBody.getGoldTicket().getPrice());
        eventRepository.save(event);
        ticketTypeRepository.save(ticketType);
        ticketPriceRepository.save(ticketPrice);
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
