package sspc.gob.mx.psr;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sspc.gob.mx.psr.enums.Gender;
import sspc.gob.mx.psr.utils.Folio;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class FolioTest {

    @Test
    void folioCreate(){
        Folio folio = new Folio();
        folio.setNameEncode("ñéclair");
        folio.setBirthDayEncode(new int[]{8, 8, 0,4,1,6,7});
        folio.setStateEncode(new int[]{1, 2, 3,4,5,6,7});
        folio.setGenderCode(Gender.FEMALE);
        folio.setNationalityEncode("MEXIC");
        folio.setConsecutive(new int[]{ 8,9,5,6,7});

        assertEquals("NECL",folio.getNameEncode());
        assertArrayEquals( new int[]{8, 8, 0,4,1,6},folio.getBirthDayEncode());
        assertArrayEquals( new int[]{1, 2},folio.getStateEncode());
        assertEquals('M',folio.getGenderCode());
        assertEquals("MEX",folio.getNationalityEncode());
        assertArrayEquals( new int[]{8, 9},folio.getConsecutive());
        assertEquals(4,folio.getExtra());
    }
}

