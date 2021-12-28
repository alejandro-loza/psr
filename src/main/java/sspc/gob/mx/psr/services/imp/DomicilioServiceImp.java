package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import sspc.gob.mx.psr.dto.DomicilioDto;
import sspc.gob.mx.psr.repository.DomicilioRepository;
import sspc.gob.mx.psr.services.DomicilioService;
import sspc.gob.mx.psr.validator.DomicilioValidador;

public class DomicilioServiceImp implements DomicilioService {

    @Autowired
    DomicilioRepository domicilioRepository;

    @Override
    public DomicilioDto crear(DomicilioValidador domicilioValidador) throws Exception {
        return null;
    }
}
