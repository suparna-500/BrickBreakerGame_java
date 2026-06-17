import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    // Timer for game loop
    private Timer timer;

    // Score
    private int score = 0;

    // Paddle position
    private int paddleX = 300;

    // Ball position
    private int ballPosX = 120;
    private int ballPosY = 350;

    // Ball movement direction
    private int ballXDir = -2;
    private int ballYDir = -3;

    // Brick map
    private MapGenerator map;

    // Constructor
    public Gameplay() {

        // Create 3 rows and 7 columns of bricks
        map = new MapGenerator(3, 7);

        // Enable keyboard input
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        // Timer runs every 10 ms
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 600);

        // Draw Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 550, 30);

        // Draw Bricks
        map.draw((Graphics2D) g);

        // Draw Paddle
        g.setColor(Color.GREEN);
        g.fillRect(paddleX, 520, 100, 10);

        // Draw Ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballPosX, ballPosY, 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Move Ball
        ballPosX += ballXDir;
        ballPosY += ballYDir;

        // Ball Rectangle
        Rectangle ballRect = new Rectangle(
                ballPosX,
                ballPosY,
                20,
                20
        );

        // Paddle Rectangle
        Rectangle paddleRect = new Rectangle(
                paddleX,
                520,
                100,
                10
        );

        // Paddle Collision
        if (ballRect.intersects(paddleRect)) {
            ballYDir = -ballYDir;
        }

        // Brick Collision
        A:
        for (int i = 0; i < map.map.length; i++) {

            for (int j = 0; j < map.map[0].length; j++) {

                if (map.map[i][j] > 0) {

                    Rectangle brickRect = new Rectangle(
                            j * map.brickWidth + 80,
                            i * map.brickHeight + 50,
                            map.brickWidth,
                            map.brickHeight
                    );

                    if (ballRect.intersects(brickRect)) {

                        // Remove brick
                        map.setBrickValue(0, i, j);

                        // Increase score
                        score += 10;

                        // Bounce ball
                        ballYDir = -ballYDir;

                        break A;
                    }
                }
            }
        }

        // Left Wall
        if (ballPosX < 0) {
            ballXDir = -ballXDir;
        }

        // Right Wall
        if (ballPosX > 660) {
            ballXDir = -ballXDir;
        }

        // Top Wall
        if (ballPosY < 0) {
            ballYDir = -ballYDir;
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Right Arrow
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (paddleX < 580) {
                paddleX += 20;
            }
        }

        // Left Arrow
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            if (paddleX > 10) {
                paddleX -= 20;
            }
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}