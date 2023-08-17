package online.epochsolutions.eticketor.api.contollers;

import online.epochsolutions.eticketor.api.dtos.DeleteResponse;
import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.api.dtos.EventResponse;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.User;
import online.epochsolutions.eticketor.services.EventService;
import online.epochsolutions.eticketor.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("eTicketor/event")
public class EventController {

    private EventService eventService;
    private TicketService ticketService;

    public EventController(EventService eventService, TicketService ticketService) {
        this.eventService = eventService;
        this.ticketService = ticketService;
    }




//TODO: Add Update and fix Delete functions

}
