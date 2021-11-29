package sspc.gob.mx.psr.dto;

import lombok.Getter;
import lombok.Setter;
import sspc.gob.mx.psr.model.Sentenced;
import java.time.LocalDateTime;


@Getter
@Setter
public class SentencedDto {

    Long id;

    String name;

    String firstLastName;

    String secondLastName;

    String nationality;

    String curp;

    String maritalStatus;

    String alias;

    String otherNames;

    Long birthDate;

    String occupation;

    String gender;

    String ethnicity;

    String schooling;

    Long homePhone;

    Long mobilePhone;

    String  email;

    LocalDateTime dateCreated;

    public SentencedDto(Sentenced sentenced){
        this.id = sentenced.getId();
        this.name = sentenced.getName();
        this.firstLastName = sentenced.getFirstName();
        this.secondLastName = sentenced.getLastName();
        this.nationality = sentenced.getNationality();
        this.curp = sentenced.getCurp();
        this.maritalStatus = sentenced.getMaritalStatus();
        this.alias = sentenced.getAlias();
        this.otherNames = sentenced.getOtherNames();
        this.birthDate = sentenced.getBirthDate().getTime();
        this.occupation = sentenced.getOccupation();
        this.gender = sentenced.getGender();
        this.ethnicity = sentenced.getEthnicity();
        this.schooling = sentenced.getSchooling();
        this.homePhone = sentenced.getHomePhone();
        this.mobilePhone = sentenced.getMobilePhone();
        this.email = sentenced.getEmail();
        this.dateCreated = sentenced.getDateCreated();
    }

}
