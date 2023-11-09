package tn.esprit.spring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class InstructorServicesImplMockTest {
    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private InstructorServicesImpl instructorServices;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testretrieveAllInstructors(){
        List<Instructor> listInstructor = instructorServices.retrieveAllInstructors();
        assertEquals(0,listInstructor.size());
    }

    @Test
    void testAddInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        Set<Course> courses = new HashSet<>();
        instructor.setCourses(courses);

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor savedInstructor = instructorServices.addInstructor(instructor);

        assertNull(savedInstructor.getNumInstructor());
        assertEquals("John", savedInstructor.getFirstName());
        assertEquals("Doe", savedInstructor.getLastName());
    }

    @Test
    void testUpdateInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        when(instructorRepository.save(any(Instructor.class))).thenReturn(instructor);

        Instructor savedInstructor = instructorServices.updateInstructor(instructor);

        assertNull(savedInstructor.getNumInstructor());
        assertEquals("John", savedInstructor.getFirstName());
        assertEquals("Doe", savedInstructor.getLastName());
    }

    @Test
    void testRetrieveInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());

        when(instructorRepository.findById(anyLong())).thenReturn(Optional.of(instructor));

        Instructor retrievedInstructor = instructorServices.retrieveInstructor(1L);

        assertNotNull(retrievedInstructor);
        assertEquals("John", retrievedInstructor.getFirstName());
        assertEquals("Doe", retrievedInstructor.getLastName());

        Instructor nonExistentInstructor = instructorServices.retrieveInstructor(-1L);
        assertNotNull(nonExistentInstructor);
    }

    @Test
    void testAddInstructorAndAssignToCourse() {
        Course course = new Course();
        course.setLevel(0);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SKI);
        course.setPrice(100.0f);
        course.setTimeSlot(60);

        when(courseRepository.save(any(Course.class))).thenReturn(course);
        when(instructorRepository.save(any(Instructor.class))).thenReturn(new Instructor());

        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        instructor.setCourses(new HashSet<>()); // Initialiser la liste de cours



        assertNull(instructor.getNumInstructor());
        assertEquals("John", instructor.getFirstName());
        assertEquals("Doe", instructor.getLastName());
        assertEquals(0,instructor.getCourses().size());
    }
}