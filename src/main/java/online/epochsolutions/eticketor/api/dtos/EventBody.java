package online.epochsolutions.eticketor.api.dtos;

import lombok.Data;

import java.time.Instant;

@Data
public class EventBody {
     private String location;
     private Long numberOfTickets;
     private Instant startTime;
     private Instant endTime;
     private String eventDescription;
     private Integer ageLimit;
     private Double price;
     private String name;
}
