package javalibrarysystem.panels;
import javax.swing.JPanel;
import java.awt.*;
public class LogoPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        // Set the color for the shape.
        g2d.setColor(new Color(132, 112, 201));

        // Set a thicker stroke for the lines and arcs.
        g2d.setStroke(new BasicStroke(14, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        int startX = 50;
        int startY = 80;
        int width = 200;
        int gap = 20;

        // Draw three horizontal lines.
        for (int i = 0; i < 3; i++) {
            if (i == 1) {
                startX = startX - 10;
                width = width + 10;
            } else {
                startX = 50;
                width = 200;
            }
            g2d.drawLine(startX, startY + 15 + i * gap, startX + width, (startY + 15) + i * gap);
        }

        int arcX = startX - 30;
        int arcY = startY - 5;
        int arcWidth = 70;
        int arcHeight = gap * 2 + 40;

        g2d.drawArc(arcX, arcY, arcWidth, arcHeight, 90, 180);

        width = width + 40;
        // Draw horizontal line at the top of the arc.
        g2d.drawLine(startX, arcY, arcX + width, arcY);
        // Draw horizontal line at the bottom of the arc.
        g2d.drawLine(startX, arcY + arcHeight, arcX + width, arcY + arcHeight);

        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.drawString("Library System", 40, (arcY + arcHeight) + 40);
    }
}
