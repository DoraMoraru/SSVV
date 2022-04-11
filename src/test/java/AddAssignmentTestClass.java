import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.StudentValidator;
import validation.TemaValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AddAssignmentTestClass {

    private static final String TEME_FILE = "src/test/resources/students/tema.xml";

    private TemaXMLRepository temaXMLRepository;


    @Before
    public void setUp() throws IOException {
        Files.write(
                Path.of(TEME_FILE), Collections.singleton(
                        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                                "<Entitati>\n" +
                                "</Entitati>"
                )
        );
        temaXMLRepository = new TemaXMLRepository(new TemaValidator(), TEME_FILE);
    }

    @Test
    public void save_returnsNull_whenSavingNewValidTema() {
        final Tema tema = new Tema("1", "desc", 2, 1);
        final Tema savedTema = temaXMLRepository.save(tema);
        assertNull(savedTema);
    }

    @Test
    public void save_returnsSavedTema_whenSavingExistingTema() {
        final Tema tema = new Tema("1", "desc2", 3, 2);
        temaXMLRepository.save(tema);
        final Tema savedTema = temaXMLRepository.save(tema);
        assertEquals(tema, savedTema);
    }

    @Test
    public void save_returnsEntity_whenIDIsNull() {
        final Tema tema = new Tema(null, "desc2", 3, 2);
        temaXMLRepository.save(tema);
        final Tema savedTema = temaXMLRepository.save(tema);
        assertEquals(tema, savedTema);
    }

    @Test
    public void save_returnsEntity_whenIDIsEmpty() {
        final Tema tema = new Tema("", "desc2", 3, 2);
        temaXMLRepository.save(tema);
        final Tema savedTema = temaXMLRepository.save(tema);
        assertEquals(tema, savedTema);
    }

    @Test
    public void save_returnsEntity_whenDescriereIsNull() {
        final Tema tema = new Tema("2", null, 3, 2);
        temaXMLRepository.save(tema);
        final Tema savedTema = temaXMLRepository.save(tema);
        assertEquals(tema, savedTema);
    }

    @Test
    public void save_returnsEntity_whenDescriereIsEmpty() {
        final Tema tema = new Tema("2", "", 3, 2);
        temaXMLRepository.save(tema);
        final Tema savedTema = temaXMLRepository.save(tema);
        assertEquals(tema, savedTema);
    }

    @Test
    public void save_returnsEntity_whenDeadlineIsInvalid() {
        final Tema tema = new Tema("2", "desc", 0, 2);
        temaXMLRepository.save(tema);
        final Tema savedTema = temaXMLRepository.save(tema);
        assertEquals(tema, savedTema);
    }

    @Test
    public void save_returnsEntity_whenStartlineIsInvalid() {
        final Tema tema = new Tema("2", "desc", 2, 0);
        temaXMLRepository.save(tema);
        final Tema savedTema = temaXMLRepository.save(tema);
        assertEquals(tema, savedTema);
    }

    @Test
    public void save_returnsEntity_whenStartlineAfterDeadline() {
        final Tema tema = new Tema("2", "desc", 2, 4);
        temaXMLRepository.save(tema);
        final Tema savedTema = temaXMLRepository.save(tema);
        assertEquals(tema, savedTema);
    }
}
