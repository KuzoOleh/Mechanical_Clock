import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MenuBar;
import java.util.Calendar;

public class Panel extends JPanel {
    int winWidth = 450;
    int winHeight = 450;

    double angle;
    int x, y;

    private static int hour;
   private static int minute;
   private static int second;

    public static int getHour() {
        return hour;
    }

    public static int getMinute() {
        return minute;
    }

    public static int getSecond() {
        return second;
    }

    Font font;

    MenuBar menuBar = new MenuBar();

    Panel() {
        this.setPreferredSize(new Dimension(winWidth,winHeight));
            ReDraw();
    }

    private void ReDraw() {
        Thread t = new Thread(() -> {
                while(true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
        });
        t.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        font = new Font("Helvetica", Font.PLAIN, 18);

        //коло
        g2D.setStroke(new BasicStroke(4));
        g2D.drawOval(5, 1, 450, 450);

        //Годинні числа
        for (int i = 1; i < 13; i++) {
            g2D.setFont(font);
            angle = i * Math.PI / 6;
            x = (int) (200 * Math.sin(angle));
            y = (int) (200 * Math.cos(angle));
            g2D.setStroke(new BasicStroke(2));
            g2D.drawString(Integer.toString(i), 225 + x, 225 - y);
            g2D.drawLine(225 + x, 225 - y, 225 + x, 225 - y);
        }
        //хвилинні числа
        for (int i = 0; i < 360; i++) {
            angle = i * Math.PI / 30;
            x = (int) (200 * Math.sin(angle));
            y = (int) (200 * Math.cos(angle));
            g2D.drawLine(225 + x, 225 - y, 225 + x, 225 - y);
        }
        //ініціалізація часу
        Calendar cal = Calendar.getInstance(MainWindow.menuBar.getZone());
        //cal = Calendar.getInstance(menuBar.getUtcZone());
//
//        int second = cal.get(menuBar.getTimeInSecond());
//        int minute = cal.get(menuBar.getTimeInMinute());
//        int hour = cal.get(menuBar.getTimeInHour());
//        cal.set(Calendar.HOUR_OF_DAY,24);
        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);

        //Годинникова стрілка
        angle = (hour * Math.PI / 6) +
                (minute * Math.PI / (6 * 60)) +
                (second * Math.PI / (360 * 60));
        x = (int) (160 * Math.sin(angle));
        y = (int) (160 * Math.cos(angle));
        g2D.drawLine(225, 225, 225 + x, 225 - y);

        //хвилинна стрілка
        angle = (minute * Math.PI / 30) +
                (second * Math.PI / (30 * 60));
        x = (int) (190 * Math.sin(angle));
        y = (int) (190 * Math.cos(angle));
        g2D.drawLine(225, 225, 225 + x, 225 - y);

        //секундна стрілка
        g2D.setColor(Color.RED);
        angle = (second * Math.PI / (30));
        x = (int) (190 * Math.sin(angle));
        y = (int) (190 * Math.cos(angle));
        g2D.drawLine(225, 225, 225 + x, 225 - y);
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
