package in.create.arena.blogapp.helpers;

public class ParkingCash {

    private static final int COST = 2;
    private int cash = 0;
    public synchronized void cashPay() {
        cash += COST;
    }

    public void close() {
        int totalCash = 0;
        totalCash = cash;
        cash = 0;
        System.out.printf("Final cash before closing for the day is => %d", totalCash);
    }
}
