package hw5;

/**
 * <b>Edge</b> is a class that represents a connection between two Nodes in a distinct labeled multigraph
 * <p>
 * Specification Fields:
 *
 * @specified label : String of data stored in this
 * @specified to : the Node that this leads to
 * @specified from : the node that this comes from
 * <p>
 * Abstraction Fuction: Given two Nodes, an Edge is the link from one node to the other, represented by
 *                      a label, the Node the Edge originates from (from) and the Node the Edge leads to (to)
 *
 * Rep Inv: label != null
 *          to != null
 *          from != null
 */
public class Edge {
    private Node to;
    private Node from;
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

    }

    /**
     * Checks that the rep inv holds
     *
     */
    private void checkRep(){
        assert (this.label != null) : "label == null";
        assert (this.from != null) : "from == null";
        assert (this.to != null) : "to == null";
    }
}
