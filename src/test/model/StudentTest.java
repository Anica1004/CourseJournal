package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    Student student;
    Course course1;
    Course course2;
    Course course3;
    Course course4;
    Course course5;

    @BeforeEach
    public void constructBefore(){
        student = new Student();
        course1 = new Course("CPSC 110", "Gregor", 4,
                1, 96, 1, "A good introduction of programming" );
        course2 = new Course ("CPSC 210", "Steve", 3,
                1, 100, 2, "Watching EdX videos is very important" );
        course3 = new Course ("SCIE 113", "Yumi", 3,
                1, 92, 2, "Essays were hard, but GPA booster" );
        course4 = new Course ("PHYS 119", "Jen", 1,
                1, 93, 2, "The labs were extremely difficult" );
        course5 = new Course ("DSCI 100", "Quan", 3,
                1, 88, 2, "Quizzes were times 45 minutes, and extremely quick" );
        student = new Student();
        student.addFourthYearCourses(course1);
        student.addFourthYearCourses(course2);
        student.addFourthYearCourses(course3);
        student.addFourthYearCourses(course4);
        student.addFourthYearCourses(course5);

    }



    @Test
    public void StudentTest(){
        student = new Student();
        assertEquals(student.getFirstYearCourses(), new ArrayList<>());
        assertEquals(student.getSecondYearCourses(), new ArrayList<>());
        assertEquals(student.getThirdYearCourses(), new ArrayList<>());
        assertEquals(student.getFourthYearCourses(), new ArrayList<>());
        List<List<Integer>> example = new ArrayList<>();
        List<Integer> element1 = new ArrayList<Integer>();
        List<Integer> element2 = new ArrayList<Integer>();
        List<Integer> element3 = new ArrayList<Integer>();
        List<Integer> element4 = new ArrayList<Integer>();
        example.add(element1);
        example.add(element2);
        example.add(element3);
        example.add(element4);
        assertEquals(student.getListOfListOfCourses(), example);
    }
    @Test
    public void SortCourseTest() {
        student = new Student();
        ArrayList<Course> firstYearCourses = new ArrayList<>();
        firstYearCourses.add(course1);
        student.sortCourse(course1, 1);
        assertEquals(student.getFirstYearCourses(), firstYearCourses);

        student.sortCourse(course2, 2);
        ArrayList<Course> secondYearCourses = new ArrayList<>();
        secondYearCourses.add(course2);
        assertEquals(student.getSecondYearCourses(), secondYearCourses);

        student.sortCourse(course3, 3);
        ArrayList<Course> thirdYearCourses = new ArrayList<>();
        thirdYearCourses.add(course3);
        assertEquals(student.getThirdYearCourses(), thirdYearCourses);

        student.sortCourse(course4, 4);
        ArrayList<Course> fourthYearCourses = new ArrayList<>();
        fourthYearCourses.add(course4);
        assertEquals(student.getFourthYearCourses(), fourthYearCourses);


    }

    @Test
    public void FindCourseTest() {
        assertEquals(student.findCourse("CPSC 110", student.getFourthYearCourses()), course1);
        assertEquals(student.findCourse("SCIE 113", student.getFourthYearCourses()), course3);
        assertEquals(student.findCourse("UNKNOWN COURSE", student.getFourthYearCourses()), null);
    }


    @Test
    public void AddFirstYearCoursesTest(){
        student = new Student();
        student.addFirstYearCourses(course1);
        List<Course> courses = new ArrayList<Course>();
        courses.add(course1);
        assertEquals(student.getFirstYearCourses(), courses);

    }

    @Test
    public void AddSecondYearCoursesTest(){
        student = new Student();
        student.addSecondYearCourses(course1);
        List<Course> courses = new ArrayList<Course>();
        courses.add(course1);
        assertEquals(student.getSecondYearCourses(), courses);

    }

    @Test
    public void AddThirdYearCoursesTest(){
        student = new Student();
        student.addThirdYearCourses(course1);
        List<Course> courses = new ArrayList<Course>();
        courses.add(course1);
        assertEquals(student.getThirdYearCourses(), courses);

    }

    @Test
    public void AddFourthYearCoursesTest(){
        student = new Student();
        student.addFourthYearCourses(course1);
        List<Course> courses = new ArrayList<Course>();
        courses.add(course1);
        assertEquals(student.getFourthYearCourses(), courses);

    }
    @Test
    public void CalculateAverageTest(){
        student = new Student();
        student.addFirstYearCourses(course4);
        student.addFirstYearCourses(course5);
        assertEquals(student.calculateAverage(student.getFirstYearCourses()), 89.25);
    }

    @Test
    public void TotalCreditTest() {
        student = new Student();
        student.addFirstYearCourses(course1);
        student.addFirstYearCourses(course2);
        student.addFirstYearCourses(course3);
        assertEquals(student.totalCredit(student.getFirstYearCourses()), 10);
    }


}

