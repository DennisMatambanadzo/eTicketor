package online.epochsolutions.eticketor.api.contollers;

import online.epochsolutions.eticketor.api.dtos.DeleteResponse;
import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.api.dtos.EventResponse;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.user.User;
import online.epochsolutions.eticketor.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("eTicketor/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/save")
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventBody body, @AuthenticationPrincipal User user){
        eventService.saveEvent(body, user);
        EventResponse response = new EventResponse();
        response.setEventDescription(body.getEventDescription());
        response.setName(body.getName());
        response.setLocation(body.getLocation());
        response.setEndTime(body.getEndTime());
        response.setStartTime(body.getStartTime());
        response.setAgeLimit(body.getAgeLimit());
//        response.setPrice(body.getPrice());
//        response.setNumberOfTickets(body.getInitialTicketCount());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/events/user")
    public ResponseEntity<List<Event>> getEventsByUser(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(eventService.getEventsByUser(user));
    }


    @GetMapping("/getList")
    public ResponseEntity<List<Event>> getEvents(){
        return ResponseEntity.ok(eventService.getEvents());
    }

//    @GetMapping("/getEvent/{id}")
//    public ResponseEntity<Optional<Event>> getEvent(@RequestParam Long id){
//        return ResponseEntity.ok(eventService.getEvent(id));
//    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> deleteEvent(@PathVariable Long id){
        DeleteResponse response = new DeleteResponse();
        if (eventService.deleteEvent(id) == 1){
            response.setMessage("Event deleted");
        }else {
            response.setMessage("Delete failed! Event does not exist");
        }
        return ResponseEntity.ok(response);

    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<EventResponse> updateEvent(@PathVariable Long id,@AuthenticationPrincipal User user, @RequestBody EventBody body){
//        eventService.updateEvent(body, user, id);
//        EventResponse response = new EventResponse();
//        response.setName(body.getName());
//        response.setEventDescription(body.getEventDescription());;
//        response.setStartTime(body.getStartTime());
//        response.setEndTime(body.getEndTime());
//        response.setAgeLimit(body.getAgeLimit());
//        response.setLocation(body.getLocation());
//        response.setNumberOfTickets(body.getInitialTicketCount());
//        response.setPrice(body.getPrice());
//        return ResponseEntity.ok(response);
//    }


}
