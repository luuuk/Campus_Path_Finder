package hw5;

import java.util.HashSet;
import java.util.Set;

/**
 * <b>Node</b> is a class that represents one Node of a direct labeled multigraph
 *
 * @param <NL> the type of labels of Nodes
 * @param <EL> the type of labels of Edges
 */
public class Node<NL extends Comparable<NL>, EL extends Comparable<EL>> {
    /* Abstraction Fuction: For a given Node n, the data of n is stored in label
     *                       and n's Edges are stored as in Set<Edge>
     *
     * Rep Inv: label != null
     */

    // String label of the Node
    private NL label;

    // Set of the Edges of the Node
    private Set<Edge<NL, EL>> edges;

    /**
     * Creates a new Node with no Edges
     *
     * @param lab the label of the new Node
     */
    public Node(NL lab) {
        label = lab;
        edges = new HashSet<>();
        checkRep();
    }

    /**
     * Adds an Edge to the Node with given label and destination
     *
     * @param to    Node Edge will link to
     * @param label Label of new Edge
     * @return true if the Edge was added successfully, false if not (Edge is duplicate)
     * @spec.modifies this
     * @spec.effects adds new Edge to edges
     */
    public boolean addEdge(Node<NL, EL> to, EL label) {
        return this.edges.add(new Edge<NL, EL>(this, to, label));
    }

    /**
     * Removes an Edge specified by the given label from edges
     *
     * @param destLabel label of destination Node for Edge to be removed
     * @return true if Edge was removed successfully, false if not in edges
     */
    //Changed to remove Edge based on target Node, as Edge labels are now not distinctive
    public boolean removeEdge(NL destLabel) {
        for (Edge<NL, EL> e : edges) {
            if (e.getDest().getLabel().equals(destLabel)) {
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
    public Set<Edge<NL, EL>> getEdges() {
        // Copy edges to prevent representation exposure
        Set<Edge<NL, EL>> edgesCopy = new HashSet<>();
        edgesCopy.addAll(edges);
        return edgesCopy;
    }

    /**
     * Removes all Edges pointing to a given node
     *
     * @param label label of the target Node
     * @return true if all Edges were cleared, false otherwise
     * @spec.modifies this
     * @spec.effects removes all entries in edges pointing to Node with given label
     */
    public boolean clearEdgesToNode(NL label) {
        //Inv: this is touching Node with given label
        while (isTouching(label)) {
            //find Edge touching target Node
            Edge<NL, EL> edgeToRemove = hasEdgeTo(label);

            //remove Edge from edges
            edges.remove(edgeToRemove);
        }
        return isTouching(label);
    }

    /**
     * Tests if this is connected to another Node with given label
     *
     * @param label label of Node to be checked for connection
     * @return true if Node is connected to the Node with given bale, false otherwise
     * @spec.requires label != null
     */
    public boolean isTouching(NL label) {
        for (Edge<NL, EL> e : edges) {
            if (e.getDest().getLabel().equals(label)) {
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
    public NL getLabel() {
        return this.label;
    }

    /**
     * Checks if this has an Edge to a Node with the given label
     *
     * @param nodeLabel the label of the Node to search for
     * @return a reference to the Edge with a destination Node with given label, null Node when label DNE
     */
    public Edge<NL, EL> hasEdgeTo(NL nodeLabel) {
        for (Edge<NL, EL> e : edges) {
            //if the destination of e is the desired node
            if (e.getDest().getLabel().equals(nodeLabel)) {
                //return a reference to the Edge
                return e;
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
    public boolean equals(Object o) {
        if (!(o instanceof Node)) {
            return false;
        }
        return label.equals(((Node<?, ?>) o).getLabel());
    }

    /**
     * Returns a unique HashCode for this
     *
     * @return a unique int that all Nodes equal to this return
     */
    @Override
    public int hashCode() {
        // Because all nodes have unique labels, label is sufficient for unique hashing
        return label.hashCode();
    }
}