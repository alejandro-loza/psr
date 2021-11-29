package sspc.gob.mx.psr.services;

import sspc.gob.mx.psr.dto.SentencedDto;
import sspc.gob.mx.psr.validator.SentencedValidator;

public interface SentencedService {
    SentencedDto create(SentencedValidator sentencedValidator);
}
