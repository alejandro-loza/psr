package mx.gob.oadprs.sicosel.services.imp;

import mx.gob.oadprs.sicosel.dto.FamiliarDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.Domicilio;
import mx.gob.oadprs.sicosel.model.Familiar;
import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.model.catalog.Pais;
import mx.gob.oadprs.sicosel.model.catalog.Parentesco;
import mx.gob.oadprs.sicosel.repository.FamiliarRepository;
import mx.gob.oadprs.sicosel.services.FamiliarService;
import mx.gob.oadprs.sicosel.services.PaisService;
import mx.gob.oadprs.sicosel.services.ParentescoService;
import mx.gob.oadprs.sicosel.validator.FamiliarValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class FamiliarServiceImp implements FamiliarService{

    @Autowired
    FamiliarRepository familiarRepository;

    @Autowired
    ParentescoService parentescoService;

    @Autowired
    PaisService paisService;


    @Override
    public FamiliarDto crear(FamiliarValidador familiarValidador, Sentenciado sentenciado) throws Exception {
        return new FamiliarDto(
                familiarRepository.save(construyeFamiliar(familiarValidador, sentenciado,
                    parentescoService.busca(familiarValidador.getParentescoId()),
                    paisService.busca(familiarValidador.getNacionalidadId()))
                )
        );
    }

    @Override
    public Familiar busca(UUID id) throws ItemNotFoundException {
        return familiarRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("familiar.notFound") );
    }

    @Override
    public Familiar buscaFamiliarSentenciado(Sentenciado sentenciado, UUID familiarId) throws ItemNotFoundException  {
        Familiar familiar = busca(familiarId);
        if(familiar.getSentenciado() != sentenciado){
            throw  new ItemNotFoundException("familiar.sentenciado.notFound");
        }
        return familiar;
    }

    @Override
    public Familiar creaDireccion(Familiar familiar, Domicilio domicilio) throws Exception {
        if(familiar.getDomicilio() == null){
            familiar.setDomicilio(domicilio);
            return familiarRepository.save(familiar);
        }
        else throw new Exception("familiar.domicilio.alreadyExist");
    }

    @Override
    public List<FamiliarDto> familiaresSentenciado(Sentenciado sentenciado) {
        return familiarRepository.findAllBySentenciado(sentenciado)
                .stream().map(FamiliarDto::new).collect(Collectors.toList());

    }

    private Familiar construyeFamiliar(FamiliarValidador familiar, Sentenciado sentenciado,
                                       Parentesco parentesco, Pais nacionalidad) {
        return Familiar.builder()
                .nombre(familiar.getNombre())
                .apellidoPaterno(familiar.getApellidoPaterno())
                .apellidoMaterno(familiar.getApellidoMaterno())
                .nacionalidad(nacionalidad)
                .documento(familiar.getDocumento())
                .telefonoFijo(familiar.getTelefonoFijo())
                .celular(familiar.getCelular())
                .parentesco(parentesco)
                .sentenciado(sentenciado)
                .build();
    }
}
