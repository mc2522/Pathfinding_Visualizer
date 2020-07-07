package PathfindingVisualizerFX.algorithms;

import java.util.Arrays;

import static PathfindingVisualizerFX.Utility.*;

public class DFSAlgorithm {

    // visited array for each row in grid
    private boolean [][] visited;
    // 2D array to represent grid
    private int [][] grid;
    // direction vectors
    private int rowDir[];
    private int columnDir[];

    /**
     * Constructor for DFSAlgorithm
     */
    public DFSAlgorithm(int [][] grid) {
        visited = new boolean[DIM][DIM];
        for (int row = 0; row < DIM; row++)
            Arrays.fill(visited[row], false);
        this.grid = grid;
        rowDir = new int[] {-1, 0, 1, 0};
        columnDir = new int[] {0, 1, 0, -1};
    }

    /**
     * Validates that the node is within grid bounds or is an empty node
     * @param row         row coordinate of node
     * @param column         column coordinate of node
     * @return boolean  true if within bounds or epty node else false
     */
    public boolean validator(int row, int column) {
        if (row >= 0 && row < DIM && column >=0 && column < DIM && !visited[row][column])
            return true;
        return false;
    }

    /**
     * Perform DFS on grid
     */
    public boolean DFS(int row, int column) {
        int d_row, d_column;
        visited[row][column] = true;
        if (grid[row][column] == EMPTY_NODE) {
            grid[row][column] = VISITED_NODE;
            System.out.println(formatGrid(grid));
            shortDelay();
        } else if (grid[row][column] == TARGET_NODE) {
            return true;
        }

        for (int index = 0; index < 4; index++) {
            d_row = row + rowDir[index];
            d_column = column + columnDir[index];

            if (validator(d_row, d_column)) {
                return DFS(d_row, d_column);
            }
        }
        return false;
    }

}
