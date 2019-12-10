import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class Chronometer {

    private static final int SPLIT_DIV = 10;
    private static final int SECONDS_DIV = SPLIT_DIV * 100;
    private static final int MINUTES_DIV = SECONDS_DIV * 60;
    private static final int HOURS_DIV = MINUTES_DIV * 60;

    private Thread runner;
    private final List<ChronometerListener> listeners = new ArrayList<>();

    synchronized void toggle() {
        if (runner != null && runner.isAlive()) {
            runner.interrupt();
        } else {
            runner = createNewRunner();
            runner.start();
        }
    }

    void addListener(ChronometerListener listener) {
        listeners.add(listener);
    }

    private Thread createNewRunner() {
        return new Thread(() -> {
            long start = System.currentTimeMillis();

            while(true) {
                try {
                    Thread.sleep(100);
                    long end = System.currentTimeMillis();

                    long duration = end - start;
                    long hours = duration / HOURS_DIV;
                    duration %= HOURS_DIV;
                    long minutes = duration / MINUTES_DIV;
                    duration %= MINUTES_DIV;
                    long seconds = duration / SECONDS_DIV;
                    duration %= SECONDS_DIV;
                    long split = duration / SPLIT_DIV;
                    final String chrono = String.format("%d:%d:%d %d", hours, minutes, seconds, split);

                    listeners.forEach(l -> l.currentChronometer(chrono));

                } catch (InterruptedException e) {
                    System.out.println("Interrupted.");
                    return;
                }
            }
        });
    }
}
