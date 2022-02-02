import javax.swing.*;

public class MainWindow {
    JFrame frame;
    int scrWidth;
    int scrHeight;


    Panel clockPanel;

    public MainWindow(){

        clockPanel = new Panel();

        scrWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        scrHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(clockPanel);
        frame.pack();
        frame.setLocation((scrWidth / 2),(scrHeight / 2));
        frame.setVisible(true);
        frame.setTitle("Механічний годинник");
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
