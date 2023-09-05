package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.repositories.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private TicketEventService ticketEvent;

    public TicketService(TicketRepository ticketRepository, TicketEventService ticketEvent) {
        this.ticketRepository = ticketRepository;
        this.ticketEvent = ticketEvent;
    }

//Method to create Tickets
    public Ticket createTickets(String name, String section){
        return ticketEvent.createTicket(name,section);
    }
}
