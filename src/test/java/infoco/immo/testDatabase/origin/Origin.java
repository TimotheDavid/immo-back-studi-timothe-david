package infoco.immo.testDatabase.origin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Origin {
    private UUID uuid;
    private String origin;
}
