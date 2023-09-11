package online.epochsolutions.eticketor.services;

import lombok.RequiredArgsConstructor;
import online.epochsolutions.eticketor.api.auth.services.JWTService;
import online.epochsolutions.eticketor.exceptions.EmailFailureException;
import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.repositories.TicketRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketEventService ticketEvent;


//Method to create Tickets
    public Ticket createTickets(String name, String section, User user,String host) throws EmailFailureException {
        return ticketEvent.createTicket(name,section,user,host);
    }

//    public List<Ticket> ticketList(User user){
//        return ticketRepository.findByUser(user);
//    }
}
