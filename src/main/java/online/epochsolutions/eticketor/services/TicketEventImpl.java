package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.Section;
import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.repositories.EventRepository;
import online.epochsolutions.eticketor.repositories.TicketRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TicketEventImpl {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

    public TicketEventImpl(TicketRepository ticketRepository, EventRepository eventRepository) {
        this.ticketRepository = ticketRepository;
        this.eventRepository = eventRepository;
    }

//    @Cacheable(cacheNames = "tickets", value = "#event.numberOfTickets")
    public Ticket createTicket(String name,String section) {
        Optional<Event> opEvent = eventRepository.findByNameIgnoreCase(name);
        CREATE_TICKET: if (opEvent.isPresent()){
            Event event = opEvent.get();
            Ticket ticket = new Ticket();
            ticket.setName(event.getName());
            ticket.setEndTime(event.getEndTime());
            ticket.setLocation(event.getLocation());
            ticket.setSection(Section.valueOf(section));
            ticket.setPrice(event.getPrice());
            ticket.setStartTime(event.getStartTime());
            event.setNumberOfTickets(event.getNumberOfTickets()-1);
            if (event.getNumberOfTickets()> -1) {
                eventRepository.save(event);
            }else {
                break CREATE_TICKET;

            }
            //TODO: WRITE LOGIC TO CHECK IF THE NUMBER OF TICKETS IS NOT ZERO
            ticketRepository.save(ticket);
            return ticket;
        }else{
            return new Ticket();
        }
        return new Ticket();
    }
}
