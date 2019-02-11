package hw5;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * <b>Graph</b> is a class that represents a direct labeled multigraph of Nodes and Edges
 */
public class Graph {
    /*
    Abstract Function: Each node in a given Graph, g, is stored in the Set<Node> nodes

    Rep Inv: nodes != null
    Each Node in nodes has a unique label
    Every Edge for each Node in nodes points to another valid Node in nodes
    */

    //Set of all Nodes in the Graph
    private Set<Node> nodes;

    /**
     * Creates a new Graph with no nodes
     */
    public Graph() {
        nodes = new HashSet<>();
    }

    /**
     * Adds a new Node to Nodes
     *
     * @param n the Node to be added
     * @spec.requires n != null
     * @spec.modifies this
     * @spec.effects adds n to nodes
     * @return true if n was successfully added to nodes, false if n is a duplicate
     */
    public boolean addNode(Node n) {
        return false; //OUT
    }

    /**
     * Removes a Node from the Graph
     *
     * @param label The label of the Node to be removed
     * @spec.modifies this
     * @spec.effects removes Node with given label from nodes
     * @return true if Node with given label was removed, false if not in nodes
     */
    public boolean removeNode(String label) {
        return false; //OUT
    }

    /**
     * Gets a given Node from the Graph corresponding to passed label
     * @param label  the label of the Node to be found
     * @return the desired Node with label "label", null if Node is not in Graph
     */
    public Node getNode(String label){
        return null; //OUT
    }

    /**
     * Removes all nodes from the Graph
     *
     * @spec.modifies this
     * @spec.effects removes all Node objects in nodes from the set
     */
    public void clearNodes() {

    }

    /**
     * Tests if Graph is empty
     *
     * @return true if Graph is empty, false if not
     */
    public boolean isEmpty(){
        return false; //OUT
    }

    /**
     * Returns a path from Node n1 to Node n2, symbolised by a String[] of Edge labels
     *
     * @param n1 Label of Node from which path originates
     * @param n2 Label of Node where path terminates (target Node)
     * @throws IllegalArgumentException
     *          if n1 or n2 is not found in this
     * @return a String[] where each entry is the label of an Edge that leads to another Edge, eventually ending
     *          at n2
     */
    public String[] getPath(String n1, String n2) {
        return null; //OUT
    }

    /**
     * Gets a set of Edges for any given Node in the Graph
     *
     * @param nodeLabel the label of the Node queried
     *
     * @throws IllegalArgumentException
     *           if there is no Node in Graph with Label "nodeLabel"
     * @return Set of all the given Edges for the Node queried
     */

    public Set<Edge> getEdges(String nodeLabel){
        return null; //OUT
    }

    /**
     * Gets an Iterator over the set of nodes
     * @return an Iterator on nodes
     */
    public Iterator<Node> getIterator(){
        return null; //OUT
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