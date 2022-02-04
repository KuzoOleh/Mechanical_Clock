import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class timerWindow implements ActionListener {
    JFrame frame;
    JComboBox hourBox;
    JComboBox minuteBox;
    JComboBox secondBox;

    JLabel textTime;
    JLabel title;

    JButton startTimer;
    JButton stopTimer;
    timerWindow(){
        frame = new JFrame();
        frame.setSize(300,300);
        frame.setLayout(new FlowLayout());

        hourBox = new JComboBox();
        minuteBox = new JComboBox();
        secondBox = new JComboBox();

        title = new JLabel("Встановіть час для будильника");
        title.setSize(100,300);

        textTime = new JLabel("    Година:  " +"  хвилина:   " + "  секунда:   ");
        textTime.setPreferredSize(new Dimension(250,100));

        startTimer = new JButton("Включити будильник");
        stopTimer = new JButton("Виключити будильник");
        stopTimer.setVisible(false);

        startTimer.addActionListener(this);
        stopTimer.addActionListener(this);

        for(int i = 0; i <= 24; i++){
            hourBox.addItem(i);
        }
        for(int i = 0; i < 60; i++){
            minuteBox.addItem(i);
            secondBox.addItem(i);
        }
        hourBox.setPreferredSize(new Dimension(75,25));
        minuteBox.setPreferredSize(new Dimension(75,25));
        secondBox.setPreferredSize(new Dimension(75,25));
        frame.add(title);
        frame.add(textTime);
        frame.add(hourBox);
        frame.add(minuteBox);
        frame.add(secondBox);
        frame.add(startTimer);
        frame.add(stopTimer);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startTimer){
            stopTimer.setVisible(true);
            System.out.println("timer started!");
        }
    }
}
