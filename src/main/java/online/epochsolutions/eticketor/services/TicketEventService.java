package online.epochsolutions.eticketor.services;

import lombok.RequiredArgsConstructor;
import online.epochsolutions.eticketor.api.auth.repositories.UserRepository;
import online.epochsolutions.eticketor.api.auth.services.JWTService;
import online.epochsolutions.eticketor.exceptions.EmailFailureException;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.Section;
import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.models.user.Role;
import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.repositories.EventRepository;
import online.epochsolutions.eticketor.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
@RequiredArgsConstructor
public class TicketEventService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final EmailService emailService;


    public Ticket createTicket(String name, String section, User user) throws EmailFailureException {



        var opEvent = eventRepository.findByNameIgnoreCase(name);
        if (opEvent.isPresent()){
            return new Ticket();
        }
        Event event = opEvent.get();

//        @Red
//        if (event.getRemainingTickets() == 0) {
//            return new Ticket();
//        }


        Ticket ticket = new Ticket();

        ticket.setName(event.getName());
        ticket.setEndTime(event.getEndTime());
        ticket.setLocation(event.getLocation());
        ticket.setSection(Section.valueOf(section));
//        ticket.setPrice(event.getPrice());
        ticket.setStartTime(event.getStartTime());
        ticket.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        ticketRepository.save(ticket);
//        event.setRemainingTickets(event.getRemainingTickets()-1);
        eventRepository.save(event);

        emailService.sendTicketPurchaseNotificationEmail(ticket,user);

        return ticket;

    }

//    private TicketToken createTicketToken(Ticket ticket){
//        var token = new TicketToken();
//        token.setToken(jwtService.generateJWT(ticket));
//        token.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
////        token.setUser(ticket.getUser());
//        token.setTicket(ticket);
//
//        return token;
//    }
    public User findHost(String host){
        return userRepository.findByRoleAndFirstNameIgnoreCase(Role.HOST,host);
    }



}
