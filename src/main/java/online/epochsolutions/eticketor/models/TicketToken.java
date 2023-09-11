//package online.epochsolutions.eticketor.models;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import online.epochsolutions.eticketor.models.user.User;
//
//import java.sql.Timestamp;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "verification_token")
//public class TicketToken {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Lob
//    @Column(name = "token", nullable = false, unique = true)
//    private String token;
//
//    @Column(name = "created_timestamp", nullable = false)
//    private Timestamp createdTimestamp;
//
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "ticket_id", nullable = false, unique = true)
//    private Ticket ticket;
//
//}