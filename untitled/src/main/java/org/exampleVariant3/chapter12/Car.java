package org.exampleVariant3.chapter12;

public class Car implements Runnable {
    private final String name;
    private final ParkingLot parkingLot;
    private final long waitTime;
    private final long startTime;

    public Car(String name, ParkingLot parkingLot, long waitTime, long startTime) {
        this.name = name;
        this.parkingLot = parkingLot;
        this.waitTime = waitTime;
        this.startTime = startTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 3000));

            printTimeMessage(name + " подъехала к парковке.");

            if (parkingLot.tryPark(name, waitTime)) {
                Thread.sleep((long) (Math.random() * 5000)); // Имитация времени нахождения на стоянке
                parkingLot.leave(name);
            }
        } catch (InterruptedException e) {
            printTimeMessage(name + " был прерван.");
            Thread.currentThread().interrupt();
        }
    }

    private void printTimeMessage(String message) {
        long currentTime = (System.currentTimeMillis() - startTime) / 1000;
        System.out.println("[" + currentTime + " сек] " + message);
    }
}
