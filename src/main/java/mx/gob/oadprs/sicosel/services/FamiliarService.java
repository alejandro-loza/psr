package mx.gob.oadprs.sicosel.services;

import mx.gob.oadprs.sicosel.dto.FamiliarDto;
import mx.gob.oadprs.sicosel.exceptions.ItemNotFoundException;
import mx.gob.oadprs.sicosel.model.Domicilio;
import mx.gob.oadprs.sicosel.model.Familiar;
import mx.gob.oadprs.sicosel.model.Sentenciado;
import mx.gob.oadprs.sicosel.validator.FamiliarValidador;

import java.util.List;
import java.util.UUID;

public interface FamiliarService {
    FamiliarDto crear(FamiliarValidador familiarValidador, Sentenciado sentenciado) throws Exception ;
    Familiar busca(UUID id) throws ItemNotFoundException;
    Familiar buscaFamiliarSentenciado(Sentenciado sentenciado, UUID familiarId) throws ItemNotFoundException;

    Familiar creaDireccion(Familiar familiar, Domicilio domicilio) throws Exception;

    List<FamiliarDto> familiaresSentenciado(Sentenciado sentenciado);
}
