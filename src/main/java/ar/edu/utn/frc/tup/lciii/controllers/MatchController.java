package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.match.ResponseMatchDTO;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseMatchDTO>> getMatchList() {
        List<Match> matchList = matchService.getMatchList();

        List<ResponseMatchDTO> responseMatchList = new ArrayList<>();
        for (Match match : matchList) {
            responseMatchList.add(modelMapper.map(match, ResponseMatchDTO.class));
        }

        return ResponseEntity.ok(responseMatchList);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseMatchDTO> getMatchById(@PathVariable Long id) {
//        ResponseMatchDTO responseMatchDTO = modelMapper.map(matchService.getMatchById(id), ResponseMatchDTO.class);
//        return ResponseEntity.ok(responseMatchDTO);
//    }

//    @PostMapping
//    public ResponseEntity<ResponseMatchDTO> createMatch(@RequestBody SaveMatchDTO saveMatchDTO) {
//        Match match = modelMapper.map(saveMatchDTO, Match.class);
//        ResponseMatchDTO responseMatchDTO = modelMapper.map(matchService.createMatch(match), ResponseMatchDTO.class);
//        return ResponseEntity.ok(responseMatchDTO);
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseMatchDTO> updateMatch(@PathVariable Long id, @RequestBody SaveMatchDTO saveMatchDTO) {
//        Match match = modelMapper.map(saveMatchDTO, Match.class);
//        match.setId(id);
//        ResponseMatchDTO responseMatchDTO = modelMapper.map(matchService.updateMatch(match), ResponseMatchDTO.class);
//        return ResponseEntity.ok(responseMatchDTO);
//    }

//    @DeleteMapping("/{id}")
//    public void deleteMatchById(@PathVariable Long id) {
//        matchService.deleteMatchById(id);
//    }
}
  