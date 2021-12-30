package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.FamiliarDto;
import sspc.gob.mx.psr.exeptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.Familiar;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.validator.FamiliarValidador;

import java.util.List;
import java.util.UUID;

public interface FamiliarService {
    public FamiliarDto crear(FamiliarValidador familiarValidador, Sentenciado sentenciado) throws Exception ;
    public Familiar busca(UUID id) throws ItemNotFoundException;
    public List<FamiliarDto> familiaresSentenciado(Sentenciado sentenciado);
}
