package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private TicketRepository ticketRepository;
    private TicketEventService ticketEvent;

    public TicketService(TicketRepository ticketRepository, TicketEventService ticketEvent) {
        this.ticketRepository = ticketRepository;
        this.ticketEvent = ticketEvent;
    }

//Method to create Tickets
    public Ticket createTickets(String name, String section, User user,String host){
        return ticketEvent.createTicket(name,section,user,host);
    }

    public List<Ticket> ticketList(User user){
        return ticketRepository.findByUser(user);
    }
}
