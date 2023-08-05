package online.epochsolutions.eticketor.api.contollers;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.services.EventService;
import online.epochsolutions.eticketor.services.TicketService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/save")
    public ResponseEntity saveEvent(@RequestBody EventBody eventBody){
        //event creation
        eventService.saveEvent(eventBody);
        /*create tickets as soon as the event is created
        */
        for (int i = 0; i< eventBody.getNumberOfTickets(); i++){
            ticketService.createTickets(eventBody.getNumberOfTickets(),
                    eventBody.getStartTime(),eventBody.getEndTime(),eventBody.getPrice(),
                    eventBody.getName(),eventBody.getLocation());
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEvents(){
        return ResponseEntity.ok(eventService.getEvents());
    }

    @GetMapping("/events/")
    public ResponseEntity<Optional<Event>> getEvent(@RequestParam Long id){
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @DeleteMapping("/events/delete")
    public ResponseEntity deleteEvent(@RequestParam Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
//TODO: Add Update and fix Delete functions

}
