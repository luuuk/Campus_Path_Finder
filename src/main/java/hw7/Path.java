package hw7;

import hw5.*;

import java.util.*;

/**
 * <b>Path</b> is a class that represents a path between nodes with a list of Nodes along the path and a weight of the sum of the Edges
 *
 * @param <NL> the type of labels of Nodes
 * @param <EL> the type of labels of Edges
 */
public class Path<NL, EL extends Comparable<EL>> {

    // Abstraction function:
    // Nodes in Path are stored as a List of Nodes in this.path.
    // The order of the Nodes corresponds to their order in the list.
    // The weight of the path is stored in this.weight

    // Representation invariant:
    // path != null
    // all Nodes in path != null
    // each Node in path is directly connected to its following node
    // weight >= 0

    //list of Nodes in the path
    private List<Node<NL, EL>> path;

    //weight of the path
    private Double weight;

    private static final boolean DEBUG_FLAG = false;

    /**
     * Creates a new Path
     *
     * @param node the first Node in the Path
     * @spec.requires node != null
     * @spec.effects creates a new Path that starts at "node"
     */
    public Path(Node<NL, EL> node) {
        path = new ArrayList<>();
        path.add(node);
        weight = 0.0;
        checkRep();
    }

    /**
     * Creates a new Path from
     *
     * @param path   list of Nodes to be included in path
     * @param weight weight of path
     * @spec.effects creates a new Path with this.path equal to param path and this.weight = param weight
     */
    public Path(List<Node<NL, EL>> path, Double weight) {
        this.path = new ArrayList<>();
        for (Node<NL, EL> node : path) {
            this.path.add(node);
        }
        this.weight = weight;
        checkRep();
    }

    /**
     * Gets the weight of this
     *
     * @return the double weight of this
     */
    public Double getWeight() {
        return this.weight;
    }

    /**
     * Gets this represented by a list
     *
     * @return this as a list
     */
    public List<Node<NL, EL>> getPath() {
        List<Node<NL, EL>> copy = new ArrayList<>();
        for (Node<NL, EL> node : path) {
            copy.add(node);
        }
        return copy;
    }

    /**
     * Gets the last Node of this
     *
     * @return destination Node of this
     */
    public Node<NL, EL> getDest() {
        if (path.isEmpty()) {
            return null;
        }
        return path.get(path.size() - 1);
    }

    /**
     * Creates a new Path based on current Path by adding a subsequent Node to this
     *
     * @param next       Node to be added to this
     * @param nextWeight Weight of edge to next Node
     * @return a new path crated by adding next to this
     * @spec.requires next is not null and nextWeight is greater than or equal to  0
     */
    public Path<NL, EL> addNewNode(Node<NL, EL> next, Double nextWeight) {
        List<Node<NL, EL>> currentPath = this.getPath();
        currentPath.add(next);
        return new Path<NL, EL>(currentPath, this.weight + nextWeight);
    }

    /**
     * Affirms that representation invariant for Path holds
     */
    private void checkRep() {
        assert path != null : "path is null";
        assert weight >= 0 : "weight < 0";
        if(DEBUG_FLAG) {
            for (int i = 0; i < path.size() - 1; i++) {
                assert path.get(i) != null : "a node in path is null";
                assert path.get(i).isTouching(path.get(i + 1).getLabel()) : "consecutive nodes in path are not connected";
            }
        }
    }
}
