package PathfindingVisualizerFX.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    // dimension of grid (length)
    private final int DIM = 20;
    // number code of empty node
    private final int EMPTY_NODE = 0;
    // number code of start node
    private final int START_NODE = 1;
    // number code of end node
    private final int END_NODE = 2;
    // number code of obstacle node
    private final int OBSTACLE_NODE = 3;
    // number code of visited node
    private final int VISITED_NODE = 4;

    /**
     * Sleeps for a duration of time so user can catch up
     */
    private static void delay() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Perform BFS on grid
     * @param grid      grid to pathfind from start node to end node
     */
    public void BFS(int [][] grid, String startCoordinates) {
        // 2D array to keep track of visited nodes
        boolean [][] visited = new boolean[DIM][DIM];
        Queue<String> queue = new LinkedList<>();
        // add start node coordinates to queue
        queue.add(startCoordinates);
        while(!queue.isEmpty()) {
            System.out.println(grid.toString());
            String [] coordinates = queue.remove().split(", ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);

            // check if node is outside boundary or visited
            if (x < 0 || x >= DIM || y < 0 || y >= DIM || visited[x][y]) continue;

            visited[x][y] = true;
            queue.add((x - 1) + ", " + y);
            if (grid[x - 1][y] == EMPTY_NODE) {
                grid[x - 1][y] = VISITED_NODE;
            } else if (grid[x - 1][y] == END_NODE) {
                System.out.println("FOUND");
                System.exit(0);
            }
            queue.add((x + 1) + ", " + y);
            if (grid[x + 1][y] == EMPTY_NODE) {
                grid[x + 1][y] = VISITED_NODE;
            } else if (grid[x + 1][y] == END_NODE) {
                System.out.println("FOUND");
                System.exit(0);
            }
            queue.add(x + ", " + (y - 1));
            if (grid[x][y - 1] == EMPTY_NODE) {
                grid[x][y - 1] = VISITED_NODE;
            } else if (grid[x][y - 1] == END_NODE) {
                System.out.println("FOUND");
                System.exit(0);
            }
            queue.add(x + ", " + (y + 1));
            if (grid[x][y + 1] == EMPTY_NODE) {
                grid[x][y + 1] = VISITED_NODE;
            } else if (grid[x][y + 1] == END_NODE) {
                System.out.println("FOUND");
                System.exit(0);
            }
            delay();
        }
    }

}
