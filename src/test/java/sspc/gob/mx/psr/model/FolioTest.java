package sspc.gob.mx.psr.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sspc.gob.mx.psr.enums.Sexo;
import sspc.gob.mx.psr.utils.FolioBuilder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class FolioTest {

    @Test
    void folioCreate(){
        Folio folio = new Folio();
        folio.setNombreCodigo("ñéclair");
        folio.setNacimientoCodigo(new int[]{8, 8, 0,4,1,6,7});
        folio.setEntidadCodigo(new int[]{1, 2, 3,4,5,6,7});
        folio.setSexoCodigo(Sexo.FEMENINO);
        folio.setNacionalidadCodigo("MEXIC");
        folio.setConsecutivo(new int[]{ 8,9,5,6,7});
        folio.setExtra();

        assertEquals("NECL",folio.getNombreCodigo());
        assertArrayEquals( new int[]{8, 8, 0,4,1,6},folio.getNacimientoCodigo());
        assertArrayEquals( new int[]{1, 2},folio.getEntidadCodigo());
        assertEquals('M',folio.getSexoCodigo());
        assertEquals("MEX",folio.getNacionalidadCodigo());
        assertArrayEquals( new int[]{8, 9},folio.getConsecutivo());
        assertEquals(4,folio.getExtra());
    }

    @Test
    void folioBuilder(){
        Folio folio = new FolioBuilder(
                "ñéclair",
                new int[]{8, 8, 0,4,1,6,7},
                new int[]{1, 2, 3,4,5,6,7},
                Sexo.FEMENINO,
                "MEXIC",
                new int[]{ 8,9,5,6,7}
        ).build();

        assertEquals("NECL",folio.getNombreCodigo());
        assertArrayEquals( new int[]{8, 8, 0,4,1,6},folio.getNacimientoCodigo());
        assertArrayEquals( new int[]{1, 2},folio.getEntidadCodigo());
        assertEquals('M',folio.getSexoCodigo());
        assertEquals("MEX",folio.getNacionalidadCodigo());
        assertArrayEquals( new int[]{8, 9},folio.getConsecutivo());
        assertEquals(4,folio.getExtra());

    }
}

