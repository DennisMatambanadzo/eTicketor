package online.epochsolutions.eticketor.services;

import lombok.RequiredArgsConstructor;
import online.epochsolutions.eticketor.api.auth.repositories.UserRepository;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.Section;
import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.models.user.Role;
import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.repositories.EventRepository;
import online.epochsolutions.eticketor.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class TicketEventService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    public Ticket createTicket(String name, String section, User user,String host) {
        var OpHost = findHost(host);
        var opEvent = eventRepository.findByUserAndNameIgnoreCase(OpHost,name);
        CREATE_TICKET: if (opEvent.isPresent()){
            Event event = opEvent.get();
            Ticket ticket = new Ticket();
            ticket.setName(event.getName());
            ticket.setEndTime(event.getEndTime());
            ticket.setLocation(event.getLocation());
            ticket.setSection(Section.valueOf(section));
            ticket.setPrice(event.getPrice());
            ticket.setStartTime(event.getStartTime());
//            ticket.setUser(user);
            event.setRemainingTickets(event.getRemainingTickets()-1);
            if (event.getRemainingTickets()> -1) {
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

    public User findHost(String host){
        return userRepository.findByRoleAndFirstNameIgnoreCase(Role.HOST,host);
    }

}
