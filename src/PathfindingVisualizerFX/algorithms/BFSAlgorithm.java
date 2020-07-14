package PathfindingVisualizerFX.algorithms;

import PathfindingVisualizerFX.Controller;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import static PathfindingVisualizerFX.Utility.*;

import java.util.LinkedList;
import java.util.Queue;

public class BFSAlgorithm {
    private Controller controller;
    private int [][] grid;
    private String startCoordinates;
    private boolean [][] visited;

    /**
     * Constructor for BFSAlgorithm
     * @param grid                  2D array that represents grid
     * @param startCoordinates      coordinates of start node
     */
    public BFSAlgorithm(int [][] grid, String startCoordinates) {
        this.grid = grid;
        this.startCoordinates = startCoordinates;
        visited = new boolean[DIM][DIM];
    }

    /**
     * Set controller
     * @param controller        FXML controller
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Validates whether or not the location is valid
     * @param row               row coordinate of node
     * @param column            column coordinate of node
     * @return boolean          true if valid else false
     */
    private boolean validator(int row, int column) {
        if (row >= 0 && row < DIM && column >= 0 && column < DIM && grid[row][column] != OBSTACLE_NODE && !visited[row][column])
            return true;
        return false;
    }

    /**
     * Perform BFS on grid
     * @return boolean            true if path to target node exists else false
     */
    public boolean BFS() {
        // direction vectors
        int [] rowDir = {-1, 0, 1, 0, -1, 1, 1, -1};
        int [] columnDir = {0, 1, 0, -1, 1, 1, -1, -1};
        // 2D array to keep track of visited nodes
        visited = new boolean[DIM][DIM];
        Queue<String> queue = new LinkedList<>();
        // add start node coordinates to queue
        queue.add(startCoordinates);
        while(!queue.isEmpty()) {
            if (controller != null) {
                System.out.println("Operating on controller");
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> controller.updateGrid());
                pause.play();
            }
            System.out.println(formatGrid(grid));
            // get the row and column coordinates of the current node in queue
            String [] coordinates = queue.remove().split(", ");
            int row = Integer.parseInt(coordinates[0]);
            int column = Integer.parseInt(coordinates[1]);

            // check if node is outside boundary or visited
            if (!validator(row, column)) continue;

            // mark current node as visited and check surrounding (adjacent) nodes
            visited[row][column] = true;

            for (int i = 0; i < 8; i++) {
                int d_row = row + rowDir[i];
                int d_column = column + columnDir[i];
                if (validator(d_row, d_column)) {
                    queue.add(d_row + ", " + d_column);
                    if (grid[d_row][d_column] == EMPTY_NODE) {
                        grid[d_row][d_column] = VISITED_NODE;
                    } else if (grid[d_row][d_column] == TARGET_NODE) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
