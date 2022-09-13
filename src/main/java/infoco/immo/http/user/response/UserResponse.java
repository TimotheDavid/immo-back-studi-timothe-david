package infoco.immo.http.user.response;

import infoco.immo.core.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class UserResponse {
    private UUID id;
    private  String  name;
    private  String email;

    public UserResponse(User user){
        this.name = user.getName();
        this.id = user.getId();
        this.email = user.getEmail();
    }
}
