public class Server {
    public static void main(String[] args) {
        //Write your code here to create your threads and test the 3 different algorithms
        //When making worker threads it is recommended for ID to start at 0 and increment by 1 
        
        // Peterson's
        System.out.println("CS Solution 1 – Peterson’s (time in milli-seconds)");
        System.out.println("Threads\tAVG TAT");

        for (int numThreads = 2; numThreads <= 100; numThreads++) {
            Peterson section = new Peterson(numThreads);
            Worker[] workers = new Worker[numThreads];

            for (int i = 0; i < numThreads; i++) {
                workers[i] = new Worker(section, i);
            }

            for (Worker w : workers) w.start();
            for (Worker w : workers) {
                try {
                    w.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            long total = 0;
            for (Worker w : workers) total += w.turnaroundTime;
            double avgTAT = (double) total / numThreads;

            System.out.printf("%d\t%.5f%n", numThreads, avgTAT);

        }
        

    }
}


