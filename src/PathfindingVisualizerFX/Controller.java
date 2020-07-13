package PathfindingVisualizerFX;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

import static PathfindingVisualizerFX.Utility.*;

public class Controller {
    // start node is being dragged
    private boolean startDrag;
    // target node is being dragged
    private boolean targetDrag;
    // lock on both start and target drag
    private boolean dragLock;
    // lock on everything unless reset
    private boolean lock;
    // stores which algorithm to run and its button
    private String algorithm;
    private Button algoBtn;
    // the grid to refer to when updating the ui gridpane
    private Grid grid;
    // stores the current start node/rectangle
    private Rectangle startRect;
    // stores the current target node/rectangle
    private Rectangle targetRect;

    @FXML
    private GridPane gridPane;
    @FXML
    private Button bfs, dfs, dijkstra, astar, resetPath, resetObstacles, resetEverything, run;



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
     * Clears the obstacle nodes
     */
    private void clearObstacles() {
        grid.clearObstacles();
        updateGrid();
    }

    /**
     * Clears the visited and path nodes
     */
    private void clearPath() {
        grid.clearPath();
        updateGrid();
    }

    /**
     * Clears every node except start and target nodes
     */
    private void clearEverything() {
        grid.clearEverything();
        updateGrid();
    }

    /**
     * Runs the chosen algorithm
     * @param algorithm     algorithm to run
     */
    private void runAlgorithm(String algorithm) {
        if (!lock) {
            lock = true;
            switch (algorithm) {
                case "bfs":
                    if (grid.performBFS()) {
                        System.out.println("FOUND");
                    } else {
                        System.out.println("NOT FOUND");
                    }
                    updateGrid();
                    break;
                case "dfs":
                    if (grid.performDFS()) {
                        System.out.println("FOUND");
                    } else {
                        System.out.println("NOT FOUND");
                    }
                    updateGrid();
                    break;
                case "dijkstra":
                    if (grid.performDijkstra()) {
                        System.out.println("FOUND");
                    } else {
                        System.out.println("NOT FOUND");
                    }
                    updateGrid();
                    break;
                case "astar":
                    if (grid.performAStar()) {
                        System.out.println("FOUND");
                    } else {
                        System.out.println("NOT FOUND");
                    }
                    updateGrid();
                    break;

            }
        }
    }

    /**
     * Updates the entire grid
     */
    public void updateGrid() {
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
        // boolean initialized
        startDrag = false;
        targetDrag = false;
        dragLock = false;

        grid = new Grid();

        // mouse event handler that creates or deletes obstacles when mouse clicks on grid
        EventHandler<MouseEvent> mouseClickHandler = event -> {
            if (!lock) {
                Rectangle sourceRect = (Rectangle) event.getSource();
                Integer row = GridPane.getRowIndex(sourceRect);
                Integer column = GridPane.getColumnIndex(sourceRect);
                int gridNode = grid.getNode(row, column);
                // change node accordingly
                if (gridNode == EMPTY_NODE) {
                    setObstacle(sourceRect, row, column);
                } else if (gridNode == OBSTACLE_NODE) {
                    setEmpty(sourceRect, row, column);
                }
            }
        };

        // mouse event handler that creates or deletes obstacles when mouse drags on grid
        EventHandler<MouseEvent> mouseDragHandler = event ->  {
            if (!lock) {
                Rectangle sourceRect = (Rectangle) event.getSource();
                Integer row = GridPane.getRowIndex(sourceRect);
                Integer column = GridPane.getColumnIndex(sourceRect);
                int gridNode = grid.getNode(row, column);
                // change node accordingly, if initial node is the start or target, drag that else create/delete obstacles
                if ((gridNode == START_NODE || startDrag) && !dragLock) {
                    startDrag = true;
                    setStart(sourceRect, startRect, row, column);
                } else if ((gridNode == TARGET_NODE || targetDrag) && !dragLock) {
                    targetDrag = true;
                    setTarget(sourceRect, targetRect, row, column);
                    // create/delete obstacles
                } else {
                    dragLock = true;
                    if (gridNode == EMPTY_NODE) {
                        setObstacle(sourceRect, row, column);
                    } else if (gridNode == OBSTACLE_NODE) {
                        setEmpty(sourceRect, row, column);
                    }
                }
            }
        };

        // mouse event handler for mouse release
        EventHandler<MouseEvent> mouseReleaseHandler = event -> {
            startDrag = false;
            targetDrag = false;
            dragLock = false;
        };

        // mouse event handler for algorithm buttons that changes the algorithm chosen
        EventHandler<MouseEvent> algorithmClickHandler = event -> {
            if (algoBtn != null)
                algoBtn.setStyle("-fx-background-color: white; -fx-text-fill: black");
            algoBtn = (Button)event.getSource();
            algoBtn.setStyle("-fx-background-color: black; -fx-text-fill: red");
            algorithm = algoBtn.getId();
            System.out.println(algorithm);
        };

        // mouse event handler for reset buttons
        EventHandler<MouseEvent> resetClickHandler = event -> {
            Button btn = (Button)event.getSource();
            String reset = btn.getId();
            switch (reset) {
                case "resetObstacles":
                    clearObstacles();
                    break;
                case "resetPath":
                    clearPath();
                    lock = false;
                    break;
                case "resetEverything":
                    clearEverything();
                    lock = false;
                    break;
            }
        };

        // mouse event handler for run button
        EventHandler<MouseEvent> runClickHandler = event -> {
            if (algorithm != null)
                runAlgorithm(algorithm);
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

        // assign event handler to algorithm chooser buttons
        dfs.setOnMouseClicked(algorithmClickHandler);
        bfs.setOnMouseClicked(algorithmClickHandler);
        dijkstra.setOnMouseClicked(algorithmClickHandler);
        astar.setOnMouseClicked(algorithmClickHandler);

        // assign event handler to clear buttons
        resetObstacles.setOnMouseClicked(resetClickHandler);
        resetPath.setOnMouseClicked(resetClickHandler);
        resetEverything.setOnMouseClicked(resetClickHandler);

        // assign event handler to run button
        run.setOnMouseClicked(runClickHandler);

        /*// todo
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                updateGrid();
                System.out.println("TASK");
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 150, 10);*/
        updateGrid();
    }

}
