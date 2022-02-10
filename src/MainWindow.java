import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;

public class MainWindow {
    public static JFrame frame;
    int scrWidth;
    int scrHeight;


    Panel clockPanel;

    public static MenuBar menuBar;

    public MainWindow(){

        menuBar = new MenuBar();

        clockPanel = new Panel();

        scrWidth = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        scrHeight = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(clockPanel);
        frame.setPreferredSize(new Dimension(460,510));
        frame.setJMenuBar(menuBar.jMenuBar);
        frame.pack();
        frame.setLocation((scrWidth / 2),(scrHeight / 2));
        frame.setVisible(true);
        frame.setTitle("Механічний годинник");
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
