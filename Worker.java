public class Worker extends Thread {
    private CriticalSection_Base Section;
    //java threads have an ID already, however you can use this to make sure you have control over the starting value and increment
    public final int ID;
    public long turnaroundTime = 0;

    public Worker(CriticalSection_Base Section, int ID) {
        this.Section = Section;
        this.ID = ID;
    }

    @Override
    public void run() {
        //This is for a single entry/exit of the CS
        long start = System.currentTimeMillis();
        for (int iter = 0; iter < 5; iter++) {
            Section.EntrySection(this);
            try {
                Section.CriticalSection(this);
            } catch (InterruptedException e) {
                //elevate the exception, this should not happen
                throw new RuntimeException(e);
            }
            Section.ExitSection(this);
        }
        long end = System.currentTimeMillis();
        turnaroundTime = end - start;
    }
}
