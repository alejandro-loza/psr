package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import sspc.gob.mx.psr.repository.DomicilioRepository;
import sspc.gob.mx.psr.services.DomicilioService;

public class DomicilioServiceImp implements DomicilioService {

    @Autowired
    DomicilioRepository domicilioRepository;

    @Override
    public DomicilioDto crear(DomicilioValidador domicilioValidador) throws Exception {
        return null;
    }
}
