package sspc.gob.mx.psr.validator;

import lombok.Data;

@Data
public class UserRequest  {
    private String user;
    private String pwd;
    private String token;
}
