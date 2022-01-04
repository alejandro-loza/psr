package sspc.gob.mx.psr.services;


import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.model.Domicilio;
import sspc.gob.mx.psr.model.catalog.Municipio;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.validator.DomicilioValidador;

@Service
public interface DomicilioService {

    Domicilio crear(DomicilioValidador domicilioValidador, Municipio municipio,
                    Pais pais) throws Exception;

}
