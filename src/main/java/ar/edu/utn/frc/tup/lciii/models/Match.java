package ar.edu.utn.frc.tup.lciii.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private Long id;
    private User user;
    private Difficulty difficulty;
    private Integer numberToGuess;
    private Integer remainingAttempts;
    private NumberToGuessCondition numberToGuessCondition;
    private Status status;
}
  