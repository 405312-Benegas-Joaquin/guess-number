package ar.edu.utn.frc.tup.lciii.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO {
    private Long id;

    @JsonProperty("user_name")
    private String userName;

    @Email
    private String email;

    private String password;
}
