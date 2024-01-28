package org.dikiwhy.parking.system.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserRequest {

    @NotBlank(message = "username must be filled in")
    private String username;
    @NotBlank(message = "username must be filled in")
    private String password;
}
