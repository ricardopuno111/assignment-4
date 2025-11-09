import java.util.concurrent.CountDownLatch;

public class Server {
    public static void main(String[] args) {
        //Write your code here to create your threads and test the 3 different algorithms
        //When making worker threads it is recommended for ID to start at 0 and increment by 1 

        // Peterson's
        System.out.println("CS Solution 1 – Peterson’s (time in milli-seconds)");
        System.out.println("Threads\tAVG TAT");

        /* 

        for (int numThreads = 2; numThreads <= 100; numThreads++) {

            Peterson section = new Peterson(numThreads);

            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch doneSignal = new CountDownLatch(numThreads);
            Worker[] workers = new Worker[numThreads];

            // Create workers
            for (int i = 0; i < numThreads; i++) {
                workers[i] = new Worker(section, i, startSignal, doneSignal);
                workers[i].start();
            }

            // Start timing and release all threads
            long startNs = System.nanoTime();
            startSignal.countDown();  // let all workers begin at once

            // Wait for all workers to finish
            try {
                doneSignal.await(); // wait for all threads to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endNs = System.nanoTime();

            // Compute average turnaround time in ms
            double totalMs = (endNs - startNs) / 1_000_000.0;
            double avgTAT = totalMs / numThreads;

            System.out.printf("%d\t%.3f ms%n", numThreads, avgTAT);

        }
        
        System.out.println();

        System.out.println("CS Solution 2 – Knuth’s (time in milli-seconds)");
        System.out.println("Threads\tAVG TAT");

        for (int numThreads = 2; numThreads <= 100; numThreads++) {

            Knuth section = new Knuth(numThreads);

            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch doneSignal = new CountDownLatch(numThreads);
            Worker[] workers = new Worker[numThreads];

            for (int i = 0; i < numThreads; i++) {
                workers[i] = new Worker(section, i, startSignal, doneSignal);
                workers[i].start();
            }

            long startNs = System.nanoTime();
            startSignal.countDown();

            try {
                doneSignal.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endNs = System.nanoTime();
            double totalMs = (endNs - startNs) / 1000.0;
            double avgTAT = totalMs / numThreads;

            System.out.printf("%d\t%.3f ms%n", numThreads, avgTAT);

        }
            */

        System.out.println();

        System.out.println("CS Solution 3 – De Bruijin's (time in milli-seconds)");
        System.out.println("Threads\tAVG TAT");

        for (int numThreads = 2; numThreads <= 100; numThreads++) {

            DeBruijin section = new DeBruijin(numThreads);

            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch doneSignal = new CountDownLatch(numThreads);
            Worker[] workers = new Worker[numThreads];

            for (int i = 0; i < numThreads; i++) {
                workers[i] = new Worker(section, i, startSignal, doneSignal);
                workers[i].start();
            }

            long startNs = System.nanoTime();
            startSignal.countDown();

            try {
                doneSignal.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endNs = System.nanoTime();
            double totalMs = (endNs - startNs) / 1000.0;
            double avgTAT = totalMs / numThreads;

            System.out.printf("%d\t%.3f ms%n", numThreads, avgTAT);

        }

    }
}


