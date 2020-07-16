package PathfindingVisualizerFX;

import PathfindingVisualizerFX.algorithms.AStarAlgorithm;
import PathfindingVisualizerFX.algorithms.BFSAlgorithm;
import PathfindingVisualizerFX.algorithms.DFSAlgorithm;
import PathfindingVisualizerFX.algorithms.DijkstraAlgorithm;

import java.util.Arrays;
import static PathfindingVisualizerFX.Utility.*;

public class Grid {
    // 2D array to represent grid
    private static int [][] grid;
    // coordinates for start node
    private String startCoordinates;
    // coordinates for end node
    private String targetCoordinates;
    // controller for updating GUI
    private Controller controller;

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
                        {3, 3, 0, 0, 3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 3, 0, 0, 0, 0, 3},
                        {3, 3, 1, 3, 0, 0, 0, 0, 3, 3, 0, 3, 3, 0, 0, 3, 3, 3, 0, 3},
                        {0, 3, 0, 0, 3, 0, 0, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 3},
                        {0, 0, 0, 3, 0, 3, 0, 0, 3, 0, 0, 3, 3, 0, 3, 0, 3, 0, 0, 0},
                        {3, 3, 0, 0, 0, 0, 3, 0, 0, 3, 0, 3, 0, 0, 3, 0, 3, 0, 3, 3},
                        {0, 3, 0, 3, 3, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0},
                        {0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 3, 0, 3, 3, 3, 3, 0, 3, 3, 0},
                        {3, 3, 0, 0, 3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 3, 0, 0, 0, 0, 3},
                        {3, 0, 0, 3, 0, 0, 0, 0, 3, 3, 0, 3, 3, 0, 0, 3, 3, 3, 0, 3},
                        {0, 3, 0, 0, 3, 0, 0, 3, 3, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 3},
                        {0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 3, 3, 0, 3, 0, 3, 0, 0, 0},
                        {3, 3, 0, 0, 0, 3, 0, 0, 3, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3, 3},
                        {3, 3, 0, 0, 3, 3, 3, 0, 3, 3, 3, 3, 3, 0, 3, 3, 3, 0, 3, 3},
                        {3, 3, 0, 0, 3, 3, 3, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 3, 3},
                        {3, 3, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 3},
                        {3, 0, 0, 3, 0, 0, 0, 0, 3, 3, 0, 3, 3, 0, 0, 3, 3, 3, 0, 3},
                        {0, 3, 0, 0, 3, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 2, 0, 3, 0, 3},
                        {0, 0, 0, 3, 0, 3, 0, 0, 3, 0, 0, 3, 3, 0, 3, 0, 3, 0, 0, 0},
                        {3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3},
                        {3, 0, 0, 3, 0, 0, 0, 0, 3, 3, 0, 3, 3, 0, 0, 3, 3, 3, 0, 3}
                    };
                startCoordinates = "1, 2";
                targetCoordinates = "16, 15";
                break;
            case 2:
                grid = new int[][]
                    {
                        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                        {3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 1, 3},
                        {3, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 3},
                        {3, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3},
                        {3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 3},
                        {3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 3},
                        {3, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3},
                        {3, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 3},
                        {3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 3},
                        {3, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 3},
                        {3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 3},
                        {3, 2, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 3, 3},
                        {3, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3},
                        {3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 3},
                        {3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 3},
                        {3, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3},
                        {3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 3},
                        {3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 0, 0, 3, 3},
                        {3, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 3, 0, 0, 3},
                        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
                    };
                startCoordinates = "1, 18";
                targetCoordinates = "11, 1";
                break;
            case 3:
                grid = new int[][]
                    {
                        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                        {3, 1, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 0, 0, 3, 0, 3, 0, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 3, 3, 3, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0, 3, 0, 3, 0, 3, 0, 3},
                        {3, 0, 3, 0, 3, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 3, 2, 3},
                        {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
                    };
                startCoordinates = "1, 1";
                targetCoordinates = "18, 18";
                break;
            case 4:
                grid = new int[][]
                    {
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 2},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0 ,0, 0, 0, 0},
                    };
                startCoordinates = "10, 0";
                targetCoordinates = "10, 19";
                break;
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Change a node in the grid
     * @param row           row coordinate of node
     * @param column        column coordinate of node
     * @param status        status of node
     */
    public void changeNode(int row, int column, int status) {
        grid[row][column] = status;
    }

    /**
     * Change start node in the grid
     * @param row           row coordinate of start node
     * @param column        column coordinate of start node
     * @return boolean      true if change has been made else false
     */
    public boolean setStart(int row, int column) {
        if (grid[row][column] != TARGET_NODE) {
            String[] startSplit = startCoordinates.split(", ");
            int prevRow = Integer.parseInt(startSplit[0]);
            int prevColumn = Integer.parseInt(startSplit[1]);
            grid[prevRow][prevColumn] = EMPTY_NODE;
            grid[row][column] = START_NODE;
            startCoordinates = row + ", " + column;
            return true;
        }
        return false;
    }

    /**
     * Change target node in the grid
     * @param row           row coordinate of target node
     * @param column        column coordinate of target node
     * @return boolean      true if change has been made else false
     */
    public boolean setTarget(int row, int column) {
        if (grid[row][column] != START_NODE) {
            String[] targetSplit = targetCoordinates.split(", ");
            int prevRow = Integer.parseInt(targetSplit[0]);
            int prevColumn = Integer.parseInt(targetSplit[1]);
            grid[prevRow][prevColumn] = EMPTY_NODE;
            grid[row][column] = TARGET_NODE;
            targetCoordinates = row + ", " + column;
            return true;
        }
        return false;
    }

    /**
     * Clears the grid of obstacle nodes
     */
    public void clearObstacles() {
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (grid[row][column] == OBSTACLE_NODE)
                    grid[row][column] = EMPTY_NODE;
            }
        }
    }

    /**
     * Clears the grid of visited and path nodes
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
     * Clears the grid of everything except start and target nodes
     */
    public void clearEverything() {
        clearObstacles();
        clearPath();
    }

    public int getNode(int row, int column) {
        return grid[row][column];
    }

    /**
     * Get the grid
     * @return grid     2D array representing grid
     */
    public int [][] getGrid() {
        return grid;
    }

    /**
     * Performs BFS on grid
     * @return boolean      true if target node is found else false
     */
    public void performBFS() {
        BFSAlgorithm BFSObj = new BFSAlgorithm(grid, startCoordinates);
        if (controller != null) {
            BFSObj.setController(controller);
            BFSObj.BFS();
        }
    }

    /**
     * Performs DFS on grid
     * @return boolean      true if target node is found else false
     */
    public void performDFS() {
        DFSAlgorithm DFSObj = new DFSAlgorithm(grid);
        if (controller != null) {
            DFSObj.setController(controller);
            String[] coordinates = startCoordinates.split(", ");
            DFSObj.DFS(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        }
    }

    /**
     * Perform Dijkstra on grid
     * @return boolean      true if target node is found else false
     */
    public void performDijkstra() {
        DijkstraAlgorithm DijkstraObj = new DijkstraAlgorithm(grid, startCoordinates);
        if (controller != null) {
            DijkstraObj.setController(controller);
            DijkstraObj.Dijkstra();
        }
    }

    /**
     * Perform A* on grid
     * @return boolean      true if target node is found else false
     */
    public void performAStar() {
        AStarAlgorithm AStarObj = new AStarAlgorithm(grid, startCoordinates, targetCoordinates);
        if (controller != null) {
            AStarObj.setController(controller);
            AStarObj.AStar();
        }
    }

}
