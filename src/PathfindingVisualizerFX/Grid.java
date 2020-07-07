package PathfindingVisualizerFX;

import PathfindingVisualizerFX.algorithms.DFSAlgorithm;
import PathfindingVisualizerFX.algorithms.DijkstraAlgorithm;

import java.util.Arrays;
import static PathfindingVisualizerFX.algorithms.BFSAlgorithm.*;
import static PathfindingVisualizerFX.Utility.*;

public class Grid {
    // 2D array to represent grid
    private static int [][] grid;
    // coordinates for start node
    private String startCoordinates;
    // coordinates for end node
    private String targetCoordinates;

    /**
     * Constructor for Grid
     * Initializes Grid with a 2D array to represent nodes
     */
    public Grid() {
        grid = new int[DIM][DIM];
        // populate the 2D array with empty nodes
        for (int [] row: grid)
            Arrays.fill(row, EMPTY_NODE);
        // default start node will be at (4, 4)
        startCoordinates = "4, 4";
        grid[4][4] = START_NODE;
        // default end node will be at (DIM - 5, DIM - 5)
        targetCoordinates = (DIM - 5) + ", " + (DIM - 5);
        grid[DIM - 5][DIM - 5] = TARGET_NODE;
    }

    /**
     * Sets preset grid
     */
    public void setPreset(int number) {
        switch (number) {
            case 1:
                grid = new int[][]
                        {
                                {OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, START_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE},
                                {EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE},
                                {EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE},
                                {EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE},
                                {EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE},
                                {EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE},
                                {EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE},
                                {EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, TARGET_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE},
                                {EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE}
                        };
                    startCoordinates = "1, 2";
                break;
            case 2:
                grid = new int[][]
                        {
                                {OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, TARGET_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, EMPTY_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, EMPTY_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE},
                                {OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE, OBSTACLE_NODE}
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
        startCoordinates = Integer.toString(x) + ", " + Integer.toString(y);
    }

    /**
     * Change target node coordinates in the 2D array
     * @param x     x coordinate of node
     * @param y     y coordinate of node
     */
    public void changeTargetNode(int x, int y) {
        grid[x][y] = TARGET_NODE;
        String [] oldCoordinates = targetCoordinates.split(", ");
        grid[Integer.parseInt(oldCoordinates[0])][Integer.parseInt(oldCoordinates[1])] = EMPTY_NODE;
        targetCoordinates = x + ", " + y;
    }

    /**
     * Add obstacle node in the 2D array
     * @param x     x coordinate of node
     * @param y     y coordinate of node
     */
    public void addObstacle(int x, int y) {
        grid[x][y] = OBSTACLE_NODE;
    }

    /**
     * Remove obstacle node in the 2D array
     * @param x     x coordinate of node
     * @param y     y coordinate of node
     */
    public void removeObstacle(int x, int y) {
        grid[x][y] = EMPTY_NODE;
    }

    /**
     * Performs BFS on grid
     * @return boolean      true if target node is found else false
     */
    public boolean performBFS() {
        return BFS(grid, startCoordinates);
    }

    /**
     * Performs DFS on grid
     * @return boolean      true if target node is found else false
     */
    public boolean performDFS() {
        DFSAlgorithm DFSObj = new DFSAlgorithm(grid);
        String [] coordinates = startCoordinates.split(", ");
        return DFSObj.DFS(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }

    public boolean performDijkstra() {
        DijkstraAlgorithm DijkstraObj = new DijkstraAlgorithm(grid, startCoordinates);
        return DijkstraObj.Dijkstra();
    }

    /**
     * Clears the grid of visited nodes
     */
    public void clearPath() {
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (grid[row][column] == VISITED_NODE || grid[row][column] == PATH_NODE)
                    grid[row][column] = EMPTY_NODE;
            }
        }
    }

    /**
     * Get the grid
     * @return grid     2D array representing grid
     */
    public int [][] getGrid() {
        return grid;
    }
}
