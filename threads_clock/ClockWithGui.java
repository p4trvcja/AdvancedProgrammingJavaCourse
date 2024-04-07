import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();

    ClockWithGui() {
        ClockThread c = new ClockThread();
        c.start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);
        int radius = 145;
        g2d.setColor(Color.BLACK);
        g2d.fillOval(-radius, -radius, 2*radius, 2*radius);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(-(radius-15), -(radius-15), 2*(radius-15), 2*(radius-15));
        g2d.setColor(Color.BLACK);
        int centerRadius = 10;
        g2d.fillOval(-centerRadius, -centerRadius, 2 * centerRadius, 2 * centerRadius);

        for(int i = 1; i <= 12; i++) {
            AffineTransform at = new AffineTransform();
            at.rotate(2 * Math.PI / 12 * i);
            Point2D src = new Point2D.Float(0, -110);
            Point2D trg = new Point2D.Float();
            at.transform(src, trg);
            g2d.drawString(Integer.toString(i), (int) trg.getX(), (int) trg.getY());
        }
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 60; i++) {
            AffineTransform at = AffineTransform.getRotateInstance(2 * Math.PI / 60 * i);
            g2d.draw(at.createTransformedShape(new Line2D.Float(radius * 0.85f, 0, radius * 0.90f, 0)));
        }

        AffineTransform saveAT = g2d.getTransform();

        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.rotate((time.getHour() % 12 + time.getMinute() / 60.0) * 2 * Math.PI / 12);
        g2d.drawLine(0, 0, 0, -70);
        g2d.setTransform(saveAT);

        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.rotate((time.getMinute() + time.getSecond() / 60.0) * 2 * Math.PI / 60);
        g2d.setPaint(Color.BLACK);
        g2d.drawLine(0, 0, 0, -90);
        g2d.setTransform(saveAT);

        g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.rotate(time.getSecond() * 2 * Math.PI / 60);
        g2d.setPaint(Color.BLACK);
        g2d.drawLine(0, 0, 0, -100);
        g2d.setTransform(saveAT);

    }

    class ClockThread extends Thread{
        @Override
        public void run() {
            while(true) {
                time = LocalTime.now();
                System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}