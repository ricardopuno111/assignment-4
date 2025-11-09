import java.util.concurrent.atomic.AtomicIntegerArray;

public class Knuth extends CriticalSection_Base {
    
    private static final int IDLE = 0;
    private static final int REQ = 1;
    private static final int CS = 2;

    private final int n;
    private final AtomicIntegerArray flag;
    private volatile int turn;

    public Knuth(int n) {
        this.n = n;
        this.flag = new AtomicIntegerArray(n);
        this.turn = 0;
        for (int i = 0; i < n; i++) {
            flag.set(i, IDLE);
        }
    }

    @Override
    public void EntrySection(Worker thread) {
        int i = thread.ID;
        int j;

        do {
            flag.set(i, REQ);
            j = turn;

            while (j != i) {
                if (flag.get(j) != IDLE)
                    j = turn;
                else
                    j = (j - 1 + n) % n;
            }

            flag.set(i, CS);

            // repeat until all other threads are not IN_CS
        } while (!allOtherFlagsNotInCS(i));

        turn = i;
    }

    @Override
    public void ExitSection(Worker thread) {
        int i = thread.ID;
        turn = (i - 1 + n) % n;
        flag.set(i, IDLE);
    }

    private boolean allOtherFlagsNotInCS(int i) {
        for (int j = 0; j < n; j++) {
            if (j != i && flag.get(j) == CS)
                return false;
        }
        return true;
    }
}