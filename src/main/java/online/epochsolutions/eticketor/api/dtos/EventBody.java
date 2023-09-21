package online.epochsolutions.eticketor.api.dtos;

import lombok.Data;
import online.epochsolutions.eticketor.models.user.TicketType;

import java.time.Instant;
import java.util.ArrayList;

@Data
public class EventBody {
//     This object carries for the Event and TicketType entities

     private String location;
     private Instant startTime;
     private Instant endTime;
     private String eventDescription;
     private Integer ageLimit;
     private String name;


     private Double price;
     private Long initialTicketCount;
     private ArrayList<TicketType> ticketType;
}
