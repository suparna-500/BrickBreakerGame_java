import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Gameplay class
// JPanel -> used for drawing game objects
// KeyListener -> used for keyboard input
// ActionListener -> used for timer events
public class Gameplay extends JPanel implements KeyListener, ActionListener {

    // Timer object
    private Timer timer;

    // Paddle X position
    private int paddleX = 300;

    // Ball position
    private int ballPosX = 120;
    private int ballPosY = 350;

    // Ball direction and speed
    private int ballXDir = -2;
    private int ballYDir = -3;

    // Brick object
    private MapGenerator map;

    // Constructor
    public Gameplay() {

        // Create 3 rows and 7 columns of bricks
        map = new MapGenerator(3, 7);

        // Add keyboard listener
        addKeyListener(this);

        // Allow keyboard focus
        setFocusable(true);

        // Enable arrow keys
        setFocusTraversalKeysEnabled(false);

        // Timer runs every 10 milliseconds
        timer = new Timer(10, this);

        // Start timer
        timer.start();
    }

    // Draw everything on screen
    @Override
    protected void paintComponent(Graphics g) {

        // Call parent paint method
        super.paintComponent(g);

        // Background color
        g.setColor(Color.BLACK);

        // Draw background
        g.fillRect(0, 0, 700, 600);

        // Draw bricks
        map.draw((Graphics2D) g);

        // Paddle color
        g.setColor(Color.GREEN);

        // Draw paddle
        g.fillRect(paddleX, 520, 100, 10);

        // Ball color
        g.setColor(Color.YELLOW);

        // Draw ball
        g.fillOval(ballPosX, ballPosY, 20, 20);
    }

    // Runs automatically by timer
  @Override
public void actionPerformed(ActionEvent e) {

    // Move ball
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
    if(ballRect.intersects(paddleRect)) {
        ballYDir = -ballYDir;
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

    // Keyboard input
    @Override
    public void keyPressed(KeyEvent e) {

        // Right Arrow
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            // Keep paddle inside screen
            if (paddleX < 580) {
                paddleX += 20;
            }
        }

        // Left Arrow
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {

            // Keep paddle inside screen
            if (paddleX > 10) {
                paddleX -= 20;
            }
        }

        // Redraw screen
        repaint();
    }

    // Required by KeyListener
    @Override
    public void keyReleased(KeyEvent e) {
    }

    // Required by KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }
}