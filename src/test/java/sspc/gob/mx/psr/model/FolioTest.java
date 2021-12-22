package sspc.gob.mx.psr.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sspc.gob.mx.psr.enums.Sexo;
import sspc.gob.mx.psr.utils.FolioBuilder;

import java.nio.LongBuffer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class FolioTest {
/*
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
        assertEquals(880416L, folio.getNacimientoCodigo());
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
                LongBuffer.allocate(6).put(8).put(8).put(0).put(4).put(1).put(6),
                new int[]{1, 2, 3,4,5,6,7},
                Sexo.FEMENINO,
                "MEXIC",
                new int[]{ 8,9,5,6,7}
        ).build();

        assertEquals("NECL",folio.getNombreCodigo());
        assertEquals( 880416L ,folio.getNacimientoCodigo());
        assertArrayEquals( new int[]{1, 2},folio.getEntidadCodigo());
        assertEquals('M',folio.getSexoCodigo());
        assertEquals("MEX",folio.getNacionalidadCodigo());
        assertArrayEquals( new int[]{8, 9},folio.getConsecutivo());
        assertEquals(4,folio.getExtra());

    }

 */
}

