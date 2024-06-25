package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.match.PlayMatchDTO;
import ar.edu.utn.frc.tup.lciii.dtos.match.ResponseMatchDTO;
import ar.edu.utn.frc.tup.lciii.dtos.user.ResponseUserDTO;
import ar.edu.utn.frc.tup.lciii.dtos.user.SaveUserDTO;
import ar.edu.utn.frc.tup.lciii.models.Difficulty;
import ar.edu.utn.frc.tup.lciii.models.User;
import ar.edu.utn.frc.tup.lciii.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> getUserList() {
        List<User> userList = userService.getUserList();

        List<ResponseUserDTO> responseUserList = new ArrayList<>();
        for (User user : userList) {
            responseUserList.add(modelMapper.map(user, ResponseUserDTO.class));
        }

        return ResponseEntity.ok(responseUserList);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable Long id) {
//        ResponseUserDTO responseUserDTO = modelMapper.map(userService.getUserById(id), ResponseUserDTO.class);
//        return ResponseEntity.ok(responseUserDTO);
//    }

    @PostMapping
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody SaveUserDTO saveUserDTO) {
        User user = modelMapper.map(saveUserDTO, User.class);
        ResponseUserDTO responseUserDTO = modelMapper.map(userService.createUser(user), ResponseUserDTO.class);
        return ResponseEntity.ok(responseUserDTO);
    }

    @PostMapping("/{userId}/matches/{difficulty}")
    public ResponseEntity<ResponseMatchDTO> createUserMatch(@PathVariable Long userId, @PathVariable Difficulty difficulty) {
        ResponseMatchDTO responseMatchDTO = modelMapper.map(userService.createUserMatch(userId, difficulty), ResponseMatchDTO.class);
        return ResponseEntity.ok(responseMatchDTO);
    }

    @PutMapping("/{userId}/matches/{matchId}")
    public ResponseEntity<ResponseMatchDTO> playUserMatch(@PathVariable Long userId, @PathVariable Long matchId, @RequestBody PlayMatchDTO playMatchDTO) {
        ResponseMatchDTO responseMatchDTO = modelMapper.map(userService.playUserMatch(userId, matchId, playMatchDTO.getNumber()), ResponseMatchDTO.class);
        return ResponseEntity.ok(responseMatchDTO);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable Long id, @RequestBody SaveUserDTO saveUserDTO) {
//        User user = modelMapper.map(saveUserDTO, User.class);
//        user.setId(id);
//        ResponseUserDTO responseUserDTO = modelMapper.map(userService.updateUser(user), ResponseUserDTO.class);
//        return ResponseEntity.ok(responseUserDTO);
//    }

//    @DeleteMapping("/{id}")
//    public void deleteUserById(@PathVariable Long id) {
//        userService.deleteUserById(id);
//    }
}
  