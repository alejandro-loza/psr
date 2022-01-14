package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.validator.DomicilioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.gob.oadprs.sicosel.model.Domicilio;
import mx.gob.oadprs.sicosel.model.catalog.Municipio;
import mx.gob.oadprs.sicosel.model.catalog.Pais;
import mx.gob.oadprs.sicosel.repository.DomicilioRepository;
import mx.gob.oadprs.sicosel.services.DomicilioService;

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
