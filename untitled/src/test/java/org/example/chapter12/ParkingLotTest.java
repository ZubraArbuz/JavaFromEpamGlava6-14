package org.example.chapter12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot(3);
    }

    @Test
    void testSuccessfulParking() throws InterruptedException {
        boolean parked = parkingLot.tryPark("Машина 1", 2);
        assertTrue(parked, "Машина должна была успешно припарковаться.");
        parkingLot.leave("Машина 1");
    }

    @Test
    void testParkingTimeout() throws InterruptedException {
        parkingLot.tryPark("Машина 1", 2);
        parkingLot.tryPark("Машина 2", 2);
        parkingLot.tryPark("Машина 3", 2);

        boolean parked = parkingLot.tryPark("Машина 4", 1);
        assertFalse(parked, "Машина не должна была припарковаться, т.к. места заняты.");
    }

    @Test
    void testParkingAfterSpaceFreed() throws InterruptedException {
        parkingLot.tryPark("Машина 1", 2);
        parkingLot.tryPark("Машина 2", 2);
        parkingLot.tryPark("Машина 3", 2);

        parkingLot.leave("Машина 1");

        boolean parked = parkingLot.tryPark("Машина 4", 2);
        assertTrue(parked, "Машина должна была припарковаться после освобождения места.");
        parkingLot.leave("Машина 4");
    }

    @Test
    void testConcurrentParking() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 5; i++) {
            int carNumber = i;
            executor.execute(() -> {
                try {
                    boolean parked = parkingLot.tryPark("Машина " + carNumber, 2);
                    if (parked) {
                        Thread.sleep(500);
                        parkingLot.leave("Машина " + carNumber);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(10, TimeUnit.SECONDS);

        assertTrue(finished, "Все потоки должны завершиться.");
    }
}
