package online.epochsolutions.eticketor.api.contollers;

import online.epochsolutions.eticketor.api.dtos.TicketResponse;
import online.epochsolutions.eticketor.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eTicketor/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController( TicketService ticketService) {
        this.ticketService = ticketService;
    }

//    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/buyTicket")
    public ResponseEntity<TicketResponse> createTicket(@RequestParam String name, @RequestParam String section){
        var ticket = ticketService.createTickets(name,section);
        TicketResponse response = new TicketResponse();
        response.setName(ticket.getName());
        response.setStartTime(ticket.getStartTime());
        response.setEndTime(ticket.getEndTime());
        response.setLocation(ticket.getLocation());
        response.setSection(ticket.getSection());
        response.setPrice(ticket.getPrice());
        return ResponseEntity.ok(response);
    }
}
