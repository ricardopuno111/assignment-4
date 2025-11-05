import java.util.concurrent.CountDownLatch;

public class Worker extends Thread {
    private CriticalSection_Base Section;
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    //java threads have an ID already, however you can use this to make sure you have control over the starting value and increment
    public final int ID;
    public double turnaroundTime = 0;

    public Worker(CriticalSection_Base Section, int ID, 
                  CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.Section = Section;
        this.ID = ID;
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    @Override
     public void run() {
        try {
            // Wait until main thread signals all workers to start
            startSignal.await();

            // Each worker enters CS multiple times to simulate workload
            for (int i = 0; i < 50; i++) {
                Section.EntrySection(this);
                Section.CriticalSection(this);
                Section.ExitSection(this);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            doneSignal.countDown();
        }
    }
}
