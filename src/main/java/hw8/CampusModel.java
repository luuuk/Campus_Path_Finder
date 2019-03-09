package hw8;

import hw5.*;
import hw7.Path;

import java.io.IOException;
import java.util.*;

/**
 * Builds and stores a model of paths and buildings on the UW campus.
 */
public class CampusModel {
    //
    // AF: Stores each building on campus as a CampusBuilding in buildings. Stores paths on campus in a Graph, where
    // the Nodes represent the coordinate start and endpoints of the path and the Edges represent the distance
    // between Nodes
    //
    // RI: buildings != null;
    //     g != null
    //     Each CampusBuilding in buildings shares coordinates with at least one CampusPath in g

    /**
     * list of all buildings on campus
     **/
    private List<CampusBuilding> buildings;
    /**
     * Graph of buildings and paths on campus
     **/
    private Graph<Coordinate, Double> g;

    /**
     * list of all paths on campus
     */
    private List<CampusPath> paths;

    /**
     * Debug flag for more expensive testing
     */
    private static final boolean DEBUG_FLAG = true;

    /**
     * Constructs a new CampusModel
     *
     * @throws IOException if data is not found
     * @spec.modifies this
     * @spec.effects populates internal models with data from tsv files
     */
    public CampusModel() throws IOException {
        paths = CampusDataParser.parsePathData("src/main/resources/hw8/campus_paths.tsv");
        buildings = CampusDataParser.parseBuildingData("src/main/resources/hw8/campus_buildings_new.tsv");
        g = new Graph<Coordinate, Double>();

        //Don't have to add buildings because all path endpoints encapsulate all buildings
        //Add coordinate locations at end of each path to graph, then add a connection between them
        for (CampusPath p : paths) {
            g.addNode(new Node<>(p.getDestination()));
            g.addNode(new Node<>(p.getOrigin()));       //addNode has built in check to see if Node already is in graph
            g.getNode(p.getOrigin()).addEdge(g.getNode(p.getDestination()), p.getDistance());
        }
        checkRep();
    }

    /**
     * Finds the shortest path between two buildings
     *
     * @param first  CampusBuilding representing of the origin building
     * @param second CampusBuilding representing the destination building
     * @return a Path representing the shortest path between the two buildings
     */
    public Path<Coordinate, Double> shortestPath(CampusBuilding first, CampusBuilding second) {
        Coordinate origin = first.getLocation();
        Coordinate dest = second.getLocation();
        return g.shortestPath(origin, dest);
    }

    /**
     * Gets a list of all the buildings in this model
     *
     * @return List of CampusBuildings in this
     */
    public List<CampusBuilding> getBuildings() {
        return buildings;
    }

    /**
     * Finds the direction of a given path
     *
     * @param src    the starting point of the path
     * @param target the ending point of the path
     * @return a String representation of the direction of the path
     */
    public String getDirection(Coordinate src, Coordinate target) {
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
        } else if (-5 * Math.PI / 8 <= angle && -3 * Math.PI / 8 >= angle) {
            return "S";
        } else if (-3 * Math.PI / 8 > angle && -1 * Math.PI / 8 < angle) {
            return "SE";
        } else {
            return "W";
        }
    }

    private void checkRep(){
        // RI: buildings != null;
        //     g != null
        //     Each CampusBuilding in buildings shares coordinates with at least one CampusPath in g
        assert buildings != null;
        assert g != null;
        if(DEBUG_FLAG){
            for(CampusBuilding b : buildings){
                assert g.getNodeSet().contains(b.getLocation());
            }
        }

    }
}
