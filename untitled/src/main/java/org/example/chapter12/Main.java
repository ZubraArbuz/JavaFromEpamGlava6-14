package org.example.chapter12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int parkingCapacity = 3; // Количество мест на парковке
        int carsCount = 10; // Количество машин
        long maxWaitTime = 2; // Максимальное время ожидания (в секундах)

        ParkingLot parkingLot = new ParkingLot(parkingCapacity);
        ExecutorService executorService = Executors.newFixedThreadPool(carsCount);

        for (int i = 1; i <= carsCount; i++) {
            executorService.execute(new Car("Машина " + i, parkingLot, maxWaitTime));
        }

        executorService.shutdown();
    }
}
