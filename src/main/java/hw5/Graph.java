package hw5;

import java.util.*;

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

    //Map of all Nodes in the Graph
    private Map<String, Node> nodes;

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
    public Set<String> getNodeSet() {
        Set<String> copyNodeNames = new HashSet<>();
        for (String nodeLabel : nodes.keySet()) {
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
    public boolean addNode(Node n) {
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
    public boolean removeNode(String label) {
        if (nodes.containsKey(label)) {
            nodes.remove(label);
            for (String key : nodes.keySet()) {
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
    public Node getNode(String label) {
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
     * Gets a set of Edges for any given Node in the Graph
     *
     * @param nodeLabel the label of the Node queried
     * @return Set of all the given Edges for the Node queried
     * @throws IllegalArgumentException if there is no Node in Graph with Label "nodeLabel"
     */

    public Set<Edge> getEdges(String nodeLabel) {
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
    public Iterator<Node> getIterator() {
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
        for (String key : nodes.keySet()) {
            assert key.equals(nodes.get(key).getLabel()) : "Key is not label of Node";
            for (Edge e : nodes.get(key).getEdges()) {
                assert nodes.containsValue(e.getDest()) : "Edge points to Node not in Graph";
            }
        }
    }
}