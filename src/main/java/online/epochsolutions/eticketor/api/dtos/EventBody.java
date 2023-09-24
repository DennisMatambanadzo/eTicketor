package online.epochsolutions.eticketor.api.dtos;

import lombok.Data;
import online.epochsolutions.eticketor.api.dtos.tickets.TicketDTO;

import java.time.Instant;

@Data
public class EventBody {
//     This object carries for the Event and TicketType entities


    private String location;
    private String name;
    private Instant startTime;
    private Instant endTime;
    private String eventDescription;
    private Integer ageLimit;

    private TicketDTO goldTicket;
    private TicketDTO silverTicket;
    private TicketDTO bronzeTicket;
}
