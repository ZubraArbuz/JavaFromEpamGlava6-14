package org.example.chapter7;

import java.util.*;
import java.util.stream.Collectors;

public class UserManager {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", 25, "USA"));
        users.add(new User(2, "Bob", 30, "USA"));
        users.add(new User(3, "Charlie", 22, "UK"));
        users.add(new User(4, "David", 28, "USA"));
        users.add(new User(5, "Eva", 35, "UK"));
        users.add(new User(6, "Frank", 27, "Canada"));
        users.add(new User(7, "George", 40, "Canada"));

        users.sort(User.byCountryAndAge());
        System.out.println("Отсортированные пользователи:");
        users.forEach(System.out::println);

        int minAge = 30;
        char initial = 'G';
        List<User> filteredUsers = users.stream()
                .filter(user -> user.getAge() > minAge && user.getName().charAt(0) == initial)
                .collect(Collectors.toList());
        System.out.println("\nПользователи старше " + minAge + " лет, чье имя начинается на '" + initial + "':");
        filteredUsers.forEach(System.out::println);

        if (!filteredUsers.isEmpty()) {
            User maxAgeUser = filteredUsers.stream().max(Comparator.comparing(User::getAge)).orElseThrow();
            User minAgeUser = filteredUsers.stream().min(Comparator.comparing(User::getAge)).orElseThrow();
            System.out.println("\nМаксимальный возраст среди отфильтрованных пользователей: " + maxAgeUser);
            System.out.println("Минимальный возраст среди отфильтрованных пользователей: " + minAgeUser);
        } else {
            System.out.println("\nНет пользователей, удовлетворяющих условиям фильтрации.");
        }
    }
}
