package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This Test was created based on the source below:
// Carter, Paul (2021) JsonSerializationDemo
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonLoaderTest {
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
    public void testStudentWithCourse(){
        JsonLoader reader = new JsonLoader("./data/studentWithCourseTest.json");
        try {
            Student testUser = reader.read();


            Course firstCourse = new Course("cpsc 110", "gregor", 4, 1, 94,
                    1, 10, "For Test 1");
            Course secondCourse = new Course("cpsc 210", "steven wolfman", 4, 2, 100,
                    2, 10, "For Test 2");
            Course thirdCourse = new Course("cpsc 310", "amy", 4, 3, 100,
                    2, 10, "For Test 3");
            Course fourthCourse = new Course("cpsc 410", "anica", 4, 4, 100,
                    1, 10, "For Test 4");


            Course userFirstYearCourse = testUser.getFirstYearCourses().get(0);
            Course userSecondYearCourse = testUser.getSecondYearCourses().get(0);
            Course userThirdYearCourse = testUser.getThirdYearCourses().get(0);
            Course userFourthYearCourse = testUser.getFourthYearCourses().get(0);
            compareCourses(userFirstYearCourse, firstCourse);
            compareCourses(userSecondYearCourse, secondCourse);
            compareCourses(userThirdYearCourse, thirdCourse);
            compareCourses(userFourthYearCourse, fourthCourse);



        } catch (IOException e) {
            fail("Could not properly read the file");
        }
    }


    @Test
    public void testStudentWithEmptyCourse(){
        JsonLoader reader = new JsonLoader("./data/studentWithNoCourseTest.json");
        try {
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
    public void testFileNotPresent(){
        JsonLoader reader = new JsonLoader("./data/randomFileThatDoesNotExist.json");
        try {
            Student testUser = reader.read();
            fail("IOException should have been present");
        } catch (IOException e) {
            // This catch provides that the test is passing
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


