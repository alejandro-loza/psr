package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.FamiliarDto;
import sspc.gob.mx.psr.exceptions.ItemNotFoundException;
import sspc.gob.mx.psr.model.Domicilio;
import sspc.gob.mx.psr.model.Familiar;
import sspc.gob.mx.psr.model.Sentenciado;
import sspc.gob.mx.psr.validator.FamiliarValidador;

import java.util.List;
import java.util.UUID;

public interface FamiliarService {
    FamiliarDto crear(FamiliarValidador familiarValidador, Sentenciado sentenciado) throws Exception ;
    Familiar busca(UUID id) throws ItemNotFoundException;
    Familiar buscaFamiliarSentenciado(Sentenciado sentenciado, UUID familiarId) throws ItemNotFoundException;

    Familiar creaDireccion(Familiar familiar, Domicilio domicilio) throws Exception;

    List<FamiliarDto> familiaresSentenciado(Sentenciado sentenciado);
}
