package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Piste;

import java.util.*;

import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PisteServiceImpTest {


        @Mock
        private IPisteRepository PisteRepository;


        @InjectMocks
        private PisteServicesImpl PisteServices;


        @Test
        @Order(1)
        void testAddPiste() {
            // Create a sample subscription
            Piste piste = new Piste();
            // Mock the behavior of the repository method
            when(PisteRepository.save(piste)).thenReturn(piste);

            // Invoke the method and verify the result
            Piste addedPiste = PisteServices.addPiste(piste);
            assertEquals(piste, addedPiste);

            // Verify that save method of PisteRepository was called once
            verify(PisteRepository, times(1)).save(piste);
        }

        @Test
        @Order(2)
        void testRetrieveAllPiste() {

                List<Piste> listPiste = PisteServices.retrieveAllPistes();
                Assertions.assertEquals(0, listPiste.size());

        }
/*
    Piste piste = new Piste(1,"f1",Color.RED, 6,7,"AA");
    List<Piste> listPiste = new ArrayList<Piste>() {
        {
            add(p1);
            add(p2);
            add(new Piste(3,"xx"));
            add(new Piste(4,"yy"));
        }
    };
Piste p1 = new Piste (1,"Courchevel");
Piste p2 = new Piste (2,"Alpes");
*/

}

