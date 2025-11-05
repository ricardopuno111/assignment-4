import java.util.concurrent.atomic.AtomicIntegerArray;

public class Knuth extends CriticalSection_Base {
    
    private final AtomicIntegerArray flag; // 0 = idle, 1 = requesting, 2 = in cs
    private final AtomicIntegerArray turn;
    private final int n;

    public Knuth(int n) {
        this.n = n;
        this.flag = new AtomicIntegerArray(n);
        for (int i = 0; i < n; i++) flag.set(i, 0);
        this.turn = new AtomicIntegerArray(0);
        turn.set(0, 0);
    }

    @Override
    public void EntrySection(Worker thread) {
        int i = thread.ID;
        flag.set(i, 1); // requesting

        int j = turn.get();
        while (j != i) {
            if (flag.get(j) != 0) {
                j = turn.get();
            } else {
                j = (j - 1 + n) %n ;
            }
            Thread.onSpinWait();
        }

        flag.set(i, 2); // in cs

        boolean conflict;
        do {
            conflict = false;
            for (int k = 0; k < n; k++) {
                if (k != i&& flag.get(k) == 2) {
                    conflict = true;
                    break;
                }
            }
            if (conflict) Thread.onSpinWait();
        }   while (conflict);

        turn.set(i);
    }

    @Override
    public void ExitSection(Worker thread) {
        int i = thread.ID;
        turn.set((i -1 + n) % n);
        flag.set(i, 0);
    }
}
