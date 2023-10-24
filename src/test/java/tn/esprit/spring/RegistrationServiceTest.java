package tn.esprit.spring;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.RegistrationServicesImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest


public class RegistrationServiceTest {
    @Autowired
    private RegistrationServicesImpl registrationService;
    private ISkierRepository skierRepository;
    private IRegistrationRepository registrationRepository;

    @Before("")
    public void setUp() {
        skierRepository = mock(ISkierRepository .class);
        registrationRepository = mock(IRegistrationRepository.class);
        registrationService = new RegistrationServicesImpl(skierRepository, registrationRepository);
    }

    @Test
    @Order(1)
    public void testAddRegistrationAndAssignToSkier() {
        // Créez un objet Skier simulé pour le test
        Skier skier = new Skier();
        skier.setId(1L);

        // Simuler le comportement de skierRepository.findById
        when(skierRepository.findById(1L)).thenReturn(Optional.of(skier));

        // Créez un objet Registration simulé pour le test
        Registration registration = new Registration();
        registration.setSkier(null);

        // Appel de la méthode à tester
        Registration result = registrationService.addRegistrationAndAssignToSkier(registration, 1L);

        // Vérifiez que skierRepository.findById a été appelé avec le bon argument
        verify(skierRepository).findById(1L);

        // Vérifiez que registration.setSkier a été appelé avec le bon skier
        assertEquals(skier, registration.getSkier());

        // Vérifiez que registrationRepository.save a été appelé
        verify(registrationRepository).save(registration);

        // Vérifiez que le résultat renvoyé par la méthode est le résultat de registrationRepository.save
        assertEquals(result, registration);
    }

    @Test
    public void testNumWeeksCourseOfInstructorBySupport() {
        // Définir les valeurs d'entrée pour le test
        Long numInstructor = 1L;
        Support support = Support.SKI; // Assurez-vous que cela correspond aux valeurs réelles

        // Créez une liste simulée de numéros de semaine
        List<Integer> simulatedWeeks = Arrays.asList(1, 2, 3);

        // Simuler le comportement de registrationRepository.numWeeksCourseOfInstructorBySupport
        when(registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support))
                .thenReturn(simulatedWeeks);

        // Appel de la méthode à tester
        List<Integer> result = registrationService.numWeeksCourseOfInstructorBySupport(numInstructor, support);

        // Vérifiez que registrationRepository.numWeeksCourseOfInstructorBySupport a été appelé avec les bons arguments
        verify(registrationRepository).numWeeksCourseOfInstructorBySupport(numInstructor, support);

        // Vérifiez que le résultat renvoyé par la méthode est le résultat simulé
        assertEquals(simulatedWeeks, result);
    }

    @Test
    public void testAssignRegistration() {
        // Créez un objet Skier simulé pour le test
        Skier skier = new Skier();
        skier.setId(1L);

        // Créez un objet Course simulé pour le test
        Course course = new Course();
        course.setId(2L);

        // Créez un objet Registration simulé pour le test
        Registration registration = new Registration();
        registration.setSkier(null);
        registration.setCourse(null);

        // Simuler le comportement de registrationRepository.save
        when(registrationRepository.save(registration)).thenReturn(registration);

        // Appel de la méthode à tester
        Registration result = registrationService.assignRegistration(registration, skier, course);

        // Vérifiez que registration.setSkier a été appelé avec le bon skier
        assertEquals(skier, registration.getSkier());

        // Vérifiez que registration.setCourse a été appelé avec la bon cours
        assertEquals(course, registration.getCourse());

        // Vérifiez que registrationRepository.save a été appelé
        verify(registrationRepository).save(registration);

        // Vérifiez que le résultat renvoyé par la méthode est le résultat de registrationRepository.save
        assertEquals(result, registration);
    }
}