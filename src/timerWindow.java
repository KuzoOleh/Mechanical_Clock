import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
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

    Panel panel;

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

    timerWindow(){
        panel = new Panel();

        frame = new JFrame();
        frame.setSize(300,300);
        frame.setLayout(new FlowLayout());

        hourBox = new JComboBox<>();
        minuteBox = new JComboBox<>();
        secondBox = new JComboBox<>();

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
        if(e.getSource() == startTimer) {
            System.out.println("timer started!");
            comboBoxHour = Objects.requireNonNull(hourBox.getSelectedItem()).toString();
            comboBoxMinute = Objects.requireNonNull(minuteBox.getSelectedItem()).toString();
            comboBoxSecond = Objects.requireNonNull(secondBox.getSelectedItem()).toString();
            toIntSecond = Integer.parseInt(comboBoxSecond);
            toIntMinute = Integer.parseInt(comboBoxMinute);
            toIntHour = Integer.parseInt(comboBoxHour);
            System.out.println(comboBoxHour + " : " + comboBoxMinute + " : " + comboBoxSecond);
            if ((toIntHour == panel.getHour()) && (toIntMinute == panel.getMinute())
                    && (toIntSecond == panel.getSecond())) {
                try {
                    stopTimer.setVisible(true);
                    clip.open(audioStream);
                    clip.start();
                } catch (LineUnavailableException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        if(e.getSource() == stopTimer){
            clip.stop();
            clip.close();
            stopTimer.setVisible(false);
            System.out.println("timer stopped!");
        }
    }
}
