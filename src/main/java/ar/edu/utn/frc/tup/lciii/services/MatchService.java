package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.match.PlayMatchDTO;
import ar.edu.utn.frc.tup.lciii.models.Difficulty;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MatchService {
    List<Match> getMatchList();
    Match getMatchById(Long id);
    Match createMatch(User user, Difficulty difficulty);
    Match updateMatch(Match match, Integer numberToPlay);
//    void deleteMatchById(Long id);
}
  