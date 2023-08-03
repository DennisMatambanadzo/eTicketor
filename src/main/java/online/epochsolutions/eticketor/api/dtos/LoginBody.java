package online.epochsolutions.eticketor.api.dtos;

import lombok.Data;

@Data
public class LoginBody {
    private String email;
    private String password;
}
