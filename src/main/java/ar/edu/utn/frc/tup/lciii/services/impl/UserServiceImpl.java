package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.match.PlayMatchDTO;
import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.UserEntity;
import ar.edu.utn.frc.tup.lciii.models.*;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.UserJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import ar.edu.utn.frc.tup.lciii.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private MatchService matchService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<User> getUserList() {
        List<UserEntity> userEntities = userJpaRepository.findAll();

        List<User> userList = new ArrayList<>();

        for (UserEntity userEntity : userEntities) {
            userList.add(modelMapper.map(userEntity, User.class));
        }

        return userList;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userJpaRepository.getReferenceById(id);
        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public User createUser(User user) {
        Optional<UserEntity> userEntityFound = userJpaRepository.findByEmail(user.getEmail());

        if (userEntityFound.isPresent()) {
            return null;
        }

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity userEntitySaved = userJpaRepository.save(userEntity);

        return modelMapper.map(userEntitySaved, User.class);
    }

    @Override
    public Match createUserMatch(Long userId, Difficulty difficulty) {
        Optional<UserEntity> userEntity = userJpaRepository.findById(userId);

        if (userEntity.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }

        User user = modelMapper.map(userEntity, User.class);

        return matchService.createMatch(user, difficulty);
    }

    @Override
    public Match playUserMatch(Long userId, Long matchId, Integer numberToPlay) {
        Match match = matchService.getMatchById(matchId);
        if (!match.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User does not own this match");
        }

        return matchService.updateMatch(match, numberToPlay);
    }

//    @Override
//    public User updateUser(User user) {
//        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
//        UserEntity userEntitySaved = userJpaRepository.save(userEntity);
//
//        return modelMapper.map(userEntitySaved, User.class);
//    }

//    @Override
//    public void deleteUserById(Long id) {
//        userJpaRepository.deleteById(id);
//    }
}
  