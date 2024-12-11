package org.exampleVariant3.chapter13;

class Subject {
    private String name;
    private String time;
    private Classroom classroom;

    public Subject(String name, String time, Classroom classroom) {
        this.name = name;
        this.time = time;
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Название: " + name + ", Время: " + time + ", Аудитория: " + classroom;
    }
}