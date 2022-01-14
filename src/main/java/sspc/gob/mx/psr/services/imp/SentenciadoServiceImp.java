package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.SentenciadoDto;
import sspc.gob.mx.psr.exceptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.Domicilio;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.repository.SentencedRepository;
import sspc.gob.mx.psr.services.*;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import java.util.UUID;


@Service
public class SentenciadoServiceImp implements SentenciadoService {

    @Autowired
    SentencedRepository sentencedRepository;

    @Autowired
    FolioService folioService;

    @Autowired
    EstadoService estadoService;

    @Autowired
    EstadoCivilService estadoCivilService;

    @Autowired
    PaisService paisService;

    @Autowired
    EscolaridadService escolaridadService;

    @Autowired
    EtniaService etniaService;

    @Autowired
    OcupacionService ocupacionService;

    @Override
    public SentenciadoDto crear(SentenciadoValidador sentenciadoValidador) throws Exception {
        Estado estado = estadoService.busca(sentenciadoValidador.getEstadoId());
        Pais pais = paisService.busca(sentenciadoValidador.getNacionalidadId());

        Sentenciado sentenciado = sentencedRepository.save(construyeSentenciado(sentenciadoValidador, estado, pais));
        return new SentenciadoDto(sentenciado, folioService.generar(sentenciado));
    }

    @Override
    public Sentenciado busca(UUID id) throws Exception{
        return sentencedRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("sentenciado.notFound") );
    }

    @Override
    public Sentenciado creaDireccion(Sentenciado sentenciado, Domicilio domicilio) throws Exception {
        if(sentenciado.getDomicilio() == null){
            sentenciado.setDomicilio(domicilio);
            return sentencedRepository.save(sentenciado);
        }
        else throw new Exception("sentenciado.domicilio.alreadyExist");
    }

    private Sentenciado construyeSentenciado(SentenciadoValidador sentencedInput, Estado estado, Pais pais) {

        Sentenciado.SentenciadoBuilder sb = Sentenciado.builder()
                .nombre(sentencedInput.getNombre())
                .apellidoPaterno(sentencedInput.getApellidoPaterno())
                .apellidoMaterno(sentencedInput.getApellidoMaterno())
                .estado(estado)
                .nacionalidad(pais)
                .documento(sentencedInput.getDocumento())
                .estadoCivil(estadoCivilService.busca(sentencedInput.getEstadoCivil()))
                .alias(sentencedInput.getAlias()) //TODO IS A List?
                .otrosNombres(sentencedInput.getOtrosNombres())  //TODO IS A List?
                .fechaNacimiento(sentencedInput.getFechaNacimiento())
                .sexo(sentencedInput.getSexo())
                .telefonoFijo(sentencedInput.getTelefonoFijo())
                .celular(sentencedInput.getCelular())
                .correoElectronico(sentencedInput.getCorreoElectronico());

        if(sentencedInput.getEscolaridad() != null)
            sb.escolaridad(escolaridadService.busca(sentencedInput.getEscolaridad()));

        if(sentencedInput.getEtniaId() != null)
            sb.etnia(etniaService.busca(sentencedInput.getEtniaId()));

        if(sentencedInput.getOcupacionId() != null)
            sb.ocupacion(ocupacionService.busca(sentencedInput.getOcupacionId()) );


        return  sb.build();
    }

}
