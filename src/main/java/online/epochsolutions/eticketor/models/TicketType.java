package online.epochsolutions.eticketor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.epochsolutions.eticketor.models.Event;
import online.epochsolutions.eticketor.models.Section;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ticket_type")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "bronze", nullable = false)
    private Long bronze;

    @Column(name = "silver", nullable = false)
    private Long silver;

    @Column(name = "gold", nullable = false)
    private Long gold;

}