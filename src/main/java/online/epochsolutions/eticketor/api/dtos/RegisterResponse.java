package online.epochsolutions.eticketor.api.dtos;

import lombok.Data;

@Data
public class RegisterResponse {
    private String email;
    private String firstName;
    private String message;
}
