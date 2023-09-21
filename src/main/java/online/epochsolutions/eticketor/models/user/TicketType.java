package online.epochsolutions.eticketor.models.user;

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

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "current_count", nullable = false)
    private Long currentCount;

    @Column(name = "initial_count", nullable = false)
    private Long initialCount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}