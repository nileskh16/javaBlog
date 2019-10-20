package in.create.arena.blogapp.helpers;

public class HelperMain {

    public static void main(String[] args) {
        runKruskal();
    }

    private static void runThreads() {
        ParkingCash cash = new ParkingCash();
        ParkingStats stats = new ParkingStats(cash);
        int numOfCores = Runtime.getRuntime().availableProcessors();
        Thread[] allThreads = new Thread[numOfCores];
        for (int i=0; i<numOfCores; i++) {
            ParkingSensors sensor = new ParkingSensors(stats);
            Thread t = new Thread(sensor);
            allThreads[i] = t;
            t.start();
        }

        for(Thread t: allThreads) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        System.out.printf("Num of cars: %d\nNum of bikes: %d\n",
                stats.getNumOfCars(),
                stats.getNumOfBikes());
        cash.close();
    }

    private static void runCrawlDir() {
        AllAboutDirectory abDir = new AllAboutDirectory("\\\\PKOTHALE-T460\\Team Docs\\Monthly Plan");
        abDir.crawlDirectory();
    }

    private static void runKruskal() {
        MSTKruskal mstKruskal = new MSTKruskal(6);
        mstKruskal.demoKruskalAlgo();
    }
}
