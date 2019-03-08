package hw5;

import hw7.Path;
import hw8.CampusPath;

import java.util.*;

/**
 * <b>Graph</b> is a class that represents a direct labeled multigraph of Nodes and Edges
 *
 * @param <NL> the type of labels of Nodes
 * @param <EL> the type of labels of Edges
 */
public class Graph<NL, EL extends Comparable<EL>> {
    /*
    Abstract Function: Each node in a given Graph, g, is stored in the Set<Node> nodes

    Rep Inv: nodes != null
    Each Node in nodes has a unique label
    Every Edge for each Node in nodes points to another valid Node in nodes
    */

    //Map of all Nodes in the Graph
    private Map<NL, Node<NL, EL>> nodes;

    //debug flag for expensive testing
    private static final boolean DEBUG_FLAG = false;

    /**
     * Creates a new Graph with no nodes
     *
     * @spec.effects sets nodes = to a new empty HashMap
     */
    public Graph() {
        nodes = new HashMap<>();
        checkRep();
    }

    /**
     * Returns a list of the labels of all Nodes in this
     *
     * @return a Set of Strings where each String is the label of a Node in this
     */
    public Set<NL> getNodeSet() {
        Set<NL> copyNodeNames = new HashSet<>();
        for (NL nodeLabel : nodes.keySet()) {
            copyNodeNames.add(nodeLabel);
        }
        return copyNodeNames;
    }

    /**
     * Adds a new Node to nodes
     *
     * @param n the Node to be added
     * @return true if n was successfully added to nodes, false if n is a duplicate Node
     * @spec.requires n != null
     * @spec.modifies this
     * @spec.effects adds n to nodes
     */
    public boolean addNode(Node<NL, EL> n) {
        if (!nodes.containsKey(n.getLabel())) {
            nodes.put(n.getLabel(), n);
            checkRep();
            return true;
        }
        return false;
    }

    /**
     * Removes a Node from the Graph and all Edges that pointed to it
     *
     * @param label The label of the Node to be removed
     * @return true if Node with given label was removed, false if not in nodes
     * @spec.modifies this
     * @spec.effects removes Node with given label and all Edges pointing to it from Nodes
     */
    public boolean removeNode(NL label) {
        if (nodes.containsKey(label)) {
            nodes.remove(label);
            for (NL key : nodes.keySet()) {
                if (nodes.get(key).isTouching(label)) {
                    nodes.get(key).clearEdgesToNode(label);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Gets a given Node from the Graph corresponding to passed label
     *
     * @param label the label of the Node to be found
     * @return the desired Node with label "label", null if Node is not in Graph
     * @spec.requires label != null
     */
    public Node<NL, EL> getNode(NL label) {
        return nodes.get(label);
    }

    /**
     * Removes all nodes from the Graph
     *
     * @spec.modifies this
     * @spec.effects removes all Node objects in nodes from the set
     */
    public void clearNodes() {
        nodes.clear();
    }

    /**
     * Tests if Graph is empty
     *
     * @return true if Graph is empty, false if not
     */
    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    /**
     * Finds the shortest path between 2 Nodes in the Graph
     *
     * @param origin the label of the Node the path originates from
     * @param dest   the label of the Node the path ends at
     * @return a list of Node labels representing the shortest path between the two Nodes
     */
    public Path<NL, EL> shortestPath(NL origin, NL dest) {
        Queue<Path<NL, EL>> active = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Path<NL, EL> o1, Path<NL, EL> o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        Set<Node<NL, EL>> finished = new HashSet<>();
        Node<NL, EL> start = this.getNode(origin);
        active.add(new Path<>(start));
        while (!active.isEmpty()) {
            Path<NL, EL> minPath = active.remove();
            Node<NL, EL> minDest = minPath.getDest();
            if (minDest.getLabel().equals(dest)) {
                return minPath;
            }
            if (finished.contains(minDest)) {
                continue;
            }
            finished.add(minDest);
            for (Edge<NL, EL> e : minDest.getEdges()) {
                if (!finished.contains(e.getDest())) {
                    Path<NL, EL> newPath = minPath.addNewNode(e.getDest(), (Double) e.getLabel());
                    active.add(newPath);
                }
            }
        }
        return null;
    }

    /**
     * Gets a set of Edges for any given Node in the Graph
     *
     * @param nodeLabel the label of the Node queried
     * @return Set of all the given Edges for the Node queried
     * @throws IllegalArgumentException if there is no Node in Graph with Label "nodeLabel"
     */

    public Set<Edge<NL, EL>> getEdges(NL nodeLabel) {
        if (!nodes.containsKey(nodeLabel)) {
            throw new IllegalArgumentException("Node with given label not in Graph");
        }
        return Collections.unmodifiableSet(nodes.get(nodeLabel).getEdges());
    }

    /**
     * Gets an Iterator over the set of nodes
     *
     * @return an Iterator on nodes
     */
    public Iterator<Node<NL, EL>> getIterator() {
        return nodes.values().iterator();
    }

    /**
     * Checks that the rep inv holds
     * <p>
     * Rep Inv: nodes != null
     * Each Node in nodes (Key in keySet) has a unique label
     * Every Edge for each Node in nodes points to another valid Node in nodes
     */
    private void checkRep() {
        assert (this.nodes != null) : "Nodes == null";
        if (DEBUG_FLAG) {
            for (NL key : nodes.keySet()) {
                assert key.equals(nodes.get(key).getLabel()) : "Key is not label of Node";
                for (Edge<NL, EL> e : nodes.get(key).getEdges()) {
                    assert nodes.containsValue(e.getDest()) : "Edge points to Node not in Graph";
                }
            }
        }
    }
}