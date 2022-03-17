package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.DomicilioDto;
import mx.gob.oadprs.sicosel.dto.SentenciadoDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.Domicilio;
import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.model.catalog.Estado;
import mx.gob.oadprs.sicosel.model.catalog.Pais;
import mx.gob.oadprs.sicosel.repository.SentencedRepository;
import mx.gob.oadprs.sicosel.services.*;
import mx.gob.oadprs.sicosel.validator.DomicilioValidador;
import mx.gob.oadprs.sicosel.validator.SentenciadoValidador;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


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

    @Autowired
    DomicilioService domicilioService;

    @Autowired
    MunicipioService municipioService;

    @Override
    public SentenciadoDto crear(SentenciadoValidador sentenciadoValidador) throws Exception {
        Estado estado = estadoService.busca(sentenciadoValidador.getEstadoId());
        Pais pais = paisService.busca(sentenciadoValidador.getNacionalidadId());

        Sentenciado sentenciado = sentencedRepository.save(construyeSentenciado(sentenciadoValidador, estado, pais));
        return new SentenciadoDto(sentenciado, folioService.generar(sentenciado));
    }

    @Override
    public SentenciadoDto modifica(SentenciadoValidador sentenciadoValidador, UUID sentenciadoId) throws Exception {
        Estado estado = estadoService.busca(sentenciadoValidador.getEstadoId());
        Pais pais = paisService.busca(sentenciadoValidador.getNacionalidadId());
        Sentenciado sentenciado = busca(sentenciadoId);
        mergeDeNuevasPropiedades(sentenciadoValidador, estado, pais, sentenciado);
        return new SentenciadoDto(sentencedRepository.save(sentenciado));//todo verificar folio previamente creado
    }

    private void mergeDeNuevasPropiedades(
            SentenciadoValidador sentenciadoValidador,
            Estado estado,
            Pais pais,
            Sentenciado sentenciado) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        PropertyUtils.describe(construyeSentenciado(sentenciadoValidador, estado, pais)).entrySet().stream()
                .filter(e -> e.getValue() != null)
                .filter(e -> ! e.getKey().equals("class"))
                .forEach(e -> {
                    try {
                        PropertyUtils.setProperty(sentenciado, e.getKey(), e.getValue());
                    } catch (Exception illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                });
    }

    @Override
    public Sentenciado busca(UUID id) throws Exception{
        return sentencedRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("sentenciado.notFound") );
    }

    @Override
    public DomicilioDto agregaDireccion(UUID sentenciadoId, DomicilioValidador validador) throws Exception {
        Sentenciado sentenciado = busca(sentenciadoId);
        Domicilio domicilio = domicilioService.crear(validador,
                municipioService.busca(validador.getMunicipioId()),
                paisService.busca(validador.getPaisId()));
        if(sentenciado.getDomicilio() == null){
            sentenciado.setDomicilio(domicilio);
            sentencedRepository.save(sentenciado);
            return new DomicilioDto(domicilio, sentenciado.getId());
        }
        else throw new Exception("sentenciado.domicilio.alreadyExist");
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
    public List<SentenciadoDto> buscaPorNombreCompleto(String nombre, String apellidoPaterno, String apellidoMaterno) throws Exception{
        return sentencedRepository
                .findAllByNombreAndApellidoPaternoAndApellidoMaterno(nombre, apellidoPaterno, apellidoMaterno)
                .stream().map(SentenciadoDto::new).collect(Collectors.toList());

    }

    @Override
    public List<SentenciadoDto> buscaPorNombreApellidoPaterno(String nombre, String apellidoPaterno) throws Exception{
        return sentencedRepository.findAllByNombreAndApellidoPaterno(nombre, apellidoPaterno)
                .stream().map(SentenciadoDto::new).collect(Collectors.toList());
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
