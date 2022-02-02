import java.time.ZoneId;
import java.util.TimeZone;

/**
 * Най буде на майбутнє
 * хз чи тре...
 */
public class Clock implements Runnable {

    public TimeZone timeZone;
    Panel panel;

    public Clock(Panel panel) {

        timeZone = GetTimeZone();

        this.panel = panel;
        Thread updateTime = new Thread(this, "Updating time");
        updateTime.start();

    }

    // TODO: Завершити вибірку тайм зони
    private TimeZone GetTimeZone() {
        TimeZone.getTimeZone(ZoneId.of("Europe/Kiev"));
        return null;
    }

    @Override
    public void run() {
        // TODO: Доробити так, щоб в Calendar.getInstance() працював вибір Time Zone
        System.out.println("Я працюю");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        panel.repaint();
    }

}
