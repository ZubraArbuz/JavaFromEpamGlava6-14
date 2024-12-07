package org.exampleVariant3.chapter12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int parkingCapacity = 3;
        int carsCount = 10;
        long maxWaitTime = 2;

        ParkingLot parkingLot = new ParkingLot(parkingCapacity);
        ExecutorService executorService = Executors.newFixedThreadPool(carsCount);

        for (int i = 1; i <= carsCount; i++) {
            executorService.execute(new Car("Машина " + i, parkingLot, maxWaitTime));
        }

        executorService.shutdown();
    }
}
