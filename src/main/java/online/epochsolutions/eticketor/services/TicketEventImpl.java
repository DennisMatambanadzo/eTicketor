package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.Section;
import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.repositories.EventRepository;
import online.epochsolutions.eticketor.repositories.TicketRepository;
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

    public Ticket createTicket(String name) {
        Optional<Event> opEvent = eventRepository.findByNameIgnoreCase(name);
        if (opEvent.isPresent()){
            Event event = opEvent.get();
            Ticket ticket = new Ticket();
            ticket.setName(event.getName());
            ticket.setEndTime(event.getEndTime());
            ticket.setLocation(event.getLocation());
            ticket.setSection(Section.STANDARD);
            ticket.setPrice(event.getPrice());
            ticket.setStartTime(event.getStartTime());
            ticketRepository.save(ticket);
            return ticket;
        }else{
            return new Ticket();
        }

    }
}
