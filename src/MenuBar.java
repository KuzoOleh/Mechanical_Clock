import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
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

    Panel panel = new Panel();
    TimeZone timeZone;
    MenuBar(){
        jMenuBar = new JMenuBar();

        jMenuBar.add(chooseTimeZone);
        jMenuBar.add(timer);

        chooseTimeZone.add(uaZone);
        chooseTimeZone.add(usZone);
        chooseTimeZone.add(jpZone);
        chooseTimeZone.add(euZone);
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
            timeZone = TimeZone.getTimeZone("Europe/Kiev");
            //timeZone = TimeZone.getTimeZone("Ukraine/Kiev");
            panel.setCal(Calendar.getInstance(timeZone));
            System.out.println(timeZone);
        }
        if(e.getSource() == usZone){
            timeZone = TimeZone.getTimeZone("America/New_York");
            System.out.println(timeZone);
        }
        if(e.getSource() == jpZone){
            timeZone = TimeZone.getTimeZone("Asia/Tokyo");
            System.out.println(timeZone);
        }
        if(e.getSource() == euZone){
            timeZone = TimeZone.getTimeZone("Europe/Berlin");
            System.out.println(timeZone);
        }
        if(e.getSource() == auZone){
            timeZone = TimeZone.getTimeZone("Australia/Canberra");
            System.out.println(timeZone);
        }
        if(e.getSource() == setTimer){

            System.out.println("wakey-wakey");
        }
    }
}
