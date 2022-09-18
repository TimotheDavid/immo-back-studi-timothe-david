package infoco.immo.http.auth;

import infoco.immo.core.User;
import infoco.immo.http.error.ErrorResponse;
import infoco.immo.http.user.HttpExceptions;
import infoco.immo.http.user.UserService;
import infoco.immo.http.user.dto.LoginUserDTO;
import infoco.immo.usecase.user.Token;
import infoco.immo.http.user.dto.CreateUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity create(@Valid  @RequestBody  CreateUserDTO createUserDTO) {
        User user = User.builder()
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .name(createUserDTO.getName())
                .build();
        userService.create(user);
        return new  ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity getlogin( @Valid @RequestBody LoginUserDTO userDto){
        User user = User.builder().email(userDto.getEmail()).password(userDto.getPassword()).build();
        Token token;
        try {
            token = userService.login(user);
        } catch (HttpExceptions e) {

            return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e));
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }



}
