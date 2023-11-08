package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = GestionStationSkiApplication.class)
@ExtendWith(MockitoExtension.class)
public class PisteServiceImpMockito {

    @Mock(lenient = true)
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteServices;
    Piste p1 = new Piste();
    @Test
    public void testaddPiste(){
        Mockito.when(pisteRepository.save(p1)).thenReturn(p1);
        Piste piste1 = pisteServices.addPiste(p1);
        //assert Not Null (p1)
        Mockito.verify(pisteRepository).save(Mockito.any(Piste.class));
    }
    List<Piste> listPiste = new ArrayList<Piste>();
    @Test
    public void testretrieveAllPistes(){
        Mockito.when(pisteRepository.findAll()).thenReturn(listPiste);
        List<Piste> listPistes1 = pisteServices.retrieveAllPistes();
        assertEquals(listPiste.size(),listPistes1.size());
    }

}
