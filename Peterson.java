import java.util.concurrent.atomic.AtomicIntegerArray;

public class Peterson extends CriticalSection_Base {
    private final AtomicIntegerArray flag;
    private final AtomicIntegerArray turn;
    private final int n;

    public Peterson(int n) {
        this.n = n;
        flag = new AtomicIntegerArray(n);
        for (int i = 0; i < n; i++) flag.set(i, -1);

        turn = new AtomicIntegerArray(Math.max(1, n - 1));
        for (int k = 0; k < Math.max(1, n - 1); k++) turn.set(k, 0);
    }

    @Override
    public void EntrySection(Worker thread) {
        int i = thread.ID;

        // for k = 0 to n-2
        for (int k = 0; k <= n - 2; k++) {
            flag.set(i, k);
            turn.set(k, i);

            // do nothing while there is some process that is at the same or higher level than me an dit's my turn...

        }

    }

}
