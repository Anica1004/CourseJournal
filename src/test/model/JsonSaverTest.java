package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonLoader;
import persistence.JsonSaver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonSaverTest {


    List<Course> emptyList;
    List<Course> firstYearCoursesTest;
    List<Course> secondYearCoursesTest;
    List<Course> thirdYearCoursesTest;
    List<Course> fourthYearCoursesTest;
    List<List<Course>> emptyListOfList;
    List<List<Course>> listOfListOfCoursesTest;

    @BeforeEach
    public void setUp() {
        emptyList = new ArrayList<>();
        emptyListOfList = new ArrayList<>();
        firstYearCoursesTest = new ArrayList<>();
        secondYearCoursesTest = new ArrayList<>();
        thirdYearCoursesTest = new ArrayList<>();
        fourthYearCoursesTest = new ArrayList<>();
        listOfListOfCoursesTest = new ArrayList<>();
        emptyListOfList.add(emptyList);
        emptyListOfList.add(emptyList);
        emptyListOfList.add(emptyList);
        emptyListOfList.add(emptyList);
    }

    @Test
    void testWriteIllegalFile() {
        try {
            Student testUser = new Student();
            JsonSaver saver = new JsonSaver("./data/TestWith\0illegal:fileName.json");
            saver.open();
            fail("FileNotFoundException has to thrown to be valid");
        } catch (FileNotFoundException e) {
            // If the Exception was caught, the test is passing
        }
    }


    @Test
    void testWriteStudentWithNoCourse() {
        try {
            Student userTest = new Student();
            JsonSaver saver = new JsonSaver("./data/studentWriteWithNoCourse.json");
            saver.open();
            saver.write(userTest);
            saver.close();

            JsonLoader reader = new JsonLoader("./data/studentWriteWithNoCourse.json");
                Student testUser = reader.read();
                assertEquals(emptyList, testUser.getFirstYearCourses());
                assertEquals(emptyList, testUser.getSecondYearCourses());
                assertEquals(emptyList, testUser.getThirdYearCourses());
                assertEquals(emptyList, testUser.getFourthYearCourses());
                assertEquals(emptyListOfList, testUser.getListOfListOfCourses());
            } catch (IOException e) {
                fail("Could not properly read the file");
            }
        }

    @Test
    void testWriteStudentWithCourse() {
        try {
            Student userTest = new Student();
            Course firstCourse = new Course("cpsc 110", "gregor", 4, 1, 94,
                    1, 10, "For Test 1");
            Course secondCourse = new Course("cpsc 210", "steven wolfman", 4, 2, 100,
                    2, 10, "For Test 2");
            Course thirdCourse = new Course("cpsc 310", "amy", 4, 3, 100,
                    2, 10, "For Test 3");
            Course fourthCourse = new Course("cpsc 410", "anica", 4, 4, 100,
                    1, 10, "For Test 4");
            userTest.addFirstYearCourses(firstCourse);
            userTest.addSecondYearCourses(secondCourse);
            userTest.addThirdYearCourses(thirdCourse);
            userTest.addFourthYearCourses(fourthCourse);
            JsonSaver saver = new JsonSaver("./data/studentWriteWithCourse.json");
            saver.open();
            saver.write(userTest);
            saver.close();

            JsonLoader reader = new JsonLoader("./data/studentWriteWithCourse.json");
            userTest = reader.read();
            Course userFirstYearCourse = userTest.getFirstYearCourses().get(0);
            Course userSecondYearCourse = userTest.getSecondYearCourses().get(0);
            Course userThirdYearCourse = userTest.getThirdYearCourses().get(0);
            Course userFourthYearCourse = userTest.getFourthYearCourses().get(0);
            compareCourses(userFirstYearCourse, firstCourse);
            compareCourses(userSecondYearCourse, secondCourse);
            compareCourses(userThirdYearCourse, thirdCourse);
            compareCourses(userFourthYearCourse, fourthCourse);


        } catch (IOException e) {
            fail("The file was not able to be read properly");
        }
    }


    private void compareCourses(Course userCourse, Course expectedCourse){
        assertEquals(userCourse.getCourseSummary(), expectedCourse.getCourseSummary());
        assertEquals(userCourse.getCourseName(), expectedCourse.getCourseName());
        assertEquals(userCourse.getYear(), expectedCourse.getYear());
        assertEquals(userCourse.getRating(), expectedCourse.getRating());
        assertEquals(userCourse.getCredit(), expectedCourse.getCredit());
        assertEquals(userCourse.getFinalMark(), expectedCourse.getFinalMark());
        assertEquals(userCourse.getTerm(), expectedCourse.getTerm());
        assertEquals(userCourse.getProfessorName(), expectedCourse.getProfessorName());


    }



}
