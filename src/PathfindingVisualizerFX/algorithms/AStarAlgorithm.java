package PathfindingVisualizerFX.algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

import static PathfindingVisualizerFX.Utility.DIM;
import static PathfindingVisualizerFX.Utility.OBSTACLE_NODE;

public class AStarAlgorithm {

    public class QueueItem {
        // coordinates of the node
        private String coordinates;
        // current distance from start node
        private int distance;
        // distance left to target node in a straight line
        private double distanceToTarget;

        /**
         * Constructor for QueueItem for a comparator to be used in pQueue
         * @param coordinates           coordinates of the node
         * @param distance              current distance from start node
         * @param distanceToTarget      distance left to target node in a straight line
         */
        public QueueItem(String coordinates, int distance, double distanceToTarget) {
            this.coordinates = coordinates;
            this.distance = distance;
            this.distanceToTarget = distanceToTarget;
        }

        /**
         * Getter method for coordinates
         * @return coordinates          coordinates of the node
         */
        public String getCoordinates() {
            return coordinates;
        }

        /**
         * Getter method for distance
         * @return distance             distance from start node
         */
        public int getDistance() {
            return distance;
        }

        /**
         * Getter method for distanceToTarget
         * @return distanceToTarget     distance to target node in a straight line
         */
        public double getDistanceToTarget() {
            return distanceToTarget;
        }

        /**
         * Get the heuristic distance to estimate best path in a* algorithm
         * @return heuristicDistance    distance + distanceToTarget
         */
        public double getHeuristicDistance() {
            return distance + distanceToTarget;
        }
    }


    /**
     * A* is just Dijkstra's algorithm with added heuristics (distance to target node) added to path
     * For more information, watch Computerphile's video: https://www.youtube.com/watch?v=ySN5Wnu88nE
     */

    private int [][] grid;
    private String startCoordinates;
    private String targetCoordinates;
    private double [][] distancesToTarget;
    private PriorityQueue<QueueItem> pQueue;

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
        distancesToTarget = new double[DIM][DIM];
        //
        calculateDistanceToTarget();
        // comparator for priority queue (pQueue)
        Comparator<QueueItem> comparator = new Comparator<QueueItem>() {
            @Override
            public int compare(QueueItem o1, QueueItem o2) {
                if (o1.getHeuristicDistance() - o2.getHeuristicDistance() <= 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        // priority queue to queue items from least total distance to
        pQueue = new PriorityQueue<>(comparator);
    }

    /**
     * Calculates the distances from each node to the target node
     */
    public void calculateDistanceToTarget() {
        String [] targetSplit = startCoordinates.split(", ");
        int targetRow = Integer.parseInt(targetSplit[0]);
        int targetColumn = Integer.parseInt(targetSplit[1]);
        System.out.println(startCoordinates);
        System.out.println(targetCoordinates);
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                if (grid[row][column] != OBSTACLE_NODE) {
                    distancesToTarget[row][column] = Math.sqrt(Math.pow(targetRow - row, 2) + Math.pow(targetColumn - column, 2));
                } else {
                    distancesToTarget[row][column] = -1;
                }
            }
        }
    }



}
