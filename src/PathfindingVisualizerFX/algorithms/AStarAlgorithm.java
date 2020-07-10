package PathfindingVisualizerFX.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

import static PathfindingVisualizerFX.Utility.*;

public class AStarAlgorithm {

    public class QueueItem {
        // coordinates of the node
        private String coordinates;
        // current distanceFromStart from start node
        private double distanceFromStart;
        // distanceFromStart left to target node in a straight line
        private double distanceToTarget;
        // heuristic distanceFromStart to influence search path
        private double heuristicDistance;

        /**
         * Constructor for QueueItem for a comparator to be used in pQueue
         * @param coordinates           coordinates of the node
         * @param distance              current distanceFromStart from start node
         * @param distanceToTarget      distanceFromStart left to target node in a straight line
         */
        public QueueItem(String coordinates, double distanceFromStart, double distanceToTarget) {
            this.coordinates = coordinates;
            this.distanceFromStart = distanceFromStart;
            this.distanceToTarget = distanceToTarget;
            heuristicDistance = distanceFromStart + distanceToTarget;
        }

        /**
         * Getter method for coordinates
         * @return coordinates          coordinates of the node
         */
        public String getCoordinates() {
            return coordinates;
        }

        /**
         * Getter method for distanceFromStart
         * @return distanceFromStart             distanceFromStart from start node
         */
        public double getDistanceFromStart() {
            return distanceFromStart;
        }

        /**
         * Getter method for distanceToTarget
         * @return distanceToTarget     distanceFromStart to target node in a straight line
         */
        public double getDistanceToTarget() {
            return distanceToTarget;
        }

        /**
         * Get the heuristic distanceFromStart to estimate best path in a* algorithm
         * @return heuristicDistance    distanceFromStart + distanceToTarget
         */
        public double getHeuristicDistance() {
            return heuristicDistance;
        }
    }


    /**
     * A* is just Dijkstra's algorithm with added heuristics (distanceFromStart to target node) added to path
     * For more information, watch Computerphile's video: https://www.youtube.com/watch?v=ySN5Wnu88nE
     */

    // 2D array to represent grid
    private int [][] grid;
    // coordinates of start node
    private String startCoordinates;
    private int startRow;
    private int startColumn;
    // coordinates of target node
    private String targetCoordinates;
    private int targetRow;
    private int targetColumn;
    // 2D array to store all nodes' distances to target node
    private double [][] distancesToTarget;
    // 2D array to store all nodes' distances from start node
    private double [][] distancesFromStart;
    // priority queue to determine which nodes get processed first
    private PriorityQueue<QueueItem> pQueue;
    // 2D array to store booleans on whether or not a node has processed
    private boolean [][] processed;
    // 2D array to store previous node coordinates
    private String [][] prev;

    /**
     * Constructor for AStarAlgorithm
     * @param grid                  2D array to represent grid
     * @param startCoordinates      coordinates of start node
     * @param targetCoordinates     coordinates of target node
     */
    public AStarAlgorithm(int [][] grid, String startCoordinates, String targetCoordinates) {
        this.grid = grid;
        // convert start coordinates
        this.startCoordinates = startCoordinates;
        String [] startSplit = startCoordinates.split(", ");
        this.startRow = Integer.parseInt(startSplit[0]);
        this.startColumn = Integer.parseInt(startSplit[1]);
        // convert target coordinates
        this.targetCoordinates = targetCoordinates;
        String [] targetSplit = targetCoordinates.split(", ");
        this.targetRow = Integer.parseInt(targetSplit[0]);
        this.targetColumn = Integer.parseInt(targetSplit[1]);
        // initialize values
        distancesToTarget = new double[DIM][DIM];
        distancesFromStart = new double[DIM][DIM];
        processed = new boolean[DIM][DIM];
        prev = new String[DIM][DIM];
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (grid[row][column] == OBSTACLE_NODE) {
                    processed[row][column] = true;
                    prev[row][column] = "X, X";
                } else {
                    processed[row][column] = false;
                    prev[row][column] = "";
                }
            }
        }
        // calculate all nodes' distanceFromStart to target node
        calculateDistances();
        // comparator for priority queue (pQueue)
        Comparator<QueueItem> comparator = (o1, o2) -> {
            if (o1.getHeuristicDistance() <= o2.getHeuristicDistance())
                return -1;
            return 1;
        };
        // priority queue to queue items from least heuristic distanceFromStart to most heuristic distanceFromStart
        pQueue = new PriorityQueue<>(comparator);
    }

    /**
     * Looks into pQueue and checks if there is a node with a specific coordinate
     * @return ret      QueueItem if node with coordinate is in pQueue else null
     */
    public QueueItem containsNode(int row, int column) {
        for (QueueItem item : pQueue) {
            String [] coordinatesSplit = item.getCoordinates().split(", ");
            int checkRow = Integer.parseInt(coordinatesSplit[0]);
            int checkColumn = Integer.parseInt(coordinatesSplit[1]);
            if (row == checkRow && column == checkColumn)
                return item;
        }
        return null;
    }

    /**
     * set the coordinates in processed to true
     * @param item      QueueItem that holds the coordinates
     */
    public void markProcessed(QueueItem item) {
        String [] coordinatesSplit = item.getCoordinates().split(", ");
        int row = Integer.parseInt(coordinatesSplit[0]);
        int column = Integer.parseInt(coordinatesSplit[1]);
        // todo DEBUG / TEST
        if (processed[row][column]) {
            System.out.println("SOMETHING IS WRONG, REMOVED IS ALREADY PROCESSED!");
            System.exit(1);
        }
        processed[row][column] = true;
    }

    /**
     * Looks at all the neighbouring nodes and adds them into pQueue to be processed later
     * @param item      current item in pQueue
     */
    public void lookAtNeighbours(QueueItem item) {
        // convert coordinates
        String [] coordinatesSplit = item.getCoordinates().split(", ");
        int row = Integer.parseInt(coordinatesSplit[0]);
        int column = Integer.parseInt(coordinatesSplit[1]);
        // direction vectors
        int [] dRow = {-1, 0, 1, 0};
        int [] dColumn = {0, 1, 0, -1};
        // look at the top, left, bottom, and right nodes and check if they're processed or obstacle nodes and add them
        // to pQueue accordingly
        for (int i = 0; i < 4; i++) {
            int updatedRow = row + dRow[i];
            int updatedColumn = column + dColumn[i];
            // check boundaries
            if (updatedRow >= 0 && updatedRow < DIM && updatedColumn >= 0 && updatedColumn < DIM
                && !processed[updatedRow][updatedColumn]
                && distancesToTarget[updatedRow][updatedColumn] != -1) {

                // first create the QueueItem to compare later or add
                QueueItem pendingItem = new QueueItem(
                    updatedRow + ", " + updatedColumn,
                    distancesFromStart[updatedRow][updatedColumn],
                    distancesToTarget[updatedRow][updatedColumn]
                );

                // check if node is already in the pQueue and compare heuristic distances if so
                QueueItem compareItem = containsNode(updatedRow, updatedColumn);
                if (compareItem == null) {
                    prev[updatedRow][updatedColumn] = item.getCoordinates();
                    pQueue.add(pendingItem);
                } else if (pendingItem.getHeuristicDistance() < compareItem.getHeuristicDistance()) {
                    prev[updatedRow][updatedColumn] = item.getCoordinates();
                    pQueue.remove(compareItem);
                    pQueue.add(pendingItem);
                }
                // change to visited node
                if (grid[updatedRow][updatedColumn] != TARGET_NODE) {
                    grid[updatedRow][updatedColumn] = VISITED_NODE;
                    System.out.println(formatGrid(grid));
                }
                shortDelay();
            }
        }
    }

    /**
     * Calculates the distances from each node to the target node and start node
     */
    public void calculateDistances() {
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (grid[row][column] != OBSTACLE_NODE) {
                    // pythagorean theorem to calculate straight line distanceFromStart to target node
                    distancesToTarget[row][column] = Math.sqrt(Math.pow(targetRow - row, 2) + Math.pow(targetColumn - column, 2));
                    distancesFromStart[row][column] = Math.sqrt(Math.pow(row - startRow, 2) + Math.pow(column - startColumn, 2));
                } else {
                    distancesToTarget[row][column] = -1;
                }
            }
        }
    }

    /**
     * Marks the shortest path on the grid by backtracking from target node in prev
     */
    public void markPath(int row, int column) {
        String previous = prev[row][column];
        if (grid[row][column] == VISITED_NODE) {
            grid[row][column] = PATH_NODE;
            System.out.println(formatGrid(grid));
            shortDelay();
        } else if (grid[row][column] == START_NODE) {
            return;
        }
        String [] previousSplit = previous.split(", ");
        int previousRow = Integer.parseInt(previousSplit[0]);
        int previousColumn = Integer.parseInt(previousSplit[1]);
        markPath(previousRow, previousColumn);
    }

    /**
     * Perform A* algorithm on grid
     * @return boolean      true if a path to target node exists else false
     */
    public boolean AStar() {
        // mark start node's previous node in prev
        prev[startRow][startColumn] = "START";
        // add start node to begin with
        pQueue.add(new QueueItem(startCoordinates, 0, distancesToTarget[startRow][startColumn]));
        while (!pQueue.isEmpty()) {
            System.out.println();
            shortDelay();
            QueueItem removed = pQueue.remove();
            // mark removed as processed
            markProcessed(removed);
            // the moment the removed element is the target node, we finish the algorithm
            if (removed.getCoordinates().equals(targetCoordinates)) {
                String [] targetCoordinatesSplit = targetCoordinates.split(", ");
                int targetRow = Integer.parseInt(targetCoordinatesSplit[0]);
                int targetColumn = Integer.parseInt(targetCoordinatesSplit[1]);
                markPath(targetRow, targetColumn);
                return true;
            }
            // look at the neighbours of the removed item and add them to pQueue
            lookAtNeighbours(removed);
        }
        return false;
    }

}
