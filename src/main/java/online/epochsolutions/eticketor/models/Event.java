package online.epochsolutions.eticketor.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import online.epochsolutions.eticketor.models.user.User;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

//    @Column(name = "remaining_tickets", nullable = false)
//    private Long remainingTickets;
//
    @Column(name = "slots", nullable = false)
    private Long TicketCount;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Lob
    @Column(name = "event_description", nullable = false)
    private String eventDescription;

    @Column(name = "age_limit")
    private Integer ageLimit;

//    @Column(name = "price", nullable = false)
//    private Double price;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}