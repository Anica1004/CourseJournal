package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {
    Course course;

    @Test
    public void CourseTest() {
        course = new Course("CPSC 110", "Gregor", 4,
                2022, 96, 1, 10, "A good introduction of programming" );
        assertEquals(course.getCourseName(),"CPSC 110");
        assertEquals(course.getProfessorName(),"Gregor");
        assertEquals(course.getCredit(), 4);
        assertEquals(course.getYear(), 2022);
        assertEquals(course.getFinalMark(), 96);
        assertEquals(course.getTerm(), 1);
        assertEquals(course.getRating(), 10);
        assertEquals(course.getCourseSummary(), "A good introduction of programming" );

    }

}
