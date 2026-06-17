import java.awt.*;   // Used for colors and drawing

// Class used to create and draw bricks
public class MapGenerator {

    // 2D array to store bricks
    // 1 = brick exists
    // 0 = brick destroyed
    public int map[][];

    // Width and height of each brick
    public int brickWidth;
    public int brickHeight;

    // Constructor
    // Runs automatically when object is created
    public MapGenerator(int row, int col) {

        // Create 2D array
        map = new int[row][col];

        // Fill all positions with 1
        // Means all bricks are present initially
        for(int i = 0; i < map.length; i++) {

            for(int j = 0; j < map[0].length; j++) {

                map[i][j] = 1;
            }
        }

        // Calculate brick width
        // Total brick area width = 540 pixels
        brickWidth = 540 / col;

        // Calculate brick height
        // Total brick area height = 150 pixels
        brickHeight = 150 / row;
    }

    // Method used to draw bricks
    public void draw(Graphics2D g) {

        // Loop through all rows
        for(int i = 0; i < map.length; i++) {

            // Loop through all columns
            for(int j = 0; j < map[0].length; j++) {

                // Draw brick only if value > 0
                if(map[i][j] > 0) {

                    // First row = Red
                    if(i == 0)
                        g.setColor(Color.RED);

                    // Second row = Blue
                    else if(i == 1)
                        g.setColor(Color.BLUE);

                    // Third row = Green
                    else
                        g.setColor(Color.GREEN);

                    // Draw filled brick
                    g.fillRect(
                            j * brickWidth + 80,   // X position
                            i * brickHeight + 50,  // Y position
                            brickWidth,            // Width
                            brickHeight            // Height
                    );

                    // Border thickness
                    g.setStroke(new BasicStroke(3));

                    // Border color
                    g.setColor(Color.BLACK);

                    // Draw border around brick
                    g.drawRect(
                            j * brickWidth + 80,
                            i * brickHeight + 50,
                            brickWidth,
                            brickHeight
                    );
                }
            }
        }
    }

    // Used when ball hits a brick
    // Change brick value from 1 to 0
    public void setBrickValue(int value, int row, int col) {

        map[row][col] = value;
    }
}