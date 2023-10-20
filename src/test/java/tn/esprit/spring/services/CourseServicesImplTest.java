package tn.esprit.spring.services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
class CourseServicesImplTest {

    @Autowired
    CourseServicesImpl cs;
    ICourseRepository iCourseServices;


    @Test
    void retrieveAllCourses() {

        List<Course> listCourse = cs.retrieveAllCourses();
        assertNotNull(listCourse);
        //assertEquals(8, listCourse.size());
    }

    @Test
    void addCourse(){

        Course course = new Course();
        assertNotNull(cs.addCourse(course));
    }

    @Test
    void updateCourse() {
        Course course = new Course();
        course.setLevel(0);
        Course courseadd = cs.addCourse(course);
        courseadd.setLevel(1);
        //assertNotNull(cs.updateCourse(course));
        Course courseupdate = cs.updateCourse(courseadd);
        assertEquals(1,courseupdate.getLevel());


    }

    @Test
    void retrieveCourse() {
        // Long numCource = 1L;
        // Course course = new Course();
        //

    }
}