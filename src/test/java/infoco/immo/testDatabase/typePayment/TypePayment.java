package infoco.immo.testDatabase.typePayment;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class TypePayment {
    private UUID uuid;
    private String type;
    private SensPayment sensPayment;
}
