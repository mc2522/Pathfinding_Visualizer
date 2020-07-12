package PathfindingVisualizerFX;

import javafx.scene.paint.Color;

public class Utility {
    // dimension of the grid (length x length)
    public final static int DIM = 20;
    // number and color code of empty node
    public final static int EMPTY_NODE = 0;
    public final static Color EMPTY_COLOR = Color.BLACK;
    // number and color code of start node
    public final static int START_NODE = 1;
    public final static Color START_COLOR = Color.RED;
    // number and color code of target node
    public final static int TARGET_NODE = 2;
    public final static Color TARGET_COLOR = Color.BLUE;
    // number and color code of obstacle node
    public final static int OBSTACLE_NODE = 3;
    public final static Color OBSTACLE_COLOR = Color.WHITE;
    // number and color code of visited node
    public final static int VISITED_NODE = 4;
    public final static Color VISITED_COLOR = Color.PURPLE;
    // number and color code of path node
    public final static int PATH_NODE = 5;
    public final static Color PATH_COLOR = Color.GREEN;

    /**
     * Sleeps for 0.25 seconds so user can catch up
     */
    public static void shortDelay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Sleeps for 1.5 seconds for users so that they can read the messages
     */
    public static void longDelay() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Formats the grid into a string to be printed
     * @param grid      2D array representing the grid
     * @return ret      formatted string
     */
    public static String formatGrid(int [][] grid) {
        String ret = "";
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                ret = ret + grid[row][column] + " ";
                if (column == DIM - 1) ret += "\n";
            }
        }
        return ret;
    }

}
