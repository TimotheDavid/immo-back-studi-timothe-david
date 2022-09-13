package infoco.immo.http.error;

import infoco.immo.http.user.HttpExceptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private String message;
    private String cause;
    private int code;
    public ErrorResponse(HttpExceptions http){
        this.message = http.getMessage();
        this.code = http.getCode();
        this.cause = http.getCause().getMessage();
    }


}

