import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
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

    ZoneId zone;

    int timeInSecond;
    int timeInMinute;
    int timeInHour;


    private TimeZone utcZone;
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

            System.out.println("wakey-wakey");
        }
    }
}
