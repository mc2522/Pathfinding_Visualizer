package PathfindingVisualizerFX;

import java.util.Arrays;

public class Grid {
    // dimension of the grid (length x length)
    private final int DIM = 20;
    // number code of empty node
    private final int EMPTY_NODE = 0;
    // number code of start node
    private final int START_NODE = 1;
    // number code of end node
    private final int END_NODE = 2;
    // number code of obstacle node
    private final int OBSTACLE_NODE = 3;
    // 2D array to represent grid
    private int [][] grid;
    // coordinates for start node
    private int [] startCoordinates;
    // coordinates for end node
    private int [] endCoordinates;

    /**
     * Constructor for Grid
     * Initializes Grid with a 2D array to represent nodes
     */
    public Grid() {
        grid = new int[DIM][DIM];
        // populate the 2D array with empty nodes
        for (int [] row: grid)
            Arrays.fill(row, EMPTY_NODE);
        // default start node will be at (0, 0)
        startCoordinates = new int[] {0, 0};
        grid[0][0] = START_NODE;
        // default end node will be at (DIM - 1, DIM - 1)
        endCoordinates = new int[] {DIM - 1, DIM - 1};
        grid[DIM - 1][DIM - 1] = END_NODE;
    }

    /**
     * Sets preset grid
     */
    public void setPreset(int number) {
        switch (number) {
            case 1:
                grid = new int[][]
                        {
                                {1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                                {1, 1, 2, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1},
                                {0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                                {0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0},
                                {1, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1},
                                {0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                                {0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0},
                                {1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
                                {1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1},
                                {0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                                {0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0},
                                {1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                                {1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1},
                                {1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1},
                                {1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                                {1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1},
                                {0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 3, 0, 1, 0, 1},
                                {0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0},
                                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                                {1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1}
                        };
                break;
        }
    }

    /**
     * Change start node coordinates in the 2D array
     * @param x     x coordinate of node
     * @param y     y coordinate of node
     */
    public void changeStartNode(int x, int y) {
        grid[x][y] = START_NODE;
        startCoordinates = new int[] {x, y};
    }

    /**
     * Change end node coordinates in the 2D array
     * @param x     x coordinate of node
     * @param y     y coordinate of node
     */
    public void changeEndNode(int x, int y) {
        grid[x][y] = END_NODE;
        endCoordinates = new int[] {x, y};
    }

    /**
     * Add obstacle node in the 2D array
     * @param x     x coordinate of node
     * @param y     y coordinate of node
     */
    public void addObstacle(int x, int y) {
        grid[x][y] = OBSTACLE_NODE;
    }

    public void removeObstacle(int x, int y) {
        grid[x][y] = EMPTY_NODE;
    }

    @Override
    public String toString() {
        String ret = "";
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                ret = ret + Integer.toString(grid[row][column]) + " ";
                if (column == DIM - 1) ret += "\n";
            }
        }
        return ret;
    }
}
