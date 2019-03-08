package hw8;

import hw5.*;
import hw7.Path;

import java.io.IOException;
import java.util.*;

/**
 * Builds and stores a model of paths and buildings on the UW campus.
 */
public class CampusModel {
    // Not an ADT because CampusModel

    /** list of all paths on campus **/
    private List<CampusPath> paths;
    /** list of all buildings on campus **/
    private List<CampusBuilding> buildings;
    /** Graph of buildings and paths on campus **/
    private Graph<Coordinate, Double> g;

    /**
     * Constructs a new CampusModel
     * @spec.modifies this
     * @spec.effects populates internal models with data from tsv files
     * @throws IOException
     */
    public CampusModel() throws IOException {
        paths = CampusDataParser.parsePathData("src/main/resources/hw8/campus_paths.tsv");
        buildings = CampusDataParser.parseBuildingData("src/main/resources/hw8/campus_buildings.tsv");
        g = new Graph<Coordinate, Double>();
        //Add each building to the graph
        for (CampusBuilding b : buildings) {
            g.addNode(new Node<>(b.getLocation()));
        }
        //Add coordinate locations at end of each path to graph, then add a connection between them
        for (CampusPath p : paths) {
            g.addNode(new Node<>(p.getDestination()));
            g.addNode(new Node<>(p.getOrigin()));       //addNode has built in check to see if Node already is in graph
            g.getNode(p.getOrigin()).addEdge(g.getNode(p.getDestination()), p.getDistance());
        }
    }

    /**
     * Finds the shortest path between two buildings
     * @param firstName name of the origin building
     * @param secondName name of the destination building
     * @return a Path representing the shortest path between the two buildings
     */
    public Path<Coordinate, Double> shortestPath(String firstName, String secondName) {
        Coordinate origin = null;
        Coordinate dest = null;
        for (CampusBuilding b : buildings) {
            if (b.getShortName().equals(firstName)) {
                origin = b.getLocation();
            }
            if (b.getShortName().equals(secondName)) {
                dest = b.getLocation();
            }
        }
    return g.shortestPath(origin, dest);
    }

    /**
     * Gets a list of all the buildings in this model
     * @return List of CampusBuildings in this
     */
    public List<CampusBuilding> getBuildings(){
        return buildings;
    }
}
