import javax.swing.*;
import java.awt.*;

public class timerWindow {
    JFrame frame;
    JComboBox hourBox;
    JComboBox minuteBox;
    JComboBox secondBox;


    timerWindow(){
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(300,300));
        frame.setVisible(true);

        hourBox = new JComboBox();
        minuteBox = new JComboBox();
        secondBox = new JComboBox();


    }


}
