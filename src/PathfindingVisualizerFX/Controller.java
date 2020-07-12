package PathfindingVisualizerFX;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import static PathfindingVisualizerFX.Utility.*;

public class Controller {
    private Grid grid;
    @FXML
    private GridPane gridPane;

    /**
     * changes the color of the rectangle and the node on the grid at row and column to an empty node
     * @param rect          rectangle to change the color of
     * @param row           row coordinate of node
     * @param column        column coordinate of node
     */
    private void setEmpty(Rectangle rect, int row, int column) {
        rect.setFill(EMPTY_COLOR);
        grid.changeNode(row, column, EMPTY_NODE);
    }

    /**
     * changes the color of the rectangle and the node on the grid at row and column to an obstacle node
     * @param rect          rectangle to change the color of
     * @param row           row coordinate of node
     * @param column        column coordinate of node
     */
    private void setObstacle(Rectangle rect, int row, int column) {
        rect.setFill(OBSTACLE_COLOR);
        grid.changeNode(row, column, OBSTACLE_NODE);
    }

    /**
     * Updates the entire grid
     */
    private void updateGrid() {
        for (Node node : gridPane.getChildren()) {
            Integer row = GridPane.getRowIndex(node);
            Integer column = GridPane.getColumnIndex(node);
            if (row == null || column == null || !(node instanceof Rectangle)) continue;
            Rectangle rect = (Rectangle)node;
            int status = grid.getNode(row, column);
            switch (status) {
                case EMPTY_NODE:
                    rect.setFill(EMPTY_COLOR);
                    break;
                case START_NODE:
                    rect.setFill(START_COLOR);
                    break;
                case TARGET_NODE:
                    rect.setFill(TARGET_COLOR);
                    break;
                case OBSTACLE_NODE:
                    rect.setFill(OBSTACLE_COLOR);
                    break;
                case VISITED_NODE:
                    rect.setFill(VISITED_COLOR);
                    break;
                case PATH_NODE:
                    rect.setFill(PATH_COLOR);
                    break;
            }
        }
    }

    /**
     * Initializer function that runs when GUI first opens
     */
    public void initialize() {
        System.out.println("START");
        grid = new Grid();
        // mouse event handler that creates or deletes obstacles when mouse drags on grid
        EventHandler<MouseEvent> mouseEventEventHandler = event ->  {
            Rectangle sourceRect = (Rectangle)event.getSource();
            Integer row = GridPane.getRowIndex(sourceRect);
            Integer column = GridPane.getColumnIndex(sourceRect);
            int gridNode = grid.getNode(row, column);
            // change node accordingly
            if (gridNode == EMPTY_NODE) {
                setObstacle(sourceRect, row, column);
            } else if (gridNode == OBSTACLE_NODE) {
                setEmpty(sourceRect, row, column);
            }
        };
        // assign event handler to nodes
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle rect = (Rectangle)node;
                // drag functionality when creating or deleting obstacles
                rect.setOnDragDetected(event -> rect.startFullDrag());
                rect.setOnMouseDragEntered(mouseEventEventHandler);
            }
        }
        // add all nodes in gridPane to nodes
        updateGrid();
    }


}
