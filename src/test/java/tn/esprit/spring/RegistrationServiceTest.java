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

@ExtendWith(MockitoExtension.class)
@SpringBootTest

class RegistrationServiceTest {

    @Mock
    private IRegistrationRepository registrationRepository;
    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private RegistrationServicesImpl registrationServices;


    @Test
    public void testAddRegistrationAndAssignToSkier() {
        // Créez un skieur de test
        Skier skier = new Skier();
        skier.setId(1L);

        Registration registration = new Registration();
        registration.setSkier(skier);
        Mockito.when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));

        Mockito.when(registrationRepository.save(Mockito.any(Registration.class))).thenAnswer(invocation -> {
            Registration savedRegistration = invocation.getArgument(0);
            savedRegistration.setId(1L);
            return savedRegistration;

        });

        Registration result = registrationServices.addRegistrationAndAssignToSkier(registration, 1L);


        assertNotNull(result);
        assertEquals(skier, result.getSkier());
        assertEquals(1L, result.getId());
    }
}
