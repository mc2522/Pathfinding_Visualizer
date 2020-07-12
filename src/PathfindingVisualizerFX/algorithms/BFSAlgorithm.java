package PathfindingVisualizerFX.algorithms;

import PathfindingVisualizerFX.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import static PathfindingVisualizerFX.Utility.*;
import static PathfindingVisualizerFX.Controller.*;

import java.util.LinkedList;
import java.util.Queue;

public class BFSAlgorithm {

    private Controller controller;
    private int [][] grid;
    private String startCoordinates;

    public BFSAlgorithm(int [][] grid, String startCoordinates) {
        this.grid = grid;
        this.startCoordinates = startCoordinates;
    }

    /**
     * Perform BFS on grid
     * @return boolean              true if path to target node exists else false
     */
    public boolean BFS() {
        // 2D array to keep track of visited nodes
        boolean [][] visited = new boolean[DIM][DIM];
        Queue<String> queue = new LinkedList<>();
        // add start node coordinates to queue
        queue.add(startCoordinates);
        while(!queue.isEmpty()) {

            System.out.println(formatGrid(grid));

            // get the row and column coordinates of the current node in queue
            String [] coordinates = queue.remove().split(", ");
            int row = Integer.parseInt(coordinates[0]);
            int column = Integer.parseInt(coordinates[1]);

            // check if node is outside boundary or visited
            if (row < 0 || row >= DIM || column < 0 || column >= DIM || visited[row][column]) continue;

            // mark current node as visited and check surrounding (adjacent) nodes
            visited[row][column] = true;
            if (row > 0) {
                queue.add((row - 1) + ", " + column);
                if (grid[row - 1][column] == EMPTY_NODE) {
                    grid[row - 1][column] = VISITED_NODE;
                } else if (grid[row - 1][column] == TARGET_NODE) {
                    return true;
                }
            }
            if (row < DIM - 1) {
                queue.add((row + 1) + ", " + column);
                if (grid[row + 1][column] == EMPTY_NODE) {
                    grid[row + 1][column] = VISITED_NODE;
                } else if (grid[row + 1][column] == TARGET_NODE) {
                    return true;
                }
            }
            if (column > 0) {
                queue.add(row + ", " + (column - 1));
                if (grid[row][column - 1] == EMPTY_NODE) {
                    grid[row][column - 1] = VISITED_NODE;
                } else if (grid[row][column - 1] == TARGET_NODE) {
                    return true;
                }
            }
            if (column < DIM - 1) {
                queue.add(row + ", " + (column + 1));
                if (grid[row][column + 1] == EMPTY_NODE) {
                    grid[row][column + 1] = VISITED_NODE;
                } else if (grid[row][column + 1] == TARGET_NODE) {
                    return true;
                }
            }
            shortDelay();
        }
        return false;
    }

}
