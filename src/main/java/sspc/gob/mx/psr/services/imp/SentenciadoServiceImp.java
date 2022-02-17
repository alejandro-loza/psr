package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.SentenciadoDto;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
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
    public Sentenciado buscaPorFolio(String folio) throws Exception {
        return folioService.buscaPorFolio(folio).getSentenciado();
    }

    @Override
    public Sentenciado creaDireccion(Sentenciado sentenciado, Domicilio domicilio) throws Exception {
        if(sentenciado.getDomicilio() == null){
            sentenciado.setDomicilio(domicilio);
            return sentencedRepository.save(sentenciado);
        }
        else throw new Exception("sentenciado.domicilio.alreadyExist");
    }

    @Override
    public Sentenciado buscaPorNombreCompleto(String nombre, String apellidoPaterno, String apellidoMaterno) throws Exception{
        return sentencedRepository.findByNombreAndApellidoPaternoAndApellidoMaterno(nombre, apellidoPaterno, apellidoMaterno)
                .orElseThrow(() -> new ItemNotFoundException("sentenciado.notFound"));
    }


    private Sentenciado construyeSentenciado(SentenciadoValidador sentencedInput, Estado estado, Pais pais) {

        return  Sentenciado.builder()
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
                .ocupacion(ocupacionService.busca(sentencedInput.getOcupacionId()))
                .sexo(sentencedInput.getSexo())
                .etnia(etniaService.busca(sentencedInput.getEtniaId()))
                .escolaridad(escolaridadService.busca(sentencedInput.getEscolaridad()))
                .telefonoFijo(sentencedInput.getTelefonoFijo())
                .celular(sentencedInput.getCelular())
                .correoElectronico(sentencedInput.getCorreoElectronico())
                .build();
    }

}
