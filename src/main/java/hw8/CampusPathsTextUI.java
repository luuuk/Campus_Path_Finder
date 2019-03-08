package hw8;

// TODO: add comments to this class

import hw5.Edge;
import hw5.Node;
import hw7.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

public class CampusPathsTextUI {

    public static void main(String[] args) {
        // Can be run using ./gradlew runCampusPaths
        PrintWriter output = new PrintWriter(System.out);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            CampusModel model = new CampusModel();
            String inputLine;
            printMenu(output);
            output.println("Enter an option ('m' to see the menu): ");
            while ((inputLine = input.readLine()) != null) {
                if (inputLine.equals("q")) {
                    return;
                }
                if (inputLine.equals("r")) {
                    //prompts the user for the abbreviated names of two buildings and prints directions for
                    // the shortest route between them
                    output.println("Abbreviated name of starting building: ");
                    String origin = input.readLine();
                    output.println("Abbreviated name of ending building: ");
                    String dest = input.readLine();
                    boolean originExists = false;
                    boolean destExists = false;
                    for (CampusBuilding b : model.getBuildings()) {
                        if (b.getShortName().equals(origin)) {
                            originExists = true;                //TODO redundant with building finding function in campusmodel
                        }
                        if (b.getShortName().equals(dest)) {
                            destExists = true;
                        }
                    }
                    if (!originExists) {
                        output.println("Unknown building: " + origin);
                    }
                    if (!destExists) {
                        output.println("Unknown building: " + dest);
                    }
                    if (originExists && destExists) {
                        printPath(model, output, origin, dest);
                    }
                }
                if (inputLine.equals("m")) {
                    //print the menu
                    printMenu(output);
                }
                if (inputLine.equals("b")) {
                    //print the list of buildings
                    printBuildings(output, model.getBuildings());
                }
                if (inputLine.startsWith("#")) {
                    //echo comments
                    output.println(inputLine);
                } else {
                    output.println("Unknown option");
                }
                output.println("Enter an option ('m' to see the menu): ");
            }
        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
        }
    }

    public static void printMenu(PrintWriter p) {
        p.println("Menu:");
        p.println("\tr to find a route");
        p.println("\tb to see a list of all buildings");
        p.println("\tq to quit");
        p.println();
    }

    public static void printBuildings(PrintWriter p, List<CampusBuilding> buildings) {
        p.println("Buildings:");
        for (CampusBuilding b : buildings) {
            p.println("\t" + b.toString());
        }
    }

    public static void printPath(CampusModel model, PrintWriter output, String origin, String dest) {
        output.println("Path from " + origin + " to " + dest + ":");
        Path<Coordinate, Double> p = model.shortestPath(origin, dest);
        List<Node<Coordinate, Double>> pathList = p.getPath();
        for (int i = 0; i < pathList.size() - 1; i++) {
            Node<Coordinate, Double> src = pathList.get(i);
            Node<Coordinate, Double> target = pathList.get(i + 1);
            Edge<Coordinate, Double> edge = src.hasEdgeTo(target.getLabel());
            output.println("\t Walk " + (int) Math.round(edge.getLabel()) + " feet " +
                    getDirection(src.getLabel(), target.getLabel()) + " to " + target.getLabel().toString());
        }
    }

    public static String getDirection(Coordinate src, Coordinate target) {
        Double xDif = target.getX() - src.getX();
        Double yDif = -(target.getY() - src.getY());
        double angle = Math.atan2(yDif, xDif);
        if (-Math.PI / 8 <= angle && Math.PI / 8 >= angle) {
            return "E";
        } else if (3 * Math.PI / 8 > angle && Math.PI / 8 < angle) {
            return "NE";
        } else if (3 * Math.PI / 8 <= angle && 5 * Math.PI / 8 >= angle) {
            return "N";
        } else if (5 * Math.PI / 8 < angle && 7 * Math.PI / 8 > angle) {
            return "NW";
        } else if (-5 * Math.PI / 8 > angle && -7 * Math.PI / 8 < angle) {
            return "SW";
        } else if (-5 * Math.PI / 8 >= angle && -3 * Math.PI / 8 <= angle) {
            return "S";
        } else if (-3 * Math.PI / 8 > angle && -1 * Math.PI / 8 < angle) {
            return "SE";
        } else if (7 * Math.PI / 8 <= angle || -7 * Math.PI / 8 >= angle) { //TODO
            return "W";
        } else {
            return null;
        }
    }
}
