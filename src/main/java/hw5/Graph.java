package hw5;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * <b>Graph</b> is a class that represents a direct labeled multigraph of Nodes and Edges
 * <p>
 * Specification Fields:
 *
 * @specified nodes : Set<Node> //set of all unique Nodes contained in the Graph
 * <p>
 * Abstract Function: Each node in a given Graph, g, is stored in the Set<Node> nodes
 *
 * Rep Inv: nodes != null
 * Each Node in nodes has a unique label
 * Every Edge for each Node in nodes points to another valid Node in nodes
 */
public class Graph {

    private Set<Node> nodes;

    /**
     * Creates a new Graph with no nodes
     */
    public Graph() {
        nodes = new HashSet<>();
    }

    /**
     * Creates a new Graph with nodes = copy of passed Set<Node>
     *
     * @requires s != null
     */
    /*
    TBD IF I NEED THIS. LEAVING IT FOR NOW AND WILL REWRITE LATER IF NECESSARY

    public Graph(Set<Node> s) {
        nodes = s;
    }*/

    /**
     * Adds a new Node to Nodes
     *
     * @param n the Node to be added
     * @requires n != null
     * @modifies this
     * @effects adds n to nodes
     * @returns true if n was successfully added to nodes, false if n is a duplicate
     */
    public boolean addNode(Node n) {

    }

    /**
     * Adds a new Edge between two Node objects in nodes
     *
     * @param n1 The Node the Edge will originate from
     * @param n2 The Node the Edge will lead to
     * @param label The label of the Edge
     *              //REQUIRES SHOULD BE DEALT WITH BY RETURN BOOLEAN
     * @modifies this
     * @effects adds new Edge from n1 to n2 with label "label"
     * @returns true if the Edge was successfully created, false if n1 or n2 are not in nodes
     */
/*    NOT SURE IF THIS WILL BE NECESSARY LATER, LEAVING TO AVOID HAVE TO REWRITE LATER.
    public boolean addEdge(Node n1, Node n2, String label) {

    }*/

    /**
     * Removes a Node from the Graph
     *
     * @param label The label of the Node to be removed
     * @modifies this
     * @effects removes Node with given label from nodes
     * @returns true if Node with given label was removed, false if not in nodes
     */
    public boolean removeNode(String label) {

    }

    /**
     * Gets a given Node from the Graph corresponding to passed label
     * @param label  the label of the Node to be found
     * @return the desired Node with label "label", null if Node is not in Graph
     */
    public Node getNode(String label){

    }

    /**
     * Removes all nodes from the Graph
     *
     * @modifies this
     * @effects removes all Node objects in nodes from the set
     */
    public void clearNodes() {

    }

    /**
     * Tests if Graph is empty
     *
     * @returns true if Graph is empty, false if not
     */
    public boolean isEmpty(){

    }

    /**
     * Returns a path from Node n1 to Node n2, symbolised by a String[] of Edge labels
     *
     * @param n1 Node from which path originates
     * @param n2 Node where path terminates (target Node)
     * @throws IllegalArgumentException
     *          if n1 or n2 is not found in this
     * @returns a String[] where each entry is the label of an Edge that leads to another Edge, eventually ending
     *          at n2
     */
    public String[] getPath(Node n1, Node n2) {

    }

    /**
     * Gets a set of Edges for any given Node in the Graph
     *
     * @param nodeLabel the label of the Node queried
     *
     * @throws IllegalArgumentException
     *           if there is no Node in Graph with Label "nodeLabel"
     * @return Set<Edge> of all the given Edges for the Node queried
     */
    public Set<Edge> getEdges(String nodeLabel){

    }

    /**
     * Gets an Iterator over the set of nodes
     * @returns an Iterator on nodes
     */
    public Iterator<Node> getIterator(){

    }

    /**
     * Checks that the rep inv holds
     *
     *  * Rep Inv: nodes != null
     *  * Each Node in nodes has a unique label
     *  * Every Edge for each Node in nodes points to another valid Node in nodes
     */
    private void checkRep(){
        assert (this.nodes != null) : "Nodes == null";
        //More to come later
    }
}