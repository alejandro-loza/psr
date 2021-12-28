package sspc.gob.mx.psr.services;


import sspc.gob.mx.psr.dto.DomicilioDto;
import sspc.gob.mx.psr.validator.DomicilioValidador;

public interface DomicilioService {
    DomicilioDto crear(DomicilioValidador domicilioValidador) throws Exception;
}
