package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.SentenciadoDto;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.repository.SentencedRepository;
import sspc.gob.mx.psr.services.EstadoService;
import sspc.gob.mx.psr.services.FolioService;
import sspc.gob.mx.psr.services.PaisService;
import sspc.gob.mx.psr.services.SentenciadoService;
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

    @Override
    @Transactional
    public SentenciadoDto crear(SentenciadoValidador sentenciadoValidador) throws Exception {
        return new SentenciadoDto( sentencedRepository.save( construyeSentenciado(sentenciadoValidador)));
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
                .estadoCivil(sentencedInput.getEstadoCivil())
                .alias(sentencedInput.getAlias())
                .otrosNombres(sentencedInput.getOtrosNombres())
                .fechaNacimiento(sentencedInput.getFechaNacimiento())
                .ocupacion(sentencedInput.getOcupacion())
                .sexo(sentencedInput.getSexo())
                .etnia(sentencedInput.getEtnia())//TODO IS A CATALOG? MAYA
                .escolaridad(sentencedInput.getEscolaridad())
                .telefonoFijo(sentencedInput.getTelefonoFijo())
                .celular(sentencedInput.getCelular())
                .email(sentencedInput.getEmail())
                .build();
    }

}
