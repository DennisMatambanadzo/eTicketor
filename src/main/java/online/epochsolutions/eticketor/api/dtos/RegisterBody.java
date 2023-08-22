package online.epochsolutions.eticketor.api.dtos;

import lombok.Data;

@Data
public class RegisterBody {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
