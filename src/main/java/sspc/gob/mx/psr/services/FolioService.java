package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.utils.Folio;
import sspc.gob.mx.psr.validator.SentencedValidator;

public interface FolioService {
    Folio generate(SentencedValidator sentencedValidator);
}
