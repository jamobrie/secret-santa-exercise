package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teammate {

    private String firstName;
    private String secondName;
    private String secretSanta;
    private Integer yearOfSecretSanta;

}
