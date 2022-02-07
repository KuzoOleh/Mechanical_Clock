import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class timerWindow implements ActionListener {
    JFrame frame;
    JComboBox<Integer> hourBox;
    JComboBox<Integer> minuteBox;
    JComboBox<Integer> secondBox;

    String comboBoxHour;
    String comboBoxMinute;
    String comboBoxSecond;

    int toIntHour;
    int toIntMinute;
    int toIntSecond;

    JLabel textTime;
    JLabel title;

    JButton startTimer;
    JButton stopTimer;

    timerWindow() {

        frame = new JFrame();
        frame.setSize(300, 300);
        frame.setLayout(new FlowLayout());

        hourBox = new JComboBox<>();
        minuteBox = new JComboBox<>();
        secondBox = new JComboBox<>();

        title = new JLabel("Встановіть час для будильника");
        title.setSize(100, 300);

        textTime = new JLabel("    Година:  " + "  хвилина:   " + "  секунда:   ");
        textTime.setPreferredSize(new Dimension(250, 100));

        startTimer = new JButton("Включити будильник");
        stopTimer = new JButton("Виключити будильник");
        stopTimer.setVisible(false);

        startTimer.addActionListener(this);
        stopTimer.addActionListener(this);

        for (int i = 1; i <= 12; i++) {
            hourBox.addItem(i);
        }
        for (int i = 0; i < 60; i++) {
            minuteBox.addItem(i);
            secondBox.addItem(i);
        }

        hourBox.setPreferredSize(new Dimension(75, 25));
        minuteBox.setPreferredSize(new Dimension(75, 25));
        secondBox.setPreferredSize(new Dimension(75, 25));
        frame.add(title);
        frame.add(textTime);
        frame.add(hourBox);
        frame.add(minuteBox);
        frame.add(secondBox);
        frame.add(startTimer);
        frame.add(stopTimer);

        if (MenuBar.clip.isOpen() || MenuBar.clip.isRunning()){
            startTimer.setEnabled(false);
            stopTimer.setVisible(true);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startTimer) {
            System.out.println("timer started!");
            comboBoxHour = Objects.requireNonNull(hourBox.getSelectedItem()).toString();
            comboBoxMinute = Objects.requireNonNull(minuteBox.getSelectedItem()).toString();
            comboBoxSecond = Objects.requireNonNull(secondBox.getSelectedItem()).toString();
            toIntSecond = Integer.parseInt(comboBoxSecond);
            toIntMinute = Integer.parseInt(comboBoxMinute);
            toIntHour = Integer.parseInt(comboBoxHour);
            if (toIntHour == 12) {
                toIntHour = 0;
            }
            System.out.println(comboBoxHour + " : " + comboBoxMinute + " : " + comboBoxSecond);

            stopTimer.setVisible(true);

            Thread checkClockAlarm = new Thread(this::run, "ClockAlarm");
            checkClockAlarm.start();
            startTimer.setEnabled(false);
        }
        if (e.getSource() == stopTimer) {
            MenuBar.clip.stop();
            MenuBar.clip.close();
            stopTimer.setVisible(false);
            System.out.println("timer stopped!");
            startTimer.setEnabled(true);
        }
    }
boolean exit = false;
    private void run() {
        while (!exit) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            int currentHour = Panel.getHour();
            int currentMinute = Panel.getMinute();
            int currentSecond = Panel.getSecond();

            if ((toIntHour == currentHour) && (toIntMinute == currentMinute)
                    && (toIntSecond == currentSecond)) {
                //                    MenuBar.clip.open(MenuBar.audioStream);
                MenuBar.clip.start();
                exit = true;
                System.out.println("Stop: " + Thread.currentThread().getName());
            }
        }
    }
}
