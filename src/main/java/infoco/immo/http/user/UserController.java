package infoco.immo.http.user;


import infoco.immo.core.User;
import infoco.immo.http.error.ErrorResponse;
import infoco.immo.http.user.dto.LoginUserDTO;
import infoco.immo.http.user.response.UserResponse;
import infoco.immo.usecase.user.Token;
import infoco.immo.http.user.dto.CreateUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping( "/api/user")
@Slf4j
@Validated
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping(value = "/{uuid}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String uuid) {
        User user = userService.get(UUID.fromString(uuid));
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUserByToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        String token = authorizationHeader.split(" ")[1];
        User user = userService.getByToken(token);
        return ResponseEntity.ok(new UserResponse(user));
    }






}
