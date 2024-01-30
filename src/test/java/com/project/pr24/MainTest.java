package com.project.pr24;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainOutput() {
        // You need to have a preset environment or mock the database interactions
        // for this to work consistently.
        Main.main(new String[]{});
        String expectedOutput = "1: LL00B0, Editorial 0\r\n" + //
                "2: LL01B0, Editorial 1\r\n" + //
                "3: LL02B0, Editorial 2\r\n" + //
                "4: LL03B0, Editorial 3\r\n" + //
                "5: LL04B0, Editorial 4\r\n" + //
                "6: LL00B1, Editorial 0\r\n" + //
                "7: LL01B1, Editorial 1\r\n" + //
                "8: LL02B1, Editorial 2\r\n" + //
                "9: LL03B1, Editorial 3\r\n" + //
                "10: LL04B1, Editorial 4\r\n" + //
                "11: LL00B2, Editorial 0\r\n" + //
                "12: LL01B2, Editorial 1\r\n" + //
                "13: LL02B2, Editorial 2\r\n" + //
                "14: LL03B2, Editorial 3\r\n" + //
                "15: LL04B2, Editorial 4\r\n" + //
                "1: Biblio 0, Ciutat 0, Llibres: [1, Editorial 0, LL00B0, 1 | 2, Editorial 1, LL01B0, 2 | 3, Editorial 2, LL02B0, 3 | 4, Editorial 3, LL03B0, 4 | 5, Editorial 4, LL04B0, ]\r\n" + //
                "2: Biblio 1, Ciutat 1, Llibres: [6, Editorial 0, LL00B1, 1 | 7, Editorial 1, LL01B1, 2 | 8, Editorial 2, LL02B1, 3 | 9, Editorial 3, LL03B1, 4 | 10, Editorial 4, LL04B1, ]\r\n" + //
                "3: Biblio 2, Ciutat 2, Llibres: [11, Editorial 0, LL00B2, 1 | 12, Editorial 1, LL01B2, 2 | 13, Editorial 2, LL02B2, 3 | 14, Editorial 3, LL03B2, 4 | 15, Editorial 4, LL04B2, ]\r\n" + //
                "1: Persona 0, +34 000 00 00 00, Llibres: [1, Editorial 0, LL00B0, 1 | 2, Editorial 1, LL01B0, 2 | 3, Editorial 2, LL02B0, ]\r\n" + //
                "2: Persona 1, +34 000 00 00 01, Llibres: [6, Editorial 0, LL00B1, 1 | 7, Editorial 1, LL01B1, 2 | 8, Editorial 2, LL02B1, ]\r\n" + //
                "3: Persona 2, +34 000 00 00 02, Llibres: [4, Editorial 3, LL03B0, 4 | 5, Editorial 4, LL04B0, ]\r\n" + //
                "4: Persona 3, +34 000 00 00 03, Llibres: [9, Editorial 3, LL03B1, 4 | 10, Editorial 4, LL04B1, ]\r\n" + //
                "5: Persona 4, +34 000 00 00 04, Llibres: [11, Editorial 0, LL00B2, 1 | 12, Editorial 1, LL01B2, ]\r\n" + //
                "6: Persona 5, +34 000 00 00 05, Llibres: [13, Editorial 2, LL02B2, ]\r\n" + //
                "7: Persona 6, +34 000 00 00 06, Llibres: [14, Editorial 3, LL03B2, ]\r\n" + //
                "8: Persona 7, +34 000 00 00 07, Llibres: [15, Editorial 4, LL04B2, ]\r\n" + //
                "9: Persona 8, +34 000 00 00 08, Llibres: []\r\n" + //
                "10: Persona 9, +34 000 00 00 09, Llibres: []\r\n" + //
                "1: Autor 0, Items: [1, Editorial 0, LL00B0, 1 | 6, Editorial 0, LL00B1, 1 | 11, Editorial 0, LL00B2, ]\r\n" + //
                "2: Autor 1, Items: [2, Editorial 1, LL01B0, 2 | 7, Editorial 1, LL01B1, 2 | 12, Editorial 1, LL01B2, ]\r\n" + //
                "3: Autor 2, Items: [3, Editorial 2, LL02B0, 3 | 8, Editorial 2, LL02B1, 3 | 13, Editorial 2, LL02B2, ]\r\n" + //
                "4: Autor 3, Items: [4, Editorial 3, LL03B0, 4 | 9, Editorial 3, LL03B1, 4 | 14, Editorial 3, LL03B2, ]\r\n" + //
                "5: Autor 4, Items: [5, Editorial 4, LL04B0, 5 | 10, Editorial 4, LL04B1, 5 | 15, Editorial 4, LL04B2, ]";

        // Normalize newlines for both expected and actual output
        String normalizedExpectedOutput = normalizeNewlines(expectedOutput);
        String normalizedActualOutput = normalizeNewlines(outContent.toString().trim());

        // Compare the normalized outputs
        assertEquals(normalizedExpectedOutput, normalizedActualOutput);
    }

    private String normalizeNewlines(String input) {
        return input.replace("\r\n", "\n").replace("\r", "\n");
    }
}
