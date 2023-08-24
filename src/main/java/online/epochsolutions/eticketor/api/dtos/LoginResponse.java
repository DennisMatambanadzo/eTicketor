package online.epochsolutions.eticketor.api.dtos;

import lombok.Data;

@Data
public class LoginResponse {

    private boolean success;
    private String jwt;
    private String failureReason;
}

