package org.example.chapter7;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    @Test
    void testUserSortingByCountryAndAge() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice", 25, "USA"));
        users.add(new User(2, "Bob", 30, "USA"));
        users.add(new User(3, "Charlie", 22, "UK"));
        users.add(new User(4, "David", 28, "USA"));
        users.add(new User(5, "Eva", 35, "UK"));
        users.add(new User(6, "Frank", 27, "Canada"));
        users.add(new User(7, "George", 40, "Canada"));

        users.sort(User.byCountryAndAge());

        List<User> expectedOrder = List.of(
                new User(6, "Frank", 27, "Canada"),
                new User(7, "George", 40, "Canada"),
                new User(3, "Charlie", 22, "UK"),
                new User(5, "Eva", 35, "UK"),
                new User(1, "Alice", 25, "USA"),
                new User(4, "David", 28, "USA"),
                new User(2, "Bob", 30, "USA")
        );

        for (int i = 0; i < users.size(); i++) {
            assertEquals(expectedOrder.get(i).getId(), users.get(i).getId());
        }
    }

    @Test
    void testFilterUsersByAgeAndInitial() {
        List<User> users = List.of(
                new User(1, "Alice", 25, "USA"),
                new User(2, "George", 35, "Canada"),
                new User(3, "Charlie", 22, "UK"),
                new User(4, "George", 40, "Canada"),
                new User(5, "Eva", 30, "UK")
        );

        int minAge = 30;
        char initial = 'G';
        List<User> filteredUsers = users.stream()
                .filter(user -> user.getAge() > minAge && user.getName().charAt(0) == initial)
                .collect(Collectors.toList());

        assertEquals(2, filteredUsers.size());

        List<Integer> filteredIds = filteredUsers.stream()
                .map(User::getId)
                .collect(Collectors.toList());
        assertTrue(filteredIds.contains(2));
        assertTrue(filteredIds.contains(4));
    }


    @Test
    void testFindMinAndMaxAgeAmongFilteredUsers() {
        List<User> users = List.of(
                new User(1, "Alice", 25, "USA"),
                new User(2, "George", 35, "Canada"),
                new User(3, "Charlie", 22, "UK"),
                new User(4, "George", 40, "Canada"),
                new User(5, "Eva", 30, "UK")
        );

        int minAge = 30;
        char initial = 'G';
        List<User> filteredUsers = users.stream()
                .filter(user -> user.getAge() > minAge && user.getName().charAt(0) == initial)
                .collect(Collectors.toList());

        User maxAgeUser = filteredUsers.stream().max(Comparator.comparing(User::getAge)).orElseThrow();
        User minAgeUser = filteredUsers.stream().min(Comparator.comparing(User::getAge)).orElseThrow();

        assertEquals(4, maxAgeUser.getId());
        assertEquals(2, minAgeUser.getId());
    }

    @Test
    void testNoFilteredUsers() {
        List<User> users = List.of(
                new User(1, "Alice", 25, "USA"),
                new User(3, "Charlie", 22, "UK"),
                new User(5, "Eva", 30, "UK")
        );

        int minAge = 30;
        char initial = 'G';
        List<User> filteredUsers = users.stream()
                .filter(user -> user.getAge() > minAge && user.getName().charAt(0) == initial)
                .collect(Collectors.toList());

        assertTrue(filteredUsers.isEmpty());
    }
}
