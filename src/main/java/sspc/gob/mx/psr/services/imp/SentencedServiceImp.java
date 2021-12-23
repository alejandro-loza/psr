package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.SentenciadoDto;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.repository.SentencedRepository;
import sspc.gob.mx.psr.services.*;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import javax.transaction.Transactional;


@Service
public class SentencedServiceImp implements SentenciadoService {

    @Autowired
    SentencedRepository sentencedRepository;

    @Autowired
    FolioService folioService;

    @Autowired
    EstadoService estadoService;

    @Autowired
    PaisService paisService;

    @Autowired
    EscolaridadService escolaridadService;

    @Override
    @Transactional
    public SentenciadoDto crear(SentenciadoValidador sentenciadoValidador) throws Exception {
        Sentenciado sentenciado = sentencedRepository.save(construyeSentenciado(sentenciadoValidador));
        return new SentenciadoDto( sentenciado);
    }

    private Sentenciado construyeSentenciado(SentenciadoValidador sentencedInput) throws Exception {
        Estado estado = estadoService.busca(sentencedInput.getEstadoId());
        Pais pais = paisService.busca(sentencedInput.getNacionalidad());

        return  Sentenciado.builder()
                .folio(folioService.generar(sentencedInput, estado, pais))
                .nombre(sentencedInput.getNombre())
                .apellidoPaterno(sentencedInput.getApellidoPaterno())
                .apellidoMaterno(sentencedInput.getApellidoMaterno())
                .estado(estado)
                .nacionalidad(pais)
                .curp(sentencedInput.getCurp())
                .estadoCivil(sentencedInput.getEstadoCivil())  //TODO IS A CATALOG?
                .alias(sentencedInput.getAlias()) //TODO IS A List?
                .otrosNombres(sentencedInput.getOtrosNombres())  //TODO IS A List?
                .fechaNacimiento(sentencedInput.getFechaNacimiento())
                .ocupacion(sentencedInput.getOcupacion()) //TODO IS A CATALOG?
                .sexo(sentencedInput.getSexo())
                .etnia(sentencedInput.getEtnia())//TODO IS A CATALOG? MAYA
                .escolaridad(escolaridadService.busca(sentencedInput.getEscolaridad()))
                .telefonoFijo(sentencedInput.getTelefonoFijo())
                .celular(sentencedInput.getCelular())
                .email(sentencedInput.getEmail())
                .build();
    }

}
