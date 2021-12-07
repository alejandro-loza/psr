package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.SentenciadoDto;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.repository.SentencedRepository;
import sspc.gob.mx.psr.services.SentenciadoService;
import sspc.gob.mx.psr.validator.SentenciadoValidador;

import java.util.Date;

@Service
public class SentencedServiceImp implements SentenciadoService {

    @Autowired
    SentencedRepository sentencedRepository;

    @Override
    public SentenciadoDto crear(SentenciadoValidador sentenciadoValidador) {
        return new SentenciadoDto( sentencedRepository.save( construyeSentenciado(sentenciadoValidador)));
    }

    private Sentenciado construyeSentenciado(SentenciadoValidador sentencedInput) {
        return  Sentenciado.builder()
                .folio(generateFolio(sentencedInput))//TODO generate the folio
                .nombre(sentencedInput.getNombre())
                .apellidoPaterno(sentencedInput.getApellidoPaterno())
                .apellidoMaterno(sentencedInput.getApellidoMaterno())
                .nacionalidad(sentencedInput.getNacionalidad())
                .curp(sentencedInput.getCurp())
                .estadoCivil(sentencedInput.getEstadoCivil())
                .alias(sentencedInput.getAlias())
                .otrosNombres(sentencedInput.getOtrosNombres())
                .fechaNacimiento(new Date(sentencedInput.getFechaNacimiento()))//TODO check this!!
                .ocupacion(sentencedInput.getOcupacion())
                .sexo(sentencedInput.getSexo())//TODO ENUM !!
                .etnia(sentencedInput.getEtnia())//TODO IS A CATALOG? MAYA
                .escolaridad(sentencedInput.getEscolaridad())
                .telefonoFijo(sentencedInput.getTelefonoFijo())
                .celular(sentencedInput.getCelular())
                .email(sentencedInput.getEmail())
                .build();
    }

    private String generateFolio(SentenciadoValidador sentencedInput) {
        return "null";
    }
}
