package tn.esprit.spring.instructor.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.IInstructorServices;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class InstructorService {

    @Mock
    IInstructorRepository instructorRepository;
    @InjectMocks
    InstructorServicesImpl instructorServices;
    ICourseRepository courseRepository;
    @Test
    @Order(1)
    public void retrieveAllInstructors(){
        List<Instructor> listInstructor = instructorServices.retrieveAllInstructors();
        assertEquals(0,listInstructor.size());
    }
    @Test
    public void testAddInstructor() {
        // Créer un instructeur fictif pour le test
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");

        // Simuler le comportement du instructorRepository
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Appeler la méthode que vous souhaitez tester
        Instructor result = instructorServices.addInstructor(instructor);

        // Vérifier si la méthode a correctement ajouté l'instructeur
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }


}
