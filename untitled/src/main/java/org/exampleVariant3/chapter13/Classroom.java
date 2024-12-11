package org.exampleVariant3.chapter13;

class Classroom {
    private String roomNumber;

    public Classroom(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return "Номер: " + roomNumber;
    }
}
