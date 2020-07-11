package PathfindingVisualizerFX;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import static PathfindingVisualizerFX.Utility.*;

public class Controller {
    private Grid grid;
    private GridPane gridPane;

    /**
     * Gets a specific node in gridPane
     * @param row       row of gridPane
     * @param column    column of gridPane
     * @return node     element in the row and column of gridPane
     */
    private Node getNode(int row, int column) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column)
                return node;
        }
        return null;
    }

    /**
     * Updates the grid
     */
    private void updateGrid() {
        longDelay();
        for (int row = 0; row < DIM; row++) {
            for (int column = 0; column < DIM; column++) {
                switch (grid.getGrid()[row][column]) {
                    case EMPTY_NODE:
                        getNode(row, column).setStyle("-fx-background-color: blue");
                        break;
                    case START_NODE:
                        break;
                    case TARGET_NODE:
                        break;
                    case VISITED_NODE:
                        break;
                    case PATH_NODE:
                        break;
                }
            }
        }
    }

    public void start(ActionEvent e) {
        grid = new Grid();

    }


}
