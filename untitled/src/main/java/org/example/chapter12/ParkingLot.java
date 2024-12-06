package org.example.chapter12;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ParkingLot {
    private final Semaphore parkingSpaces;

    public ParkingLot(int capacity) {
        this.parkingSpaces = new Semaphore(capacity);
    }

    public boolean tryPark(String carName, long waitTimeSeconds) throws InterruptedException {
        System.out.println(carName + " пытается найти место...");
        if (parkingSpaces.tryAcquire(waitTimeSeconds, TimeUnit.SECONDS)) {
            System.out.println(carName + " припарковался.");
            return true;
        } else {
            System.out.println(carName + " не смог найти место и уехал.");
            return false;
        }
    }

    public void leave(String carName) {
        System.out.println(carName + " покидает парковку.");
        parkingSpaces.release();
    }
}
