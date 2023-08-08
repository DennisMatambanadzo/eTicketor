package online.epochsolutions.eticketor.api.contollers;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.api.dtos.EventResponse;
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
    public ResponseEntity<EventResponse> saveEvent(@RequestBody EventBody eventBody){
        //event creation
        eventService.saveEvent(eventBody);
        EventResponse response = new EventResponse();
        response.setName(eventBody.getName());
        response.setEventDescription(eventBody.getEventDescription());;
        response.setStartTime(eventBody.getStartTime());
        response.setEndTime(eventBody.getEndTime());
        response.setAgeLimit(eventBody.getAgeLimit());
        response.setLocation(eventBody.getLocation());
        response.setNumberOfTickets(eventBody.getNumberOfTickets());
        response.setPrice(eventBody.getPrice());

        return ResponseEntity.ok(response);
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
