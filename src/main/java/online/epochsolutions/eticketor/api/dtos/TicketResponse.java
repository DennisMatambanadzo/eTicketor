package online.epochsolutions.eticketor.api.dtos;

import lombok.Data;
import online.epochsolutions.eticketor.models.Section;

import java.time.Instant;

@Data
public class TicketResponse {

    private String name;
    private Instant startTime;
    private Instant endTime;
    private String location;
    private Section section;
    private Double price;
}
