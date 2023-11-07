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
@Test
    public void testNumWeeksCourseOfInstructorBySupport() {
        // Arrange
        IRegistrationRepository registrationRepository = mock(IRegistrationRepository.class); // Crée un mock de RegistrationRepository.

        Long numInstructor = 123L; // Remplacez par la valeur de votre choix.
        Support support = Support.SNOWBOARD; // Remplacez par le support de votre choix.

        List<Integer> expectedWeeks = List.of(2, 4, 6); // Les valeurs attendues.

        // Configurer le mock pour renvoyer les valeurs attendues lorsque la méthode est appelée.
        when(registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support))
                .thenReturn(expectedWeeks);

        // Act
        List<Integer> result = registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support);

        // Assert
        assertEquals(expectedWeeks, result); // Vérifie que le résultat est égal aux valeurs attendues.

        // Vérifie que la méthode numWeeksCourseOfInstructorBySupport a été appelée avec les bons arguments.
        verify(registrationRepository).numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }
}
