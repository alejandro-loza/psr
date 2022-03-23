package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.Domicilio;
import mx.gob.oadprs.sicosel.model.Familiar;
import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.model.catalog.Municipio;
import mx.gob.oadprs.sicosel.model.catalog.Pais;
import mx.gob.oadprs.sicosel.model.catalog.Parentesco;
import mx.gob.oadprs.sicosel.repository.DomicilioRepository;
import mx.gob.oadprs.sicosel.services.DomicilioService;
import mx.gob.oadprs.sicosel.validator.DomicilioValidador;
import mx.gob.oadprs.sicosel.validator.FamiliarValidador;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

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

    @Override
    public Domicilio modifica(DomicilioValidador domicilioValidador, Municipio municipio,
                              Pais pais, UUID domicilioId) throws Exception {

        Domicilio domicilio = domicilioRepository.findById(domicilioId)
                .orElseThrow(() -> new ItemNotFoundException("domicilio.notFound") );
        mergeDeNuevasPropiedades(domicilioValidador, municipio, pais, domicilio);
        return domicilioRepository.save(domicilio);
    }

    private void mergeDeNuevasPropiedades(DomicilioValidador domicilioValidador,
                                          Municipio municipio,
                                          Pais pais,
                                          Domicilio domicilio) throws Exception {

        PropertyUtils.describe(construyeDomicilio(
                domicilioValidador, municipio, pais
        )).entrySet().stream()
            .filter(e -> e.getValue() != null)
            .filter(e -> ! e.getKey().equals("class"))
            .forEach(e -> {
                try {
                    PropertyUtils.setProperty(domicilio, e.getKey(), e.getValue());
                } catch (Exception illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            });
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
