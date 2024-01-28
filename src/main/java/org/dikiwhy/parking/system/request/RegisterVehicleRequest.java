package org.dikiwhy.parking.system.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterVehicleRequest {

    @NotBlank(message = "this field must be filled in")
    private String policeNumber;
    @NotBlank(message = "this field must be filled in")
    private String vehicleType;
}
