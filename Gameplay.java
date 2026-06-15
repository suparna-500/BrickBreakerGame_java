import javax.swing.*;
import java.awt.*;

public class Gameplay extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 600);

        // Paddle
        g.setColor(Color.GREEN);
        g.fillRect(300, 520, 100, 10);
    }
}