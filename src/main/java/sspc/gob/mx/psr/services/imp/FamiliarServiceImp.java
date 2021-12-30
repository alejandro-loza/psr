package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.FamiliarDto;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.Familiar;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.model.catalog.Parentesco;
import sspc.gob.mx.psr.repository.FamiliarRepository;
import sspc.gob.mx.psr.services.FamiliarService;
import sspc.gob.mx.psr.services.ParentescoService;
import sspc.gob.mx.psr.services.SentenciadoService;
import sspc.gob.mx.psr.validator.FamiliarValidador;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class FamiliarServiceImp implements FamiliarService{

    @Autowired
    FamiliarRepository familiarRepository;

    @Autowired
    SentenciadoService sentenciadoService;

    @Autowired
    ParentescoService parentescoService;


    @Override
    public FamiliarDto crear(FamiliarValidador familiarValidador, Sentenciado sentenciado) throws Exception {
        var parentesco = parentescoService.busca(familiarValidador.getParentescoId());

        return new FamiliarDto(familiarRepository.save(construyeFamiliar(familiarValidador, sentenciado, parentesco)));
    }

    @Override
    public Familiar busca(UUID id) throws ItemNotFoundException  {
        return familiarRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("familiar.notFound") );
    }

    @Override
    public List<FamiliarDto> familiaresSentenciado(Sentenciado sentenciado) {
        return familiarRepository.findAllBySentenciado(sentenciado)
                .stream().map(FamiliarDto::new).collect(Collectors.toList());

    }

    private Familiar construyeFamiliar(FamiliarValidador familiar, Sentenciado sentenciado, Parentesco parentesco) {
        return Familiar.builder()
                .nombre(familiar.getNombre())
                .apellidoPaterno(familiar.getApellidoPaterno())
                .apellidoMaterno(familiar.getApellidoMaterno())
                .documento(familiar.getDocumento())
                .telefonoFijo(familiar.getTelefonoFijo())
                .celular(familiar.getCelular())
                .parentesco(parentesco)
                .sentenciado(sentenciado)
                .build();
    }
}
