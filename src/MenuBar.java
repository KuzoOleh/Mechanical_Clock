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
    JMenuItem usZone = new JMenuItem("Вашингтон(UTC-8)");
    JMenuItem jpZone = new JMenuItem("Токіо(UTC+9)");
    JMenuItem euZone = new JMenuItem("Берлін(UTC+1)");

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

        chooseTimeZone.addActionListener(this);
        timer.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == uaZone){

            timeZone = TimeZone.getTimeZone("Ukraine/Kiev");
            panel.setCal(Calendar.getInstance(timeZone));
            System.out.print("bob");
        }
    }
}
