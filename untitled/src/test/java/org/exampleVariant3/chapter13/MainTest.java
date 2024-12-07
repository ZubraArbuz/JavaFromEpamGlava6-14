package org.exampleVariant3.chapter13;

import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainTest {
    private Connection connection;

    @BeforeAll
    void setupDatabase() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/test_schedule_db";
        String user = "postgres";
        String password = "q1w2e3";
        connection = DriverManager.getConnection(url, user, password);
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS Teachers (
                    id SERIAL PRIMARY KEY,
                    full_name VARCHAR(255) NOT NULL
                );
                CREATE TABLE IF NOT EXISTS Classrooms (
                    id SERIAL PRIMARY KEY,
                    room_number VARCHAR(10) NOT NULL
                );
                CREATE TABLE IF NOT EXISTS Subjects (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    time VARCHAR(255) NOT NULL,
                    classroom_id INT,
                    FOREIGN KEY (classroom_id) REFERENCES Classrooms(id)
                );
                CREATE TABLE IF NOT EXISTS TeacherSubjects (
                    id SERIAL PRIMARY KEY,
                    teacher_id INT,
                    subject_id INT,
                    FOREIGN KEY (teacher_id) REFERENCES Teachers(id),
                    FOREIGN KEY (subject_id) REFERENCES Subjects(id)
                );
            """);
        }
    }

    @BeforeEach
    void populateDatabase() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("TRUNCATE TABLE TeacherSubjects, Subjects, Teachers, Classrooms RESTART IDENTITY;");
            stmt.execute("""
                INSERT INTO Teachers (full_name) VALUES ('Ivanov Ivan Ivanovich'), ('Petrov Petr Petrovich');
                INSERT INTO Classrooms (room_number) VALUES ('101'), ('102');
                INSERT INTO Subjects (name, time, classroom_id) VALUES 
                    ('Math', 'Monday 08:00', 1),
                    ('Science', 'Monday 10:00', 2);
                INSERT INTO TeacherSubjects (teacher_id, subject_id) VALUES 
                    (1, 1),
                    (2, 2);
            """);
        }
    }

    @Test
    void testGetTeachersByDayAndClassroom() {
        assertDoesNotThrow(() -> {
            Main.getTeachersByDayAndClassroom(connection, "Monday", "101");
            // Проверка логики или выводов.
        });
    }

    @Test
    void testGetTeachersNotWorkingOnDay() {
        assertDoesNotThrow(() -> {
            Main.getTeachersNotWorkingOnDay(connection, "Tuesday");
        });
    }

    @Test
    void testGetDaysWithSpecificClassCount() {
        assertDoesNotThrow(() -> {
            Main.getDaysWithSpecificClassCount(connection, 1);
        });
    }

    @Test
    void testGetDaysWithSpecificRoomCount() {
        assertDoesNotThrow(() -> {
            Main.getDaysWithSpecificRoomCount(connection, 1);
        });
    }

    @Test
    void testShiftFirstClassToLast() {
        assertDoesNotThrow(() -> {
            Main.shiftFirstClassToLast(connection, "Monday");
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT time FROM Subjects WHERE id = 1");
                assertTrue(rs.next());
                assertTrue(rs.getString("time").contains("(shifted)"));
            }
        });
    }

    @AfterAll
    void closeDatabase() throws SQLException {
        connection.close();
    }
}
