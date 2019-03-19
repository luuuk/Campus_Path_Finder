package hw8;

/**
 * Text UI for finding the shortest path between buildings on the UW campus
 */

import hw5.Edge;
import hw5.Node;
import hw7.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class CampusPathsTextUI {

    /**
     * Main method for Campus paths UI. Provides user text interface that can be used to find paths between buildings
     * on campus.
     *
     * @param args list of arguments passed in commandline. Ignored in this class
     */

    //not an ADT

    public static void main(String[] args) {
        // Can be run using ./gradlew runCampusPaths
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            CampusModel model = new CampusModel();
            String inputLine;
            printMenu();
            System.out.print("Enter an option ('m' to see the menu): ");
            while ((inputLine = input.readLine()) != null) {
                if (inputLine.equals("q")) {
                    return;
                } else if (inputLine.equals("r")) {
                    //prompts the user for the abbreviated names of two buildings and prints directions for
                    // the shortest route between them
                    System.out.print("Abbreviated name of starting building: ");
                    String origin = input.readLine();
                    while (origin.startsWith("#") || origin.isEmpty()) {
                        System.out.println(origin);
                        origin = input.readLine();
                    }
                    System.out.print("Abbreviated name of ending building: ");
                    String dest = input.readLine();
                    while (dest.startsWith("#") || dest.isEmpty()) {
                        System.out.println(dest);
                        dest = input.readLine();
                    }
                    CampusBuilding org = null;
                    CampusBuilding end = null;
                    for (CampusBuilding b : model.getBuildings()) {
                        if (b.getShortName().equals(origin)) {
                            org = b;
                        }
                        if (b.getShortName().equals(dest)) {
                            end = b;
                        }
                    }
                    if (org == null) {
                        System.out.println("Unknown building: " + origin);
                    }
                    if (end == null) {
                        System.out.println("Unknown building: " + dest);
                    }
                    if (end != null && org != null) {
                        printPath(model, org, end);
                    }
                    System.out.println();
                    System.out.print("Enter an option ('m' to see the menu): ");
                } else if (inputLine.equals("m")) {
                    //print the menu
                    printMenu();
                    System.out.print("Enter an option ('m' to see the menu): ");
                } else if (inputLine.equals("b")) {
                    //print the list of buildings
                    printBuildings(model.getBuildings());
                    System.out.print("Enter an option ('m' to see the menu): ");
                } else if (inputLine.startsWith("#") || inputLine.isEmpty()) {
                    //echo comments
                    System.out.println(inputLine);
                } else {
                    System.out.println("Unknown option");
                    System.out.println();
                    System.out.print("Enter an option ('m' to see the menu): ");
                }
            }
        } catch (
                IOException e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
        }

    }

    /**
     * Prints the menu of options for the user
     */
    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("\tr to find a route");
        System.out.println("\tb to see a list of all buildings");
        System.out.println("\tq to quit");
        System.out.println();
    }

    /**
     * Prints all the buildings on campus
     *
     * @param buildings list of buildings on campus
     */
    private static void printBuildings(List<CampusBuilding> buildings) {
        buildings.sort(new Comparator<CampusBuilding>() {
            @Override
            public int compare(CampusBuilding o1, CampusBuilding o2) {
                return o1.getShortName().compareTo(o2.getShortName());
            }
        });
        System.out.println("Buildings:");
        for (CampusBuilding b : buildings) {
            System.out.println("\t" + b.toString());
        }
        System.out.println();
    }

    /**
     * Prints the shortest path between 2 buildings on campus
     *
     * @param model  the model of campus including all buildings and paths
     * @param origin building path will begin at
     * @param dest   building path will end at
     */
    private static void printPath(CampusModel model, CampusBuilding origin, CampusBuilding dest) {
        System.out.println("Path from " + origin.getLongName() + " to " + dest.getLongName() + ":");
        Path<Coordinate, Double> p = model.shortestPath(origin, dest);
        List<Node<Coordinate, Double>> pathList = p.getPath();
        for (int i = 0; i < pathList.size() - 1; i++) {
            Node<Coordinate, Double> src = pathList.get(i);
            Node<Coordinate, Double> target = pathList.get(i + 1);
            Edge<Coordinate, Double> edge = src.hasEdgeTo(target.getLabel());
            System.out.println("\t Walk " + (int) Math.round(edge.getLabel()) + " feet " +
                    model.getDirection(src.getLabel(), target.getLabel()) + " to (" + Math.round(target.getLabel().getX())
                    + ", " + Math.round(target.getLabel().getY()) + ")");
        }
        System.out.println("Total distance: " + (int) Math.round(p.getWeight()) + " feet");
    }
}
