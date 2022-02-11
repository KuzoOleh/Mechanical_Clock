import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;


public class Panel extends JPanel {
    int winWidth = 450;
    int winHeight = 450;

    double angle;
    int x, y;

    private static int hour;
    private static int minute;
    private static int second;
    BufferedImage image;

    {
        try {
            image = ImageIO.read(new File("src\\resource\\image\\second_Hand.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static ImageIcon icon = new ImageIcon("src\\resource\\image\\kyiv.jpg");

    Panel() {
        this.setPreferredSize(new Dimension(winWidth, winHeight));
        ReDraw();
    }

    private void ReDraw() {
        Thread t = new Thread(() -> {
            while (true) {
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
        Graphics2D secondHand = (Graphics2D) g;
        Graphics2D minuteHand = (Graphics2D) g;
        Graphics2D hourHand = (Graphics2D) g;


        Image im = icon.getImage();
        g.drawImage(im, 0, 0, this);

        font = new Font("Helvetica", Font.BOLD, 22);

        //коло
        g2D.setStroke(new BasicStroke(4));
        g2D.setColor(Color.BLACK);
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

        second = cal.get(Calendar.SECOND);
        minute = cal.get(Calendar.MINUTE);
        hour = cal.get(Calendar.HOUR);

        //Годинникова стрілка
        hourHand.setStroke(new BasicStroke(5));
        angle = (hour * Math.PI / 6) +
                (minute * Math.PI / (6 * 60)) +
                (second * Math.PI / (360 * 60));
        x = (int) (160 * Math.sin(angle));
        y = (int) (160 * Math.cos(angle));
        hourHand.drawLine(225, 225, 230 + x, 230 - y);

        //хвилинна стрілка
        minuteHand.setStroke(new BasicStroke(4));
        minuteHand.setColor(Color.BLACK);
        angle = (minute * Math.PI / 30) +
                (second * Math.PI / (30 * 60));
        x = (int) (190 * Math.sin(angle));
        y = (int) (190 * Math.cos(angle));
        minuteHand.drawLine(225, 225, 225 + x, 225 - y);

        //секундна стрілка
        secondHand.setStroke(new BasicStroke(3));
        secondHand.setColor(Color.RED);
        angle = (second * Math.PI / (30));
        x = (int) (190 * Math.sin(angle));
        y = (int) (190 * Math.cos(angle));
        secondHand.drawLine(225, 225, 225 + x, 225 - y);
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
