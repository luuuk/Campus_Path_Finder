package hw5;

/**
 * <b>Edge</b> is a class that represents a connection between two Nodes in a distinct labeled multigraph
 */
public class Edge {
    /*
    Abstraction Fuction: Given two Nodes, an Edge is the link from one node to the other, represented by
                   a label, the Node the Edge originates from (from) and the Node the Edge leads to (to)

    Rep Inv: label != null
             to != null
             from != null
    */

    // The Node that this leads to
    private Node to;

    // The node that this comes from
    private Node from;

    // String of data stored in this
    private String label;

    /**
     * Creates new Edge linking Node n1 and Node n2 with given label
     *
     * @param n1 Node Edge comes from
     * @param n2 Node Edge links to
     * @param lab label of Edge
     */
    public Edge(Node n1, Node n2, String lab){
        to = n2;
        from = n1;
        label = lab;
    }

    /**
     * Returns the label of this Edge
     *
     * @return label of this
     */
    public String getLabel(){
        return null; //OUT
    }

    /**
     * Checks that the rep inv holds
     */
    private void checkRep(){
        assert (this.label != null) : "label == null";
        assert (this.from != null) : "from == null";
        assert (this.to != null) : "to == null";
    }

    /**
     * Gets a copy of the Node this originates from
     * @return a copy of from
     */
    public Node getOrigin() {
        Node copy = from;
        return copy;
    }

    /**
     * Gets a copy of the Node this leads to
     * @return a copy of to
     */
    public Node getDest() {
        Node copy = to;
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
        if(o instanceof Edge && ((Edge) o).getLabel().equals(this.label)
                && ((Edge) o).getDest().equals(this.to) && ((Edge) o).getOrigin().equals(this.from)) {
            return true;
        }
        return false;
    }
}
