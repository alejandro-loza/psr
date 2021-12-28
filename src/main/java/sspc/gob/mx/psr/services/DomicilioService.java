package sspc.gob.mx.psr.services;


import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.DomicilioDto;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.model.catalog.Municipio;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.validator.DomicilioValidador;

@Service
public interface DomicilioService {

    DomicilioDto crear(DomicilioValidador domicilioValidador, Municipio municipio,
                       Pais pais, Sentenciado sentenciado) throws Exception;

}
