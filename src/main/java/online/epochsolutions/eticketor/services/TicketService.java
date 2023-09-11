package online.epochsolutions.eticketor.services;

import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.models.user.User;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketEventService ticketEvent;

    public TicketService( TicketEventService ticketEvent) {
        this.ticketEvent = ticketEvent;
    }

//Method to create Tickets
    public Ticket createTickets(String name, String section, User user,String host){
        return ticketEvent.createTicket(name,section,user,host);
    }

//    public List<Ticket> ticketList(User user){
//        return ticketRepository.findByUser(user);
//    }
}
