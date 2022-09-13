package infoco.immo.http.tenant.dto;


import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateTenantDTO {
    private  String  civility;
    private  String firstName;
    private  String name;
    private  String birthDate;

    private String birthPlace;
    private  String email;
    private  String secondEmail;
    private  String phone;
    private String id;

}
