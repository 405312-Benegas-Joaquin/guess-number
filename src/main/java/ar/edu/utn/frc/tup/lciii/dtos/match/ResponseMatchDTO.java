package ar.edu.utn.frc.tup.lciii.dtos.match;

import ar.edu.utn.frc.tup.lciii.models.Difficulty;
import ar.edu.utn.frc.tup.lciii.models.NumberToGuessCondition;
import ar.edu.utn.frc.tup.lciii.models.Status;
import ar.edu.utn.frc.tup.lciii.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMatchDTO {
    private Long id;
//    private User user;
//    private Difficulty difficulty;
//    @JsonProperty("number_to_guess")
//    private Integer numberToGuess;
    @JsonProperty("remaining_attempts")
    private Integer remainingAttempts;
    @JsonProperty("number_to_guess_condition")
    private NumberToGuessCondition numberToGuessCondition;
    private Status status;
}
