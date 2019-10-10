package in.create.arena.blogapp.helpers;

public class ParkingStats {
    private int numOfCars;
    private int numOfBikes;
    private ParkingCash cash;
    private final Object carObj, bikeObj;

    public ParkingStats(ParkingCash cash) {
        this.cash = cash;
        numOfBikes = 0;
        numOfCars = 0;
        carObj = new Object();
        bikeObj = new Object();
    }

    public void carEnter() {
        synchronized(carObj) {
            numOfCars++;
        }
    }

    public void carExit() {
        synchronized(carObj) {
            numOfCars--;
        }
        cash.cashPay();
    }

    public void bikeEnter() {
        synchronized(bikeObj) {
            numOfBikes++;
        }
    }

    public void bikeExit() {
        synchronized(bikeObj) {
            numOfBikes--;
        }
        cash.cashPay();
    }

    public int getNumOfCars() {
        return numOfCars;
    }

    public int getNumOfBikes() {
        return numOfBikes;
    }
}
