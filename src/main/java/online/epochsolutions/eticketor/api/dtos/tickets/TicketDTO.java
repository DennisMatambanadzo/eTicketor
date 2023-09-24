package online.epochsolutions.eticketor.api.dtos.tickets;

import lombok.Data;

@Data
public class TicketDTO {
    private String name;
    private Double price;
    private Long count;
}
