package tn.esprit.spring.instructor.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.IInstructorServices;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class InstructorService {

    @Autowired
    IInstructorRepository instructorRepository;
    @Autowired
    InstructorServicesImpl instructorServices;
    @Autowired
    ICourseRepository courseRepository;
    @Test
    @Order(1)
    public void retrieveAllInstructors(){
        List<Instructor> listInstructor = instructorServices.retrieveAllInstructors();
        assertEquals(0,listInstructor.size());
    }
    @Test
    @Order(2)
    public void testAddInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        Set<Course> courses = new HashSet<>();
        // Ajoutez des cours au set courses si nécessaire

        instructor.setCourses(courses);

        Instructor savedInstructor = instructorRepository.save(instructor);

        assertNotNull(savedInstructor.getNumInstructor());
        assertEquals("John", savedInstructor.getFirstName());
        assertEquals("Doe", savedInstructor.getLastName());
        // Vérifiez d'autres attributs si nécessaire
    }
    @Test
    @Order(3)
    public void testUpdateInstructor() {
        // Créez un instructeur et sauvegardez-le dans la base de données
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        Instructor savedInstructor = instructorRepository.save(instructor);

        // Modifiez les détails de l'instructeur
        savedInstructor.setFirstName("Jane");
        savedInstructor.setLastName("Doe");

        // Mettez à jour l'instructeur dans la base de données
        Instructor updatedInstructor = instructorRepository.save(savedInstructor);

        // Vérifiez que l'instructeur a bien été mis à jour
        assertNotNull(updatedInstructor.getNumInstructor());
        assertEquals("Jane", updatedInstructor.getFirstName());
        assertEquals("Doe", updatedInstructor.getLastName());
        // Vérifiez d'autres attributs si nécessaire
    }
    @Test
    @Order(4)
    public void testRetrieveInstructor() {
        // Créez un nouvel instructeur et sauvegardez-le dans la base de données
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        Instructor savedInstructor = instructorRepository.save(instructor);

        // Récupérez l'instructeur à l'aide de retrieveInstructor
        Instructor retrievedInstructor = instructorRepository.findById(savedInstructor.getNumInstructor()).orElse(null);

        // Vérifiez que l'instructeur récupéré n'est pas null et a les bonnes informations
        assertNotNull(retrievedInstructor);
        assertEquals("John", retrievedInstructor.getFirstName());
        assertEquals("Doe", retrievedInstructor.getLastName());

        // Essayez de récupérer un instructeur qui n'existe pas
        Instructor nonExistentInstructor = instructorRepository.findById(-1L).orElse(null);

        // Vérifiez que l'instructeur non existent est null
        assertNull(nonExistentInstructor);
    }
    @Test
    @Order(5)
    public void testAddInstructorAndAssignToCourse() {
        // Créer un nouveau cours et sauvegarder dans la base de données
        Course course = new Course();
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SKI);
        course.setPrice(100.0f);
        course.setTimeSlot(60);
        courseRepository.save(course);

        // Créer un nouvel instructeur
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        // Appeler la méthode addInstructorAndAssignToCourse avec le cours et l'instructeur
        Instructor savedInstructor = instructorServices.addInstructorAndAssignToCourse(instructor, course.getNumCourse());

        // Vérifier que l'instructeur a bien été ajouté et assigné au cours
        assertNotNull(savedInstructor.getNumInstructor());
        assertEquals("John", savedInstructor.getFirstName());
        assertEquals("Doe", savedInstructor.getLastName());
        assertNotNull(savedInstructor.getCourses());
        assertEquals(1, savedInstructor.getCourses().size());
        assertEquals(1, savedInstructor.getCourses().iterator().next().getLevel());
    }

}
