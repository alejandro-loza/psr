package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.FamiliarDto;
import sspc.gob.mx.psr.exceptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.Domicilio;
import sspc.gob.mx.psr.model.Familiar;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.model.catalog.Pais;
import sspc.gob.mx.psr.model.catalog.Parentesco;
import sspc.gob.mx.psr.repository.FamiliarRepository;
import sspc.gob.mx.psr.services.FamiliarService;
import sspc.gob.mx.psr.services.PaisService;
import sspc.gob.mx.psr.services.ParentescoService;
import sspc.gob.mx.psr.validator.FamiliarValidador;

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
    public Familiar busca(UUID id) throws ItemNotFoundException  {
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
