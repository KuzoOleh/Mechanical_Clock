import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

    File file = new File("src//resource//Timer.wav");
    AudioInputStream audioStream;

    {
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    Clip clip;

    {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

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

        for (int i = 0; i <= 24; i++) {
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
        }
        if (e.getSource() == stopTimer) {
            clip.stop();
            clip.close();
            stopTimer.setVisible(false);
            System.out.println("timer stopped!");
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
                try {
                    clip.open(audioStream);
                    clip.start();
                } catch (LineUnavailableException | IOException ex) {
                    ex.printStackTrace();
                }
                exit = true;
                System.out.println("Stop: " + Thread.currentThread().getName());
            }
        }
    }
}
