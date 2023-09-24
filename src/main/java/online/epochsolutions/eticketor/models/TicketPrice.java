package online.epochsolutions.eticketor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ticket_price")
public class TicketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "bronze", nullable = false)
    private Double bronze;

    @Column(name = "silver", nullable = false)
    private Double silver;

    @Column(name = "gold", nullable = false)
    private Double gold;


    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "event_id", nullable = false, unique = true)
    private Event event;

}