package infoco.immo.http.user;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Slf4j
@Getter
public class HttpExceptions extends Exception{
    private String message;
    private Throwable cause;
    private String causeMessage;
    private int code;

    public HttpExceptions(String message, String  cause,int code) {
        super(message);
        this.message = message;
        this.cause = new Throwable(cause);
        this.code = code;
    }

    public int getCode() {
        return this.code;

    }

    public String toString() {
        return this.message + ":" + this.cause.getMessage();

    }
}
