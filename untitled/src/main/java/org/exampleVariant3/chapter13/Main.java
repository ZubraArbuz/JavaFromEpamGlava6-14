package org.exampleVariant3.chapter13;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/schedule_db";
        String user = "postgres";
        String password = "q1w2e3";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите действие:");
            System.out.println("1. Найти преподавателей по дню недели и аудитории");
            System.out.println("2. Найти преподавателей, которые не работают в определенный день");
            System.out.println("3. Найти дни недели с заданным количеством занятий");
            System.out.println("4. Найти дни недели с заданным количеством аудиторий");
            System.out.println("5. Перенести первое занятие на последнее место");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("Введите день недели (например, Monday):");
                    String day = scanner.nextLine();
                    System.out.println("Введите номер аудитории (например, 101):");
                    String classroom = scanner.nextLine();
                    getTeachersByDayAndClassroom(connection, day, classroom);
                }
                case 2 -> {
                    System.out.println("Введите день недели (например, Monday):");
                    String day = scanner.nextLine();
                    getTeachersNotWorkingOnDay(connection, day);
                }
                case 3 -> {
                    System.out.println("Введите количество занятий:");
                    int classCount = scanner.nextInt();
                    getDaysWithSpecificClassCount(connection, classCount);
                }
                case 4 -> {
                    System.out.println("Введите количество аудиторий:");
                    int roomCount = scanner.nextInt();
                    getDaysWithSpecificRoomCount(connection, roomCount);
                }
                case 5 -> {
                    System.out.println("Введите день недели (например, Monday):");
                    String day = scanner.nextLine();
                    shiftFirstClassToLast(connection, day);
                }
                default -> System.out.println("Неверный выбор");
            }
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getTeachersByDayAndClassroom(Connection connection, String day, String classroom) {
        String query = """
            SELECT t.full_name, s.name, s.time, c.room_number
            FROM Teachers t
            JOIN TeacherSubjects ts ON t.id = ts.teacher_id
            JOIN Subjects s ON ts.subject_id = s.id
            JOIN Classrooms c ON s.classroom_id = c.id
            WHERE s.time LIKE ? AND c.room_number = ?;
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, day + "%");
            statement.setString(2, classroom);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Преподаватель: " + resultSet.getString("full_name"));
                System.out.println("Предмет: " + resultSet.getString("name"));
                System.out.println("Время: " + resultSet.getString("time"));
                System.out.println("Аудитория: " + resultSet.getString("room_number"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getTeachersNotWorkingOnDay(Connection connection, String day) {
        String query = """
            SELECT t.full_name 
            FROM Teachers t
            WHERE t.id NOT IN (
                SELECT ts.teacher_id
                FROM TeacherSubjects ts
                JOIN Subjects s ON ts.subject_id = s.id
                WHERE s.time LIKE ?
            );
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, day + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Преподаватель не работает: " + resultSet.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getDaysWithSpecificClassCount(Connection connection, int classCount) {
        String query = """
            SELECT s.time, COUNT(s.id) AS class_count
            FROM Subjects s
            GROUP BY s.time
            HAVING COUNT(s.id) = ?;
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, classCount);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("День: " + resultSet.getString("time") + ", Class Count: " + resultSet.getInt("class_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getDaysWithSpecificRoomCount(Connection connection, int roomCount) {
        String query = """
            SELECT s.time, COUNT(DISTINCT c.id) AS room_count
            FROM Subjects s
            JOIN Classrooms c ON s.classroom_id = c.id
            GROUP BY s.time
            HAVING COUNT(DISTINCT c.id) = ?;
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomCount);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println("День: " + resultSet.getString("time") + ", Room Count: " + resultSet.getInt("room_count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void shiftFirstClassToLast(Connection connection, String day) {
        String query = """
            UPDATE Subjects
            SET time = CONCAT(time, ' (shifted)')
            WHERE id = (
                SELECT id FROM Subjects
                WHERE time LIKE ? 
                ORDER BY time ASC
                LIMIT 1
            );
        """;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, day + "%");
            int updatedRows = statement.executeUpdate();

            if (updatedRows > 0) {
                System.out.println("Первое занятие в день " + day + " перенесено на последнее место.");
            } else {
                System.out.println("Занятия для переноса не найдены.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
