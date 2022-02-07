import javax.sound.sampled.*;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class MenuBar implements ActionListener {
    JMenuBar jMenuBar;

    JMenu chooseTimeZone = new JMenu("Часовий пояс");
    JMenu timer = new JMenu("Будильник");
    JMenuItem uaZone = new JMenuItem("Київ(UTC+2)");
    JMenuItem usZone = new JMenuItem("Нью-Йорк(UTC-8)");
    JMenuItem jpZone = new JMenuItem("Токіо(UTC+9)");
    JMenuItem euZone = new JMenuItem("Берлін(UTC+1)");
    JMenuItem auZone = new JMenuItem("Канберра(UTC+11)");

    JMenuItem setTimer = new JMenuItem("Поставити будильник");

    private ZoneId zone = ZoneId.of("Europe/Kiev");

    private int timeInSecond;
    private int timeInMinute;
    private int timeInHour;

    public static File file = new File("src//resource//Timer.wav");
    public static AudioInputStream audioStream;

    {
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
    public static Clip clip;

    {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public int getTimeInSecond() {
        return timeInSecond;
    }

    public int getTimeInMinute() {
        return timeInMinute;
    }

    public int getTimeInHour() {
        return timeInHour;
    }

    public TimeZone getZone() {
        return TimeZone.getTimeZone(zone);
    }

    MenuBar(){
        jMenuBar = new JMenuBar();

        jMenuBar.add(chooseTimeZone);
        jMenuBar.add(timer);

        chooseTimeZone.add(uaZone);
        chooseTimeZone.add(usZone);
        chooseTimeZone.add(jpZone);
        chooseTimeZone.add(euZone);
        chooseTimeZone.add(auZone);
        timer.add(setTimer);

        uaZone.addActionListener(this);
        usZone.addActionListener(this);
        jpZone.addActionListener(this);
        euZone.addActionListener(this);
        auZone.addActionListener(this);
        setTimer.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == uaZone){
           //беремо час Київа
            zone = ZoneId.of("Europe/Kiev");
            ZonedDateTime kievTime = ZonedDateTime.now(zone);
             timeInSecond = kievTime.getSecond();
             timeInMinute = kievTime.getMinute();
             timeInHour = kievTime.getHour();
             System.out.println("Київ: " +timeInHour + " : " + timeInMinute +" : " + timeInSecond);
        }
        if(e.getSource() == usZone){
            zone = ZoneId.of("America/New_York");
            ZonedDateTime nyTime = ZonedDateTime.now(zone);
            timeInSecond = nyTime.getSecond();
            timeInMinute = nyTime.getMinute();
            timeInHour = nyTime.getHour();
            System.out.println("Нью-Йорк: " + timeInHour + " : " + timeInMinute +" : " + timeInSecond);
        }
        if(e.getSource() == jpZone){
            zone = ZoneId.of("Asia/Tokyo");
            ZonedDateTime tokyoTime = ZonedDateTime.now(zone);
            timeInSecond = tokyoTime.getSecond();
            timeInMinute = tokyoTime.getMinute();
            timeInHour = tokyoTime.getHour();
            System.out.println("Токіо: " + timeInHour + " : " + timeInMinute +" : " + timeInSecond);
        }
        if(e.getSource() == euZone){
            zone = ZoneId.of("Europe/Berlin");
            ZonedDateTime berlinTime = ZonedDateTime.now(zone);
            timeInSecond = berlinTime.getSecond();
            timeInMinute = berlinTime.getMinute();
            timeInHour = berlinTime.getHour();
            System.out.println("Берлін: " + timeInHour + " : " + timeInMinute +" : " + timeInSecond);

        }
        if(e.getSource() == auZone){
            zone = ZoneId.of("Australia/Canberra");
            ZonedDateTime berlinTime = ZonedDateTime.now(zone);
            timeInSecond = berlinTime.getSecond();
            timeInMinute = berlinTime.getMinute();
            timeInHour = berlinTime.getHour();
            System.out.println("Канберра: " + timeInHour + " : " + timeInMinute +" : " + timeInSecond);

        }
        if(e.getSource() == setTimer){
            try {
                timerWindow TimerWindow = new timerWindow();
                TimerWindow.frame.setVisible(true);
                clip.open(audioStream);
                //clip.start();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("wakey-wakey");
        }
    }
}
