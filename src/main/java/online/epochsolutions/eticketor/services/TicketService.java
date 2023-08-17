package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.repositories.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private TicketEventImpl ticketEvent;

    public TicketService(TicketRepository ticketRepository, TicketEventImpl ticketEvent) {
        this.ticketRepository = ticketRepository;
        this.ticketEvent = ticketEvent;
    }

//Method to create Tickets
    public Ticket createTickets(String name){
        return ticketEvent.createTicket(name);
    }
}
