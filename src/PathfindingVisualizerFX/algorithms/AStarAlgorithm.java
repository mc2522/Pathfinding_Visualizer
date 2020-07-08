package PathfindingVisualizerFX.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

import static PathfindingVisualizerFX.Utility.DIM;

public class AStarAlgorithm {

    /**
     * A* is just Dijkstra's algorithm with added heuristics (distance to target node) added to path
     * For more information, watch Computerphile's video: https://www.youtube.com/watch?v=ySN5Wnu88nE
     */

    private int [][] grid;
    private String startCoordinates;
    private String targetCoordinates;
    private double [][] heuristicDistances;
    private PriorityQueue<String> pQueue;

    /**
     * Constructor for AStarAlgorithm
     * @param grid                  2D array to represent grid
     * @param startCoordinates      coordinates of start node
     * @param targetCoordinates     coordinates of target node
     */
    public AStarAlgorithm(int [][] grid, String startCoordinates, String targetCoordinates) {
        this.grid = grid;
        this.startCoordinates = startCoordinates;
        this.targetCoordinates = targetCoordinates;
        heuristicDistances = new double[DIM][DIM];
        calculateHeuristicDistances();
        // todo create comparator for priority queue

        // todo initialize pQueue with comparator

    }


    /**
     * Calculates the distances from each node to the target node
     */
    public void calculateHeuristicDistances() {
        String [] targetSplit = startCoordinates.split(", ");
        int targetRow = Integer.parseInt(targetSplit[0]);
        int targetColumn = Integer.parseInt(targetSplit[1]);
        System.out.println(startCoordinates);
        System.out.println(targetCoordinates);
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++)
                heuristicDistances[row][column] = Math.sqrt(Math.pow(targetRow - row, 2) + Math.pow(targetColumn - column, 2));
        }
    }

}
