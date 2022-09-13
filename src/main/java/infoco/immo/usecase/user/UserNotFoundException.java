package infoco.immo.usecase.user;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(){
        super("user not found");

    }

}
