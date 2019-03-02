package hw5;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GraphTest {

    private Graph<String, String> g1 = new Graph<String, String>();
    private Node<String, String> n1 = new Node<String, String>("N1");
    private Node<String, String> n2 = new Node<String, String>("N2");
    private Node<String, String> n3 = new Node<String, String>("N3");

    private Graph<String, String> graphWithNodes() {
        Graph<String, String> gWithNodes = new Graph<>();
        gWithNodes.addNode(new Node<String, String>("N4"));
        gWithNodes.addNode(new Node<String, String>("N5"));
        return gWithNodes;
    }


    private Graph<String, String> pathGraph() {
        Graph<String, String> graphWithEdges = new Graph<String, String>();
        graphWithEdges.addNode(new Node<String, String>("N4"));
        graphWithEdges.addNode(new Node<String, String>("N5"));
        graphWithEdges.addNode(new Node<String, String>("N6"));
        graphWithEdges.getNode("N4").addEdge(graphWithEdges.getNode("N5"), "N4 to N5");
        graphWithEdges.getNode("N5").addEdge(graphWithEdges.getNode("N6"), "N5 to N6");
        return graphWithEdges;
    }

    @Test
    public void testConstructor() {
        assertNotNull(g1);
    }

    @Test
    public void testAddNode() {
        assertTrue(g1.addNode(n1));
        assertFalse(g1.addNode(n1));
    }

    @Test
    public void testRemoveNode() {
        Graph<String, String> g = graphWithNodes();
        assertTrue(g.removeNode("N4"));
        assertFalse(g.removeNode("N4"));
    }

    @Test
    public void testGetNodeSet() {
        Graph<String, String> g = graphWithNodes();
        Set<String> nodeLabels = g.getNodeSet();

    }

    @Test
    public void testGetNode() {
        Graph<String, String> g = graphWithNodes();
        Node<String, String> n4 = new Node<String, String>("N4");
        assertEquals(n4, g.getNode("N4")); // Do I need to define equals() for this to work?
        assertNull(g.getNode("N8"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(g1.isEmpty());
        assertFalse(graphWithNodes().isEmpty());
    }

    @Test
    public void testClearNodes() {
        Graph<String, String> g = graphWithNodes();
        assertFalse(g.isEmpty());
        g.clearNodes();
        assertTrue(g.isEmpty());
    }

    @Test
    public void testIterator() {
        Graph<String, String> g = graphWithNodes();
        Iterator<Node<String, String>> i = g.getIterator();
        Node n4 = new Node<String, String>("N4");
        assertEquals(i.next(), n4);
    }

    @Test
    public void testGetEdges() {
        //n4 -> n5 -> n6
        Graph<String, String> g = pathGraph();
        assertEquals(g.getNode("N4").getEdges(), g.getEdges("N4"));
    }
}
