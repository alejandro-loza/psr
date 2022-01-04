package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.model.Domicilio;
import sspc.gob.mx.psr.model.catalog.Municipio;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.repository.DomicilioRepository;
import sspc.gob.mx.psr.services.DomicilioService;
import sspc.gob.mx.psr.validator.DomicilioValidador;

@Service
public class DomicilioServiceImp implements DomicilioService {

    @Autowired
    DomicilioRepository domicilioRepository;

    @Override
    public Domicilio crear(DomicilioValidador domicilioValidador, Municipio municipio,
                           Pais pais) {
        return domicilioRepository.save(construyeDomicilio(
                domicilioValidador, municipio, pais
        ));
    }

    private Domicilio construyeDomicilio(DomicilioValidador cmd, Municipio municipio,
                                         Pais pais){
        return Domicilio.builder()
                .estado(municipio.getEstado())
                .municipio(municipio)
                .pais(pais)
                .colonia(cmd.getColonia())
                .codigoPostal(cmd.getCodigoPostal())
                .calle(cmd.getCalle())
                .numero(cmd.getNumero())
                .latitud(cmd.getLatitud())
                .longitud(cmd.getLongitud())
                .descripcion(cmd.getDescripcion() != null ? cmd.getDescripcion(): null)
                .build();
    }
}
