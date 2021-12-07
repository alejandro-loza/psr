package sspc.gob.mx.psr.utils;

import lombok.Getter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import sspc.gob.mx.psr.enums.Sexo;
import java.util.Arrays;

@Getter
public class Folio {
    private static final int EXTRA_QUOTIENT = 7;
    private String nameEncode;
    private int[] birthDayEncode;
    private int[] stateEncode;
    private char genderCode;
    private String nationalityEncode;
    private int[] consecutive;
    private static int extra;

    public void setNameEncode(String nameEncode) {
        this.nameEncode = StringUtils.stripAccents(nameEncode.substring(0, 4).toUpperCase());
    }

    public void setBirthDayEncode(int[] birthDayEncode) {
        this.birthDayEncode = Arrays.copyOfRange(birthDayEncode, 0, 6);
    }

    public void setStateEncode(int[] stateEncode) {
        this.stateEncode = Arrays.copyOfRange(stateEncode, 0 ,2);
    }

    public void setGenderCode(Sexo genderCode) {
        this.genderCode = genderCode.getCodigo();
    }

    public void setNationalityEncode(String nationalityEncode) {
        this.nationalityEncode = StringUtils.stripAccents(nationalityEncode.substring(0, 3).toUpperCase());
    }

    public void setConsecutive(int[] consecutive) {
        this.consecutive =  Arrays.copyOfRange(consecutive, 0 ,2);
    }

    public int getExtra() {
        Folio.extra = calculateExtra();
        return  extra;
    }

    private int calculateExtra() {
        return Arrays.stream(ArrayUtils.addAll(
                ArrayUtils.addAll(
                        ArrayUtils.addAll(this.nameEncode.chars().toArray(), this.birthDayEncode),
                        ArrayUtils.addAll(this.stateEncode, this.nationalityEncode.chars().toArray())
                ),
                ArrayUtils.addAll(this.consecutive, (int) this.genderCode)
        )).sum() % EXTRA_QUOTIENT;
    }


}
