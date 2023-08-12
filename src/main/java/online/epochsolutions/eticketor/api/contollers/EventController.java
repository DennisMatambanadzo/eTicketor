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

    @GetMapping("/getList")
    public ResponseEntity<List<Event>> getEvents(){
        return ResponseEntity.ok(eventService.getEvents());
    }

    @GetMapping("/getEvent")
    public ResponseEntity<Optional<Event>> getEvent(@RequestParam Long id){
        return ResponseEntity.ok(eventService.getEvent(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEvent(@RequestParam Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update")
    public ResponseEntity<EventResponse> updateEvent(@RequestParam Long id, @RequestBody Event event){
        eventService.updateEvent(id,event);
        EventResponse response = new EventResponse();
        response.setName(event.getName());
        response.setEventDescription(event.getEventDescription());;
        response.setStartTime(event.getStartTime());
        response.setEndTime(event.getEndTime());
        response.setAgeLimit(event.getAgeLimit());
        response.setLocation(event.getLocation());
        response.setNumberOfTickets(event.getNumberOfTickets());
        response.setPrice(event.getPrice());
        return ResponseEntity.ok(response);
    }
//TODO: Add Update and fix Delete functions

}
