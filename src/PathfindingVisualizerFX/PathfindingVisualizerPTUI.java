package PathfindingVisualizerFX;

import java.util.Scanner;
import static PathfindingVisualizerFX.Utility.*;

public class PathfindingVisualizerPTUI {
    // Scanner for input
    private static Scanner scan;
    // flag for whether or not grid needs to be reset
    private static boolean solved;
    // display message
    private final static String MESSAGE =
        "\nPATHFINDING VISUALIZER\n----------" +
        "\nNUMBER CODES\n----------" +
        "\nEMPTY NODE =\t\t0\nSTART NODE =\t\t1\nTARGET NODE =\t\t2\nOBSTACLE NODE =\t\t3\nVISITED NODE:\t\t4\nCHOSEN PATH:\t\t5" +
        "\n----------" +
        "\nCOMMANDS:" +
        "\n1. HELP" +
        "\n2. ALGORITHMS" +
        "\n3. PRESETS" +
        "\n4. CLEAR PATH" +
        "\n5. EXIT";
    // algorithms choices
    private final static String ALGORITHMS = "\nSELECT THE NUMBER YOU WANT TO RUN\n----------" +
        "\n1. BFS (Breadth First Search)" +
        "\n2. DFS (Depth First Search)" +
        "\n3. Dijkstra" +
        "\n4. A*" +
        "\n5. Go Back" +
        "\n\nYour algorithm number: ";
    // preset choices
    private final static String PRESETS =
        "\nCHOOSE YOUR PRESET (NUMBER):" +
        "\n----------" +
        "\n1.\t3 3 0 0 3 0 3 0 0 3 0 0 3 0 3 0 0 0 0 3" +
        "\n\t3 3 1 3 0 0 0 0 3 3 0 3 3 0 0 3 3 3 0 3" +
        "\n\t0 3 0 0 3 0 0 3 3 0 0 0 0 3 0 0 0 3 0 3" +
        "\n\t0 0 0 3 0 3 0 0 3 0 0 3 3 0 3 0 3 0 0 0" +
        "\n\t3 3 0 0 0 0 3 0 0 3 0 3 0 0 3 0 3 0 3 3" +
        "\n\t0 3 0 3 3 0 0 0 3 0 0 3 0 0 0 0 0 0 3 0" +
        "\n\t0 0 0 0 3 3 3 3 3 0 3 0 3 3 3 3 0 3 3 0" +
        "\n\t3 3 0 0 3 0 3 0 0 3 0 0 3 0 3 0 0 0 0 3" +
        "\n\t3 0 0 3 0 0 0 0 3 3 0 3 3 0 0 3 3 3 0 3" +
        "\n\t0 3 0 0 3 0 0 3 3 0 0 0 0 3 0 0 0 3 0 3" +
        "\n\t0 0 0 3 0 0 0 0 3 0 0 3 3 0 3 0 3 0 0 0" +
        "\n\t3 3 0 0 0 3 0 0 3 3 3 3 3 0 3 0 3 0 3 3" +
        "\n\t3 3 0 0 3 3 3 0 3 3 3 3 3 0 3 3 3 0 3 3" +
        "\n\t3 3 0 0 3 3 3 0 3 3 3 3 3 0 0 0 0 0 3 3" +
        "\n\t3 3 0 0 3 0 3 0 0 0 0 0 0 0 3 0 3 0 0 3" +
        "\n\t3 0 0 3 0 0 0 0 3 3 0 3 3 0 0 3 3 3 0 3" +
        "\n\t0 3 0 0 3 0 0 3 3 0 0 0 0 0 0 2 0 3 0 3" +
        "\n\t0 0 0 3 0 3 0 0 3 0 0 3 3 0 3 0 3 0 0 0" +
        "\n\t3 3 0 0 0 0 0 0 0 0 0 0 0 0 3 3 3 3 3 3" +
        "\n\t0 0 0 3 3 3 3 3 0 0 3 3 3 0 0 0 0 0 0 3" +
        "\n" +
        "\n2.\t3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3" +
        "\n\t3 0 3 0 0 3 0 0 3 0 0 3 0 0 3 0 0 3 1 3" +
        "\n\t3 0 0 3 0 0 3 0 0 0 0 0 3 0 0 3 0 0 0 3" +
        "\n\t3 3 0 0 3 0 0 0 0 0 3 0 0 3 0 0 0 0 0 3" +
        "\n\t3 0 3 0 0 3 0 0 3 0 0 3 0 0 3 0 0 3 0 3" +
        "\n\t3 0 0 3 0 0 0 0 0 3 0 0 3 0 0 0 0 0 3 3" +
        "\n\t3 3 0 0 0 0 0 3 0 0 3 0 0 0 0 0 3 0 0 3" +
        "\n\t3 0 3 0 0 3 0 0 3 0 0 3 0 0 3 0 0 3 0 3" +
        "\n\t3 0 0 0 0 0 3 0 0 3 0 0 0 0 0 3 0 0 3 3" +
        "\n\t3 0 0 0 3 0 0 3 0 0 0 0 0 3 0 0 3 0 0s 3" +
        "\n\t3 0 3 0 0 3 0 0 3 0 0 3 0 0 3 0 0 0 0 3" +
        "\n\t3 2 0 3 0 0 3 0 0 0 0 0 3 0 0 3 0 0 3 3" +
        "\n\t3 3 0 0 3 0 0 0 0 0 3 0 0 3 0 0 0 0 0 3" +
        "\n\t3 0 3 0 0 3 0 0 3 0 0 3 0 0 0 0 0 3 0 3" +
        "\n\t3 0 0 3 0 0 0 0 0 3 0 0 3 0 0 3 0 0 3 3" +
        "\n\t3 3 0 0 0 0 0 3 0 0 3 0 0 0 0 0 3 0 0 3" +
        "\n\t3 0 3 0 0 0 0 0 3 0 0 0 0 0 3 0 0 3 0 3" +
        "\n\t3 0 0 0 0 0 3 0 0 3 0 0 3 0 0 3 0 0 3 3" +
        "\n\t3 0 0 0 3 0 0 3 0 0 0 0 0 3 0 0 3 0 0 3" +
        "\n\t3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3" +
        "\n" +
        "\n3.\t3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3" +
        "\n\t3 1 3 0 3 0 3 0 3 0 0 0 0 0 0 3 0 0 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 3 3 3 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 0 0 0 0 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 0 3 3 3 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 0 0 0 0 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 3 3 3 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 0 0 0 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 0 0 3 0 3 0 3 3 3 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 0 0 0 0 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 3 3 3 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 0 0 0 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 0 3 3 3 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 0 0 0 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 3 3 3 0 3 0 3 0 3 0 3" +
        "\n\t3 0 0 0 3 0 3 0 0 0 0 0 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 3 3 3 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 3 0 3 0 0 0 0 3 0 3 0 3 0 3" +
        "\n\t3 0 3 0 3 0 0 0 0 0 3 3 0 0 0 0 0 3 2 3" +
        "\n\t3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3";

    /**
     * Reads the user input
     */
    public static void input() {
        String input;
        Grid grid = new Grid();
        while (true) {
            System.out.println("\n- - - - - - - - - - - - - - - - - - - -\n" +
                formatGrid(grid.getGrid()) + "- - - - - - - - - - - - - - - - - - - -\n");
            System.out.print("Enter your command: ");
            input = scan.nextLine().trim().toUpperCase();
            switch (input) {
                case "HELP":
                    System.out.println(MESSAGE);
                    longDelay();
                    break;
                case "PRESETS":
                    // print relevant information
                    System.out.println(PRESETS);
                    System.out.print("\nEnter the preset number: ");
                    // scan for the next int and scan nextLine to consume new line
                    grid.setPreset(scan.nextInt());
                    scan.nextLine();
                    // set solved to false so we can run more algorithms
                    solved = false;
                    break;
                case "ALGORITHMS":
                    System.out.print(ALGORITHMS);
                    int algorithm = Integer.parseInt(scan.nextLine().trim());
                    switch (algorithm) {
                        // BFS
                        case 1:
                            // if the grid is fresh, run bfs
                            if (!solved) {
                                if (grid.performBFS(null)) {
                                    System.out.println("Path to target node exists!");
                                } else {
                                    System.out.println("Path to target node doesn't exist!");
                                }
                                solved = true;
                            } else {
                                System.out.println("\nCreate a new grid first!");
                            }
                            longDelay();
                            break;
                        // DFS
                        case 2:
                            // if the grid is fresh, run dfs
                            if (!solved) {
                                if (grid.performDFS()) {
                                    System.out.println("Path to target node exists!");
                                } else {
                                    System.out.println("Path to target node doesn't exist!");
                                }
                                solved = true;
                            } else {
                                System.out.println("\nCreate a new grid first!");
                            }
                            longDelay();
                            break;
                        case 3:
                            // if the grid is fresh, run dijkstra
                            if (!solved) {
                                if (grid.performDijkstra()) {
                                    System.out.println("Path to target node exists!");
                                } else {
                                    System.out.println("Path to target node doesn't exist!");
                                }
                                solved = true;
                            } else {
                                System.out.println("\nCreate a new grid first!");
                            }
                            longDelay();
                            break;
                        case 4:
                            // if the grid is fresh, run A*
                            if (!solved) {
                                if (grid.performAStar()) {
                                    System.out.println("Path to target node exists!");
                                } else {
                                    System.out.println("Path to target node doesn't exist!");
                                }
                                solved = true;
                            } else {
                                System.out.println("\nCreate a new grid first!");
                            }
                            longDelay();
                            break;
                        case 5:
                            break;
                        default:
                            System.out.println("Invalid algorithm number.");
                            longDelay();
                    }
                    break;
                case "EXIT":
                    System.exit(0);
                default:
                    System.out.println("\nCommand not recognized.");
                    shortDelay();
            }
        }
    }

    public static void main(String[] args) {
        scan = new Scanner(System.in);
        solved = false;
        // introductory message
        System.out.println(MESSAGE);
        // read commands
        try {
            input();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
