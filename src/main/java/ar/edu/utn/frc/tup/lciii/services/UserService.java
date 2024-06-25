package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.match.PlayMatchDTO;
import ar.edu.utn.frc.tup.lciii.models.Difficulty;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUserList();
    User getUserById(Long id);
    User createUser(User user);
    Match createUserMatch(Long userId, Difficulty difficulty);
    Match playUserMatch(Long userId, Long matchId, Integer numberToPlay);
//    User updateUser(User user);
//    void deleteUserById(Long id);
}
  