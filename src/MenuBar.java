import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar implements ActionListener {
    JMenuBar jMenuBar = new JMenuBar();

    JMenu timeZone = new JMenu("Часовий пояс");
    JMenu timer = new JMenu("Будильник");

    JMenuItem uaZone = new JMenuItem("ЮА");


    MenuBar(){
        jMenuBar.add(timeZone);
        jMenuBar.add(timer);

        timeZone.add(uaZone);

        jMenuBar.setVisible(true);

        timeZone.addActionListener(this);
        timer.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == uaZone){
            System.out.print("bob");
        }
    }
}
