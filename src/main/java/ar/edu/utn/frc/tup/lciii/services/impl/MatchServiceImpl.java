package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.match.PlayMatchDTO;
import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.UserEntity;
import ar.edu.utn.frc.tup.lciii.models.*;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Match> getMatchList() {
        List<MatchEntity> matchEntities = matchJpaRepository.findAll();

        List<Match> matchList = new ArrayList<>();

        for (MatchEntity matchEntity : matchEntities) {
            matchList.add(modelMapper.map(matchEntity, Match.class));
        }

        return matchList;
    }

    @Override
    public Match getMatchById(Long id) {
        Optional<MatchEntity> matchEntityOptional = matchJpaRepository.findById(id);

        if (matchEntityOptional.isEmpty()) {
            throw new EntityNotFoundException("Match not found");
        }

        return modelMapper.map(matchEntityOptional.get(), Match.class);
    }

    @Override
    public Match createMatch(User user, @RequestBody Difficulty difficulty) {
        Random random = new Random();

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setUser(userEntity);
        matchEntity.setDifficulty(difficulty);
        matchEntity.setStatus(Status.STARTED);
        switch (difficulty) {
            case EASY -> matchEntity.setRemainingAttempts(10);
            case MEDIUM -> matchEntity.setRemainingAttempts(8);
            case HARD -> matchEntity.setRemainingAttempts(5);
        }
        matchEntity.setNumberToGuess(random.nextInt(100) + 1);
        matchEntity.setCreatedAt(LocalDateTime.now());
        matchEntity.setUpdatedAt(LocalDateTime.now());

        MatchEntity matchEntitySaved = matchJpaRepository.save(matchEntity);

        return modelMapper.map(matchEntitySaved, Match.class);
    }

    @Override
    public Match updateMatch(Match match, Integer numberToPlay) {
//        Optional<MatchEntity> matchEntityOptional = matchJpaRepository.findById(matchId);
//
//        if (matchEntityOptional.isEmpty()) {
//            throw new EntityNotFoundException("Match not found");
//        }
//
//        MatchEntity matchEntity = matchEntityOptional.get();
//
//        if (!matchEntity.getUserEntity().getId().equals(userId)) {
//            throw new IllegalArgumentException("User does not own this match");
//        }

        if (match.getRemainingAttempts().equals(0)) {
            throw new IllegalArgumentException("Match has no remaining attempts");
        }

        if (match.getStatus().equals(Status.FINISHED)) {
            throw new IllegalArgumentException("Match already finished");
        }

        if (match.getNumberToGuess().equals(numberToPlay)) {
            match.setStatus(Status.FINISHED);
            match.setNumberToGuessCondition(NumberToGuessCondition.GUESSED);
        } else {
            if (match.getNumberToGuess() > numberToPlay) {
                match.setNumberToGuessCondition(NumberToGuessCondition.MAJOR);
            } else {
                match.setNumberToGuessCondition(NumberToGuessCondition.MINOR);
            }

            match.setRemainingAttempts(match.getRemainingAttempts() - 1);

            if (match.getRemainingAttempts().equals(0)) {
                match.setStatus(Status.FINISHED);
            }
        }

        MatchEntity matchEntity = modelMapper.map(match, MatchEntity.class);
        matchEntity.setUpdatedAt(LocalDateTime.now());
        MatchEntity matchEntitySaved = matchJpaRepository.save(matchEntity);

        return modelMapper.map(matchEntitySaved, Match.class);
    }

//    @Override
//    public void deleteMatchById(Long id) {
//        matchJpaRepository.deleteById(id);
//    }
}
  