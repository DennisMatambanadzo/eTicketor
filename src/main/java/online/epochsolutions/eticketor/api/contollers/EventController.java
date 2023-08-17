package online.epochsolutions.eticketor.api.contollers;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.User;
import online.epochsolutions.eticketor.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("eTicketor/event")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/save")
    public ResponseEntity saveEvent(@RequestBody EventBody eventBody, @AuthenticationPrincipal User user){
        eventService.saveEvent(eventBody,user);
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

    @GetMapping("/events/user")
    public ResponseEntity<List<Event>> getEventsByUser(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(eventService.getEventByUser(user));

    }

    @DeleteMapping("/events/delete")
    public ResponseEntity deleteEvent(@RequestParam Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }


//TODO: Add Update and fix Delete functions

}
