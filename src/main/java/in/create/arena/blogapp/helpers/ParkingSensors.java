package in.create.arena.blogapp.helpers;

import java.util.concurrent.TimeUnit;

public class ParkingSensors implements Runnable {

    private ParkingStats stats;

    public ParkingSensors(ParkingStats stats) {
        this.stats = stats;
    }

    @Override
    public void run() throws ArithmeticException {
        for (int i = 0; i<7; i++) {
            stats.bikeEnter();
            stats.bikeEnter();
            try {
                TimeUnit.MICROSECONDS.sleep(60);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            stats.carEnter();
            stats.carEnter();
            try {
                TimeUnit.MICROSECONDS.sleep(60);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            stats.bikeExit();
            stats.carExit();
            stats.carExit();
            stats.bikeExit();
        }
    }
}
