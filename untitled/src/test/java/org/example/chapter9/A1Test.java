package org.example.chapter9;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.*;


class A1Test {

    @Test
    void parseLine_validInput_returnsBigDecimal() throws ParseException, A1.InvalidNumberException {
        String line = "1234.56;en-US";
        BigDecimal result = A1.parseLine(line);
        assertEquals(new BigDecimal("1234.56"), result);
    }

    @Test
    void parseLine_invalidFormat_throwsException() {
        String line = "1234.56";
        assertThrows(A1.InvalidNumberException.class, () -> A1.parseLine(line));
    }

    @Test
    void parseLine_invalidLocale_throwsException() {
        String line = "1234.56,invalid-locale";
        assertThrows(A1.InvalidNumberException.class, () -> A1.parseLine(line));
    }

    @Test
    void parseLine_numberOutOfRange_throwsException() {
        String line = "1e2,en-US";
        assertThrows(A1.InvalidNumberException.class, () -> A1.parseLine(line));
    }
}
