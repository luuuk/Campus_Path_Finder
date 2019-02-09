package hw5;

import java.util.List;
import java.util.Set;

/**
 * <b>Node</b> is a class that represents one Node of a direct labeled multigraph
 * <p>
 * Specification Fields:
 *
 * @specified label : String of data stored in this
 * @specified List<Edge> edges : List of all the Edges connected to this
 * <p>
 * Abstraction Fuction: For a given Node n, the data of n is stored in label
 *                      and n's Edges are stored as in Set<Edge>
 *
 * Rep Inv: label != null
 *     */
public class Node {
    private String label;
    private Set<Edge> edges;

    /**
     * Creates a new Node with no Edges
     *
     * @param lab the label of the new Node
     */
    public Node(String lab){

    }

    /**
     * Adds an Edge to the Node
     *
     * @param to Node Edge will link to
     * @modifies this
     * @effects adds e1 to edges
     * @returns true if the Edge was added successfully, false if not (Edge is duplicate)
     */
    public boolean addEdge(Node to, String label) {

    }

    /**
     * Removes an Edge specified by
     *
     * @param edgeLabel label of Edge to be removed
     * @returns true is Edge was removed successfully
     */
    public boolean removeEdge(String edgeLabel) {

    }

    /*
    Might not need this method, but keepong it for now so that I dont have to rewrite it if I do
    *//**
     * Returns a list of all the edges of this
     *
     * @returns a List<Edge> of all the Edges linked to this
     *//*
    public List<Edge> getEdges() {

    }*/

    /**
     * Removes all Edges for given node
     *
     * @modifies this
     * @effects removes all entries from edges
     * @returns true if all Edges were cleared, false otherwise
     */
    public boolean clearEdges() {

    }

    /**
     * Gets label for given Node
     *
     * @returns the String label for this
     */
    public String getLabel(){
        //return label;
    }

    /**
     * Checks if this has an Edge with the given label
     *
     * @param edgeLabel the label of the Edge to search for
     * @returns true if this has an Edge with the given label, false if not
     */
    public boolean hasEdge(String edgeLabel){

    }

    /**
     * Checks that the rep inv holds
     */
    private void checkRep(){
        assert (this.label != null) : "label != null";
    }
}
