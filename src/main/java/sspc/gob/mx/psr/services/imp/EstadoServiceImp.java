package sspc.gob.mx.psr.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sspc.gob.mx.psr.model.catalog.Estado;
import sspc.gob.mx.psr.repository.EstadoRepository;

import java.util.List;

@Service

public class EstadoServiceImp {

   @Autowired
    EstadoRepository estadorepository;

   public List<Estado> estados(){
       return estadorepository.findAll();

   }
}
