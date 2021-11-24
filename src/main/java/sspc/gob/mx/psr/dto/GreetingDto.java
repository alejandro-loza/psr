package sspc.gob.mx.psr.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GreetingDto {
    public GreetingDto(String message) {
        this.message = message;
    }

    String message;
}
