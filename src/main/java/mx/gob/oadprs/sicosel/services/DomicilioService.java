package mx.gob.oadprs.sicosel.services;


import mx.gob.oadprs.sicosel.model.Domicilio;
import mx.gob.oadprs.sicosel.model.catalog.Municipio;
import mx.gob.oadprs.sicosel.model.catalog.Pais;
import mx.gob.oadprs.sicosel.validator.DomicilioValidador;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DomicilioService {

    Domicilio crear(DomicilioValidador domicilioValidador, Municipio municipio,
                    Pais pais) throws Exception;

    Domicilio modifica(DomicilioValidador domicilioValidador, Municipio municipio,
                       Pais pais, UUID domicilioId) throws Exception;
}
