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
            "\nCOMMANDS\n----------" +
            "\n1. HELP";
    // todo commands for interactions with the grid
    private final static String ALGORITHMS = "\nSELECT THE NUMBER YOU WANT TO RUN\n----------" +
            "\n1. BFS (Breadth First Search)" +
            "\n2. DFS (Depth First Search)" +
            "\n3. Dijkstra" +
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
                    "\n\t0 0 0 3 3 3 3 3 0 0 3 3 3 0 0 0 0 0 0 3";

    /**
     * Reads the user input
     */
    public static void input() {
        String input;
        Grid grid = new Grid();
        while (true) {
            System.out.println("\n" + formatGrid(grid.getGrid()));
            System.out.print("Enter your command: ");
            input = scan.nextLine().toUpperCase();
            switch (input) {
                case "HELP":
                    System.out.println(MESSAGE);
                    longDelay();
                    break;
                case "CLEAR PATH":
                    // clear visited nodes and set solved to false so we can run more algorithms
                    grid.clearPath();
                    solved = false;
                    break;
                case "PRESET":
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
                    int algorithm = scan.nextInt();
                    scan.nextLine();
                    switch (algorithm) {
                        // BFS
                        case 1:
                            // if the grid is fresh, run bfs
                            if (!solved) {
                                if (grid.performBFS()) {
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
                            }
                            longDelay();
                            break;
                        default:
                            System.out.println("Invalid algorithm number.");
                            longDelay();
                    }
                    break;
                /*case "BFS":
                    // if the grid is fresh, run bfs
                    if (!solved) {
                        if (grid.performBFS()) {
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
                case "DFS":
                    // if the grid is fresh, run dfs
                    if (!solved) {
                        if (grid.performDFS()) {
                            System.out.println("Path to target node exists!");
                        } else {
                            System.out.println("Path to target node doesn't exist!");
                        }
                        solved = true;
                    } else {
                        System.out.println("Create a new grid first!");
                    }
                    longDelay();
                    break;*/
                case "CHANGE TARGET":
                    System.out.print("\nState x-coordinate (up-down) (1-20): ");
                    int x = scan.nextInt();
                    System.out.print("\nState y-coordinate (left-right) (1-20): ");
                    int y = scan.nextInt();
                    grid.changeTargetNode(x - 1, y - 1);
                    break;
                case "EXIT":
                    System.exit(0);
                case "\n":
                    break;
                default:
                    System.out.println("Command not recognized.");
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
