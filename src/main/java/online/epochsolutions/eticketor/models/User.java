package online.epochsolutions.eticketor.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Valid
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Valid
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Valid
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<VerificationToken> verificationTokens = new ArrayList<>();

}