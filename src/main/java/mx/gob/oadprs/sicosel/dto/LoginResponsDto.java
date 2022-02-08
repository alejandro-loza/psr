package mx.gob.oadprs.sicosel.dto;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeNames = true, includePackage = false)
public class LoginResponsDto {
     public List<LoginDto> loginDto;
}
