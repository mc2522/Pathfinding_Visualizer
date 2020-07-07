package PathfindingVisualizerFX.algorithms;

import java.util.*;

import  static PathfindingVisualizerFX.Utility.*;

public class DijkstraAlgorithm {
    // coordinates of start node
    private int startRow;
    private int startColumn;
    // 2D array to represent grid
    private int [][] grid;
    // array to mark unvisited nodes
    private boolean [][] unvisited;
    // counter for number of unvisited nodes in unvisited so we don't have to search the whole 2D array every time
    private int unvisitedCounter;
    // 2D array to record previous shortest path of nodes
    private String [][] prev;
    // queue to store coordinates
    private Queue<String> queue = new LinkedList<>();

    /**
     * Constructor for DijkstraAlgorithm
     * @param grid                  2D array to represent grid
     * @param startCoordinates      coordinates of start node
     */
    public DijkstraAlgorithm(int [][] grid, String startCoordinates) {
        this.grid = grid;
        unvisited = new boolean[DIM][DIM];
        prev = new String[DIM][DIM];
        unvisitedCounter = DIM * DIM;
        // initialize all values in distances and unvisited
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (grid[row][column] == OBSTACLE_NODE) {
                    unvisited[row][column] = false;
                    prev[row][column] = "X, X";
                } else {
                    unvisited[row][column] = true;
                    prev[row][column] = "O, O";
                }
            }
        }
        // get start coordinates
        String [] startSplit = startCoordinates.split(", ");
        startRow = Integer.parseInt(startSplit[0]);
        startColumn = Integer.parseInt(startSplit[1]);
        prev[startRow][startColumn] = "START";
    }

    /**
     * Marks the shortest path on the grid by backtracking from target node in prev
     */
    public void markPath(int row, int column) {
        String previous = prev[row][column];
        if (grid[row][column] == VISITED_NODE) {
            grid[row][column] = PATH_NODE;
            System.out.println(formatGrid(grid));
        } else if (grid[row][column] == START_NODE) {
            return;
        }
        String [] previousSplit = previous.split(", ");
        int previousRow = Integer.parseInt(previousSplit[0]);
        int previousColumn = Integer.parseInt(previousSplit[1]);
        markPath(previousRow, previousColumn);
    }

    /**
     * Run the dijkstra algorithm on the grid
     * @return boolean      true if path from start node to target node is found else false
     */
    public boolean Dijkstra() {
        // add start node coordinates to queue to begin with
        queue.add(startRow + ", " + startColumn);
        // keep calculating node distances while there are unvisited nodes
        while (unvisitedCounter > 0 || !queue.isEmpty()) {
            System.out.println(formatGrid(grid));

            // get the row and column coordinates of the current node in queue
            String [] coordinates = queue.remove().split(", ");
            int row = Integer.parseInt(coordinates[0]);
            int column = Integer.parseInt(coordinates[1]);

            // check if node is outside boundary or should not be visited
            if (row < 0 || row >= DIM || column < 0 || column >= DIM || !unvisited[row][column] || prev[row][column].equals("X, X")) continue;

            // update unvisited
            unvisited[row][column] = false;
            unvisitedCounter--;

            // check neighbouring nodes and assign their prev node to keep track of shortest path
            if (row > 0) {
                queue.add((row - 1) + ", " + column);
                if (grid[row - 1][column] == EMPTY_NODE) {
                    grid[row - 1][column] = VISITED_NODE;
                    prev[row - 1][column] = row + ", " + column;
                } else if (grid[row - 1][column] == TARGET_NODE) {
                    prev[row - 1][column] = row + ", " + column;
                    markPath(row - 1, column);
                    return true;
                }
            }
            if (row < DIM - 1) {
                queue.add((row + 1) + ", " + column);
                if (grid[row + 1][column] == EMPTY_NODE) {
                    grid[row + 1][column] = VISITED_NODE;
                    prev[row + 1][column] = row + ", " + column;
                } else if (grid[row + 1][column] == TARGET_NODE) {
                    prev[row + 1][column] = row + ", " + column;
                    markPath(row + 1, column);
                    return true;
                }
            }
            if (column > 0) {
                queue.add(row + ", " + (column - 1));
                if (grid[row][column - 1] == EMPTY_NODE) {
                    grid[row][column - 1] = VISITED_NODE;
                    prev[row][column - 1] = row + ", " + column;
                } else if (grid[row][column - 1] == TARGET_NODE) {
                    prev[row][column - 1] = row + ", " + column;
                    markPath(row, column - 1);
                    return true;
                }
            }
            if (column < DIM - 1) {
                queue.add(row + ", " + (column + 1));
                if (grid[row][column + 1] == EMPTY_NODE) {
                    grid[row][column + 1] = VISITED_NODE;
                    prev[row][column + 1] = row + ", " + column;
                } else if (grid[row][column + 1] == TARGET_NODE) {
                    prev[row][column + 1] = row + ", " + column;
                    markPath(row, column + 1);
                    return true;
                }
            }
            shortDelay();
        }
        return false;
    }

}
