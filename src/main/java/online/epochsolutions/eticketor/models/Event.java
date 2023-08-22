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

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "slots", nullable = false)
    private Long numberOfTickets;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Lob
    @Column(name = "event_description", nullable = false)
    private String eventDescription;

    @Column(name = "age_limit")
    private Integer ageLimit;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "name", nullable = false)
    private String name;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

}