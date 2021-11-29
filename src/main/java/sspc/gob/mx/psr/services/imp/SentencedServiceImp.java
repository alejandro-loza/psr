package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.dto.SentencedDto;
import sspc.gob.mx.psr.model.Sentenced;
import sspc.gob.mx.psr.repository.SentencedRepository;
import sspc.gob.mx.psr.services.SentencedService;
import sspc.gob.mx.psr.validator.SentencedValidator;

import java.util.Date;

@Service
public class SentencedServiceImp implements SentencedService {

    @Autowired
    SentencedRepository sentencedRepository;

    @Override
    public SentencedDto create(SentencedValidator sentencedInput) {
        return new SentencedDto( sentencedRepository.save( generateSentenced(sentencedInput)));
    }

    private Sentenced generateSentenced(SentencedValidator sentencedInput) {
        return  Sentenced.builder()
                .folio(generateFolio(sentencedInput))//TODO generate the folio
                .name(sentencedInput.getName())
                .firstName(sentencedInput.getFirstName())
                .lastName(sentencedInput.getLastName())
                .nationality(sentencedInput.getNationality())
                .curp(sentencedInput.getCurp())
                .maritalStatus(sentencedInput.getMaritalStatus())
                .alias(sentencedInput.getAlias())
                .otherNames(sentencedInput.getOtherNames())
                .birthDate(new Date(sentencedInput.getBirthDate()))//TODO check this!!
                .occupation(sentencedInput.getOccupation())
                .gender(sentencedInput.getGender())//TODO ENUM !!
                .ethnicity(sentencedInput.getEthnicity())//TODO IS A CATALOG?
                .schooling(sentencedInput.getSchooling())
                .homePhone(sentencedInput.getHomePhone())
                .mobilePhone(sentencedInput.getMobilePhone())
                .email(sentencedInput.getEmail())
                .build();
    }

    private String generateFolio(SentencedValidator sentencedInput) {
        return "null";
    }
}
