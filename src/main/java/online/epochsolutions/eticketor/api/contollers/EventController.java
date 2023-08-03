package online.epochsolutions.eticketor.api.contollers;

import online.epochsolutions.eticketor.api.dtos.EventBody;
import online.epochsolutions.eticketor.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("eTicketor/event")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/save")
    public ResponseEntity saveEvent(@RequestBody EventBody eventBody){
        eventService.saveEvent(eventBody);
        return ResponseEntity.ok().build();
    }
}
