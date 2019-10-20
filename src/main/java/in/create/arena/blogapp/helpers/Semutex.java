package in.create.arena.blogapp.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Semutex {
    private static Semaphore librarySemaphore = new Semaphore(1);

    private static class LibraryResource implements Runnable {

        private String name;

        LibraryResource(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " is acquiring lock...");
                librarySemaphore.acquire();
                System.out.println(name + ": Remaining locks => " + librarySemaphore.availablePermits());
                for (int i=0; i<4; i++) {
                    System.out.println(name + " is executing now. Remaining locks to acquire => " + librarySemaphore.availablePermits());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException iex) {
                iex.printStackTrace();
            } finally {
                System.out.println(name + " is releasing lock...");
                librarySemaphore.release();
                System.out.println(name + ": Remaining locks after release => " + librarySemaphore.availablePermits());
            }
        }
    }

    public static void main(String[] args) {
        List<Thread> studentsThread = new ArrayList<Thread>();
        studentsThread.add(new Thread(new LibraryResource("Larry")));
        studentsThread.add(new Thread(new LibraryResource("Serge")));
        studentsThread.add(new Thread(new LibraryResource("Jeff")));
        studentsThread.add(new Thread(new LibraryResource("Elon")));
        studentsThread.add(new Thread(new LibraryResource("Sundar")));
        studentsThread.add(new Thread(new LibraryResource("Satya")));

        Long startTime = new Date().getTime();
        for(Thread student: studentsThread) {
            student.start();
        }
        try {
            for(Thread student: studentsThread) {
                student.join();
            }
        } catch (InterruptedException iox) {
            iox.printStackTrace();
        }
        Long finishTime = new Date().getTime();
        System.out.println("Total milliseconds required: " + (finishTime - startTime));
    }
}
