package PathfindingVisualizerFX;

import java.util.Scanner;

public class PathfindingVisualizerPTUI {
    // Scanner for input
    private static Scanner scan;
    // display message
    private final static String MESSAGE =
            "\nPathfinding Visualizer\n----------" +
            "\nNUMBER CODES\n----------" +
            "\nEMPTY NODE =\t\t0\nSTART NODE =\t\t1\nEND NODE =\t\t\t2\nOBSTACLE NODE =\t\t3\nVISITED NODE:\t\t4\nCHOSEN PATH:\t\t5";
    // todo commands for interactions with the grid
    private final static String COMMANDS = "";
    // preset choices
    private final static String PRESETS =
                    "\nCHOOSE YOUR PRESET (NUMBER):" +
                    "\n1.\t1 1 0 0 1 0 1 0 0 1 0 0 1 0 1 0 0 0 0 1" +
                    "\n\t1 1 2 1 0 0 0 0 1 1 0 1 1 0 0 1 1 1 0 1" +
                    "\n\t0 1 0 0 1 0 0 1 1 0 0 0 0 1 0 0 0 1 0 1" +
                    "\n\t0 0 0 1 0 1 0 0 1 0 0 1 1 0 1 0 1 0 0 0" +
                    "\n\t1 1 0 0 0 0 1 0 0 1 0 1 0 0 1 0 1 0 1 1" +
                    "\n\t0 1 0 1 1 0 0 0 1 0 0 1 0 0 0 0 0 0 1 0" +
                    "\n\t0 0 0 0 1 1 1 1 1 0 1 0 1 1 1 1 0 1 1 0" +
                    "\n\t1 1 0 0 1 0 1 0 0 1 0 0 1 0 1 0 0 0 0 1" +
                    "\n\t1 0 0 1 0 0 0 0 1 1 0 1 1 0 0 1 1 1 0 1" +
                    "\n\t0 1 0 0 1 0 0 1 1 0 0 0 0 1 0 0 0 1 0 1" +
                    "\n\t0 0 0 1 0 0 0 0 1 0 0 1 1 0 1 0 1 0 0 0" +
                    "\n\t1 1 0 0 0 1 0 0 1 1 1 1 1 0 1 0 1 0 1 1" +
                    "\n\t1 1 0 0 1 1 1 0 1 1 1 1 1 0 1 1 1 0 1 1" +
                    "\n\t1 1 0 0 1 1 1 0 1 1 1 1 1 0 0 0 0 0 1 1" +
                    "\n\t1 1 0 0 1 0 1 0 0 0 0 0 0 0 1 0 1 0 0 1" +
                    "\n\t1 0 0 1 0 0 0 0 1 1 0 1 1 0 0 1 1 1 0 1" +
                    "\n\t0 1 0 0 1 0 0 1 1 0 0 0 0 0 0 3 0 1 0 1" +
                    "\n\t0 0 0 1 0 1 0 0 1 0 0 1 1 0 1 0 1 0 0 0" +
                    "\n\t1 1 0 0 0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1" +
                    "\n\t0 0 0 1 1 1 1 1 0 0 1 1 1 0 0 0 0 0 0 1";

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
     * Reads the user input
     */
    public static void input() {
        String input;
        Grid grid = new Grid();
        while (true) {
            System.out.println("\n" + grid.toString());
            System.out.print("Enter your command: ");
            input = scan.nextLine().toUpperCase();
            switch (input) {
                case "HELP":
                    break;
                case "PRESET":
                    System.out.println(PRESETS);
                    grid.setPreset(scan.nextInt());
                    break;
                case "EXIT":
                    System.exit(0);
                default:
                    System.out.println("Command not recognized.");
                    delay();
            }
        }
    }

    public static void main(String[] args) {
        scan = new Scanner(System.in);
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
