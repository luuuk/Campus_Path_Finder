package hw5;

/**
 * <b>Edge</b> is a class that represents a connection between two Nodes in a distinct labeled multigraph
 *
 * @param <NL> the type of labels of Nodes
 * @param <EL> the type of labels of Edges
 */
public class Edge<NL, EL extends Comparable<EL>> {
    /*
    Abstraction Fuction: Given two Nodes, an Edge is the link from one node to the other, represented by
                   a label, the Node the Edge originates from (from) and the Node the Edge leads to (to)

    Rep Inv: label != null
             to != null
             from != null
    */

    // The Node that this leads to
    private Node<NL, EL> to;

    // The node that this comes from
    private Node<NL, EL> from;

    // String of data stored in this
    private EL label;

    /**
     * Creates new Edge linking Node n1 and Node n2 with given label
     *
     * @param n1  Node Edge comes from
     * @param n2  Node Edge links to
     * @param lab label of Edge
     */
    public Edge(Node<NL, EL> n1, Node<NL, EL> n2, EL lab) {
        to = n2;
        from = n1;
        label = lab;
        checkRep();
    }

    /**
     * Returns the label of this Edge
     *
     * @return label of this
     */
    public EL getLabel() {
        return label;
    }

    /**
     * Gets a copy of the Node this originates from
     *
     * @return a copy of from
     */
    public Node<NL, EL> getOrigin() {
        Node<NL, EL> copy = from;
        return copy;
    }

    /**
     * Gets a copy of the Node this leads to
     *
     * @return a copy of to
     */
    public Node<NL, EL> getDest() {
        Node<NL, EL> copy = to;
        return copy;
    }

    /**
     * Tests if 2 Edges are equal, determined by the same values for too, from, and label
     * and both being of type Edge
     *
     * @param o other Edge to be checked
     * @return true if o is an Edge equal to this
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Edge)) {
            return false;
        }
        Edge<?, ?> e = (Edge<?, ?>) o;
        return label.equals(e.getLabel());
    }

    /**
     * Returns a unique HashCode for this
     *
     * @return a unique int that all Edges equal to this return
     */
    @Override
    public int hashCode() {
        return label.hashCode() * 3 + from.hashCode() * 5 + to.hashCode() * 7;
    }

    /**
     * Checks that the rep inv holds
     */
    private void checkRep() {
        assert (this.label != null) : "label == null";
        assert (this.from != null) : "from == null";
        assert (this.to != null) : "to == null";
    }
}
