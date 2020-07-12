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
    private boolean startDrag;
    private boolean targetDrag;
    private Grid grid;
    @FXML
    private GridPane gridPane;
    private Rectangle startRect;
    private Rectangle targetRect;

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
     * Set the start node to a different location in the grid
     * @param rect          node to represent start node
     * @param prevRect      previous node that represented start node
     * @param row           row coordinate of new start node
     * @param column        column coordinate of new start node
     */
    private void setStart(Rectangle rect, Rectangle prevRect, int row, int column) {
        if (grid.setStart(row, column)) {
            prevRect.setFill(EMPTY_COLOR);
            rect.setFill(START_COLOR);
            startRect = rect;
        }
    }

    /**
     * Set the target node to a different location in the grid
     * @param rect          node to represent target node
     * @param prevRect      previous node that represented target node
     * @param row           row coordinate of new target node
     * @param column        column coordinate of new target node
     */
    private void setTarget(Rectangle rect, Rectangle prevRect, int row, int column) {
        if (grid.setTarget(row, column)) {
            prevRect.setFill(EMPTY_COLOR);
            rect.setFill(TARGET_COLOR);
            targetRect = rect;
        }
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
                    startRect = rect;
                    rect.setFill(START_COLOR);
                    break;
                case TARGET_NODE:
                    targetRect = rect;
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

        startDrag = false;
        targetDrag = false;
        grid = new Grid();

        EventHandler<MouseEvent> mouseClickHandler = event -> {
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

        // mouse event handler that creates or deletes obstacles when mouse drags on grid
        EventHandler<MouseEvent> mouseDragHandler = event ->  {
            Rectangle sourceRect = (Rectangle)event.getSource();
            Integer row = GridPane.getRowIndex(sourceRect);
            Integer column = GridPane.getColumnIndex(sourceRect);
            // TODO MOVE START AND TARGET NODE
            int gridNode = grid.getNode(row, column);
            // change node accordingly
            if (gridNode == START_NODE || startDrag) {
                startDrag = true;
                setStart(sourceRect, startRect, row, column);
            } else if (gridNode == TARGET_NODE || targetDrag) {
                targetDrag = true;
                setTarget(sourceRect, targetRect, row, column);
            } else if (!startDrag && !targetDrag) {
                if (gridNode == EMPTY_NODE) {
                    setObstacle(sourceRect, row, column);
                } else if (gridNode == OBSTACLE_NODE) {
                    setEmpty(sourceRect, row, column);
                }
            }
        };

        // mouse event handler for mouse release
        EventHandler<MouseEvent> mouseReleaseHandler = event -> {
            startDrag = false;
            targetDrag = false;
        };

        // assign event handler to nodes
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle rect = (Rectangle)node;
                // click/drag functionality when creating or deleting obstacles
                rect.setOnMouseClicked(mouseClickHandler);
                rect.setOnDragDetected(event -> rect.startFullDrag());
                rect.setOnMouseDragEntered(mouseDragHandler);
                rect.setOnMouseReleased(mouseReleaseHandler);
            }
        }
        // add all nodes in gridPane to nodes
        updateGrid();
    }


}
