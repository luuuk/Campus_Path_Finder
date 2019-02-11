package hw5;

import java.util.List;
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

    }

    /**
     * Adds an Edge to the Node
     *
     * @param to Node Edge will link to
     * @param label Label of new Edge
     * @spec.modifies this
     * @spec.effects adds e1 to edges
     * @return true if the Edge was added successfully, false if not (Edge is duplicate)
     */
    public boolean addEdge(Node to, String label) {
        return false; //OUT
    }

    /**
     * Removes an Edge specified by
     *
     * @param edgeLabel label of Edge to be removed
     * @return true is Edge was removed successfully
     */
    public boolean removeEdge(String edgeLabel) {
        return false; //OUT
    }

    /**
     * Returns a Set of all the edges of this
     *
     * @return a Set of all the Edges linked to this
     */
    public Set<Edge> getEdges() {
        return null; //OUT
    }

    /**
     * Removes all Edges for given node
     *
     * @spec.modifies this
     * @spec.effects removes all entries from edges
     * @return true if all Edges were cleared, false otherwise
     */
    public boolean clearEdges() {
        return false; //OUT
    }

    /**
     * Gets label for given Node
     *
     * @return the String label for this
     */
    public String getLabel() {
        //return label;
        return null; //OUT
    }

    /**
     * Checks if this has an Edge with the given label
     *
     * @param edgeLabel the label of the Edge to search for
     * @return true if this has an Edge with the given label, false if not
     */
    public boolean hasEdge(String edgeLabel) {
        return false; //OUT
    }

    /**
     * Checks that the rep inv holds
     */
    private void checkRep() {
        assert (this.label != null) : "label != null";
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
        return label.hashCode() + edges.hashCode();
    }
}