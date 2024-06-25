package ar.edu.utn.frc.tup.lciii.entities;

import ar.edu.utn.frc.tup.lciii.models.Difficulty;
import ar.edu.utn.frc.tup.lciii.models.NumberToGuessCondition;
import ar.edu.utn.frc.tup.lciii.models.Status;
import ar.edu.utn.frc.tup.lciii.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column(name = "number_to_guess")
    private Integer numberToGuess;

    @Column(name = "remaining_attempts")
    private Integer remainingAttempts;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "number_to_guess_condition")
    @Enumerated(EnumType.STRING)
    private NumberToGuessCondition numberToGuessCondition;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}
  