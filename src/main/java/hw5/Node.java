package hw5;

import java.util.HashSet;
import java.util.Set;

/**
 * <b>Node</b> is a class that represents one Node of a direct labeled multigraph
 */
public class Node {
    /* Abstraction Fuction: For a given Node n, the data of n is stored in label
    *                       and n's Edges are stored as in Set<Edge>
    *
    * Rep Inv: label != null
    */

    // String label of the Node
    private String label;

    // Set of the Edges of the Node
    private Set<Edge> edges;

    /**
     * Creates a new Node with no Edges
     *
     * @param lab the label of the new Node
     */
    public Node(String lab){
        label = lab;
        edges = new HashSet<>();
        checkRep();
    }

    /**
     * Adds an Edge to the Node with given label and destination
     *
     * @param to Node Edge will link to
     * @param label Label of new Edge
     * @spec.modifies this
     * @spec.effects adds new Edge to edges
     * @return true if the Edge was added successfully, false if not (Edge is duplicate)
     */
    public boolean addEdge(Node to, String label) {
        return this.edges.add(new Edge(this, to, label));
    }

    /**
     * Removes an Edge specified by the given label from edges
     *
     * @param edgeLabel label of Edge to be removed
     * @return true if Edge was removed successfully, false if not in edges
     */
    public boolean removeEdge(String edgeLabel) {
        for(Edge e : edges) {
            if (e.getLabel().equals(edgeLabel)){
                return edges.remove(e);
            }
        }
        checkRep();
        return false;
    }

    /**
     * Returns a Set of all the edges of this
     *
     * @return a Set of all the Edges linked to this
     */
    public Set<Edge> getEdges() {
        // Copy edges to preserve immutability, takes O(E) time
        Set<Edge> edgesCopy = new HashSet<>();
        for(Edge e : edges){
            edgesCopy.add(e);
        }
        return edgesCopy;
    }

    /**
     * Removes all Edges pointing to a given node
     *
     * @param label label of the target Node
     * @spec.modifies this
     * @spec.effects removes all entries in edges pointing to Node with given label
     * @return true if all Edges were cleared, false otherwise
     */
    public boolean clearEdgesToNode(String label) {
        for (Edge e : edges) {
            if(e.getDest().getLabel().equals(label)){
                edges.remove(e);
            }
        }
        checkRep();
        return isTouching(label);
    }

    /**
     * Tests if this is connected to another Node with given label
     *
     * @param label label of Node to be checked for connection
     * @spec.requires label != null
     * @return true if Node is connected to the Node with given bale, false otherwise
     */
    public boolean isTouching(String label){
        for(Edge e : edges){
            if(e.getDest().getLabel().equals(label)){
                return true;
            }
        }
        return false;
    }

    /**
     * Gets label for given Node
     *
     * @return the String label for this
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Checks if this has an Edge with the given label
     *
     * @param edgeLabel the label of the Edge to search for
     * @return a reference to the Edge with the given label, null if not
     */
    public Edge hasEdge(String edgeLabel) {
        for (Edge e : edges) {
            if(e.getLabel().equals(edgeLabel)){
                return e;
                //Is this a security issue? Am I exposing my rep?
            }
        }
        return null;
    }

    /**
     * Checks that the rep inv holds
     */
    private void checkRep() {
        assert label != null : "label == null";
        assert edges != null : "edges == null";
    }

    /**
     * Checks if two Nodes are equal, determined by the same label and both objects being of type Node
     *
     * @param o the other Object (likely a Node) to be compared
     * @return true if o is equal to this in label and Object type
     */
    @Override
    public boolean equals (Object o) {
         return o instanceof Node && ((Node) o).getLabel().equals(this.getLabel());

    }

    /**
     * Returns a HashCode for this
     * @return a unique int that all Nodes equal to this return
     */
    @Override
    public int hashCode() {
        // Because all nodes have unique labels, label is sufficient for unique hashing
        return label.hashCode();
    }
}