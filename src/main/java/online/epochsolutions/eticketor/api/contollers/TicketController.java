package online.epochsolutions.eticketor.api.contollers;

import online.epochsolutions.eticketor.api.dtos.TicketResponse;
import online.epochsolutions.eticketor.exceptions.EmailFailureException;
import online.epochsolutions.eticketor.models.Ticket;
import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eTicketor/ticket")
public class TicketController {



    private final TicketService ticketService;


    public TicketController( TicketService ticketService) {
        this.ticketService = ticketService;
    }



    @PostMapping("/buyTicket")
    public ResponseEntity<TicketResponse> createTicket(@RequestParam String name, @RequestParam String section,@RequestParam String host,@AuthenticationPrincipal User user) throws EmailFailureException {
        var  ticket = ticketService.createTickets(name,section,user,host);
        TicketResponse response = new TicketResponse();
        response.setName(ticket.getName());
        response.setStartTime(ticket.getStartTime());
        response.setEndTime(ticket.getEndTime());
        response.setLocation(ticket.getLocation());
        response.setSection(ticket.getSection());
        response.setPrice(ticket.getPrice());
        return ResponseEntity.ok(response);
    }

//    @GetMapping("ticketList")
//    public ResponseEntity<List<Ticket>> getTicketList(@AuthenticationPrincipal User user){
//
//        var tickets = ticketService.ticketList(user);
//
//        return ResponseEntity.ok(tickets);
//    }

    @GetMapping("viewTicket")
    public ResponseEntity<TicketResponse> getTicket(){
        var response = new TicketResponse();

        return ResponseEntity.ok(response);
    }

}
