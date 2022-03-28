
import domain.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.StudentXMLRepository;
import validation.StudentValidator;
import validation.ValidationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.Assert.*;


public class AddStudentTestClass {

    private static final String STUDENTS_FILE = "src/test/resources/students/studenti.xml";

    private StudentXMLRepository studentXMLRepository;

    @Before
    public void setUp() throws IOException {
        Files.write(
                Path.of(STUDENTS_FILE), Collections.singleton(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                                "<Entitati>\n" +
                                "</Entitati>"
                )
        );
        studentXMLRepository = new StudentXMLRepository(new StudentValidator(), STUDENTS_FILE);
    }


    @Test
    public void save_returnsNull_whenSavingNewValidStudent() {
        final Student student = new Student("3", "Dora", 935);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsSavedStudent_whenSavingExistingStudent() {
        final Student student = new Student("3", "Dora", 935);
        studentXMLRepository.save(student);
        final Student savedStudent = studentXMLRepository.save(student);
        assertEquals(student, savedStudent);
    }

    @Test
    public void save_returnsNull_whenIDIsNull() {
        final Student student = new Student(null, "Dora", 935);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenIDIsEmpty() {
        final Student student = new Student("", "Dora", 935);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenNumeIsNull() {
        final Student student = new Student("1", null, 935);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenNumeIsEmpty() {
        final Student student = new Student("1", "", 935);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenGrupaIsLessThanTheLeftBoundary() {
        final Student student = new Student("1", "Dora", 109);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenGrupaIsTheLeftBoundary() {
        final Student student = new Student("1", "Dora", 110);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenGrupaIsGreaterThanTheLeftBoundary() {
        final Student student = new Student("1", "Dora", 111);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenGrupaIsLessThanTheRightBoundary() {
        final Student student = new Student("1", "Dora", 937);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenGrupaIsTheRightBoundary() {
        final Student student = new Student("1", "Dora", 938);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }

    @Test
    public void save_returnsNull_whenGrupaIsGreaterThanTheRightBoundary() {
        final Student student = new Student("1", "Dora", 939);
        final Student savedStudent = studentXMLRepository.save(student);
        assertNull(savedStudent);
    }


//    @After
//    public void tearDown() {
//        new File(STUDENTS_FILE).delete();
//    }
}
