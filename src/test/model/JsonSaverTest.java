package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonLoader;
import persistence.JsonSaver;

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
            fail("IOException has to thrown to be valid");
        } catch (IOException e) {
            // If the Exception was caught, the test is passing
        }
    }


    @Test
    void testWriteStudentWithNoCourse() {
        try {
            Student userTest = new Student();
            JsonSaver saver = new JsonSaver("./data/studentWithNoCourse");
            saver.open();
            saver.write(userTest);
            saver.close();

            JsonLoader reader = new JsonLoader("./data/studentWithNoCourse");
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
            wr.addThingy(new Thingy("saw", Category.METALWORK));
            wr.addThingy(new Thingy("needle", Category.STITCHING));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(wr);
            writer.close();
        }




}
