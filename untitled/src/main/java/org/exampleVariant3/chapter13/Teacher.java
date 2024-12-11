package org.exampleVariant3.chapter13;

import java.util.ArrayList;
import java.util.List;

class Teacher {
    private int id;
    private String fullName;
    private List<Subject> subjects;

    public Teacher(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
        this.subjects = new ArrayList<>();
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public String toString() {
        return "Преподаватель: " + fullName + ", Предметы: " + subjects;
    }
}
