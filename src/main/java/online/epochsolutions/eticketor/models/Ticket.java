package online.epochsolutions.eticketor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "ticket_number", nullable = false, unique = true)
//    private String ticketNumber;

    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time", nullable = false)
    private Instant endTime;

    @Column(name = "location", nullable = false)
    private String location;




//    @Lob
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(name = "qr_code", nullable = false)
//    private String qrCode;

    @Column(name = "price", nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "section", nullable = false)
    private Section section;



}

