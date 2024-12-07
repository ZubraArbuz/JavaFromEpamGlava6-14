package org.exampleVariant3.chapter12;

public class Car implements Runnable {
    private final String name;
    private final ParkingLot parkingLot;
    private final long waitTime;

    public Car(String name, ParkingLot parkingLot, long waitTime) {
        this.name = name;
        this.parkingLot = parkingLot;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        try {
            if (parkingLot.tryPark(name, waitTime)) {
                Thread.sleep((long) (Math.random() * 5000)); // Имитация времени нахождения на стоянке
                parkingLot.leave(name);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " был прерван.");
            Thread.currentThread().interrupt();
        }
    }
}
