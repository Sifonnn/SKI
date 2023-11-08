package tn.esprit.spring;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.RegistrationServicesImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest

class RegistrationServiceTest {
@InjectMocks
    private tn.esprit.spring.services.RegistrationServicesImpl registrationServices;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private ICourseRepository ICourseRepository;

    @InjectMocks
    private RegistrationServicesImplTest registrationServicesImplMock;



    @Test
    public void testAddRegistrationAndAssignToSkier() {
        // Créez un Skier fictif
        Long numSkier = 1L;
        Skier skier = new Skier();
        skier.setNumSkier(numSkier);

        // Créez un Registration fictif
        Registration registration = new Registration();
        registration.setSkier(skier);

        // Définissez les comportements simulés pour les méthodes de vos repositories
        Mockito.when(skierRepository.findById(numSkier)).thenReturn(Optional.of(skier));
        Mockito.when(registrationRepository.save(Mockito.any(Registration.class))).thenReturn(registration);

        // Appelez la méthode que vous testez
        Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, numSkier);

        // Assurez-vous que la méthode a correctement attribué le Skier et enregistré la Registration
        Assertions.assertEquals(skier, result.getSkier());

        // Vérifiez que les méthodes des repositories ont été appelées comme prévu
        Mockito.verify(skierRepository, Mockito.times(1)).findById(numSkier);
        Mockito.verify(registrationRepository, Mockito.times(1)).save(registration);
    }
}
