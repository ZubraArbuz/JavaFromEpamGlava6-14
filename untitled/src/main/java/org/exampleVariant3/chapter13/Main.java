package org.exampleVariant3.chapter13;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
                    List<String> results = getTeachersByDayAndClassroom(connection, day, classroom);
                    results.forEach(System.out::println);
                }
                case 2 -> {
                    System.out.println("Введите день недели (например, Monday):");
                    String day = scanner.nextLine();
                    List<String> results = getTeachersNotWorkingOnDay(connection, day);
                    results.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.println("Введите количество занятий:");
                    int classCount = scanner.nextInt();
                    List<String> results = getDaysWithSpecificClassCount(connection, classCount);
                    results.forEach(System.out::println);
                }
                case 4 -> {
                    System.out.println("Введите количество аудиторий:");
                    int roomCount = scanner.nextInt();
                    List<String> results = getDaysWithSpecificRoomCount(connection, roomCount);
                    results.forEach(System.out::println);
                }
                case 5 -> {
                    System.out.println("Введите день недели (например, Monday):");
                    String day = scanner.nextLine();
                    boolean success = shiftFirstClassToLast(connection, day);
                    if (success) {
                        System.out.println("Первое занятие в день " + day + " перенесено на последнее место.");
                    } else {
                        System.out.println("Занятия для переноса не найдены.");
                    }
                }
                default -> System.out.println("Неверный выбор");
            }
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getTeachersByDayAndClassroom(Connection connection, String day, String classroom) {
        String query = """
            SELECT t.full_name, s.name, s.time, c.room_number
            FROM Teachers t
            JOIN TeacherSubjects ts ON t.id = ts.teacher_id
            JOIN Subjects s ON ts.subject_id = s.id
            JOIN Classrooms c ON s.classroom_id = c.id
            WHERE s.time LIKE ? AND c.room_number = ?;
        """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, day + "%");
            statement.setString(2, classroom);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String result = String.format("Преподаватель: %s, Предмет: %s, Время: %s, Аудитория: %s",
                        resultSet.getString("full_name"),
                        resultSet.getString("name"),
                        resultSet.getString("time"),
                        resultSet.getString("room_number"));
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List<String> getTeachersNotWorkingOnDay(Connection connection, String day) {
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
        List<String> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, day + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                results.add("Преподаватель не работает: " + resultSet.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List<String> getDaysWithSpecificClassCount(Connection connection, int classCount) {
        String query = """
            SELECT s.time, COUNT(s.id) AS class_count
            FROM Subjects s
            GROUP BY s.time
            HAVING COUNT(s.id) = ?;
        """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, classCount);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String result = String.format("День: %s, Количество занятий: %d",
                        resultSet.getString("time"),
                        resultSet.getInt("class_count"));
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List<String> getDaysWithSpecificRoomCount(Connection connection, int roomCount) {
        String query = """
            SELECT s.time, COUNT(DISTINCT c.id) AS room_count
            FROM Subjects s
            JOIN Classrooms c ON s.classroom_id = c.id
            GROUP BY s.time
            HAVING COUNT(DISTINCT c.id) = ?;
        """;
        List<String> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomCount);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String result = String.format("День: %s, Количество аудиторий: %d",
                        resultSet.getString("time"),
                        resultSet.getInt("room_count"));
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static boolean shiftFirstClassToLast(Connection connection, String day) {
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
            return updatedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
