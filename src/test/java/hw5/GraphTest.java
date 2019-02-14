package hw5;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class GraphTest {

    private Graph g1 = new Graph();
    private Node n1 = new Node("N1");
    private Node n2 = new Node("N2");
    private Node n3 = new Node("N3");

    private Graph graphWithNodes() {
        Graph gWithNodes = new Graph();
        gWithNodes.addNode(new Node("N4"));
        gWithNodes.addNode(new Node("N5"));
        return gWithNodes;
    }


    private Graph pathGraph() {
        Graph graphWithEdges = new Graph();
        graphWithEdges.addNode(new Node("N4"));
        graphWithEdges.addNode(new Node("N5"));
        graphWithEdges.addNode(new Node("N6"));
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
        Graph g = graphWithNodes();
        assertTrue(g.removeNode("N4"));
        assertFalse(g.removeNode("N4"));
    }

    @Test
    public void testGetNodeSet() {
        Graph g = graphWithNodes();
        Set<String> nodeLabels = g.getNodeSet();

    }

    @Test
    public void testGetNode() {
        Graph g = graphWithNodes();
        Node n4 = new Node("N4");
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
        Graph g = graphWithNodes();
        assertFalse(g.isEmpty());
        g.clearNodes();
        assertTrue(g.isEmpty());
    }

/*
    @Test
    public void testGetPath() {
        Graph g = pathGraph();
        String[] path = g.getPath("N4", "N6");
        String[] expectedPath = {"N4" , "N5", "N6"};
        assertArrayEquals(expectedPath, path);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPathException(){
        Graph g = pathGraph();
        g.getPath("N3", "N4");
    }
    */

    @Test
    public void testIterator() {
        Graph g = graphWithNodes();
        Iterator i = g.getIterator();
        Node n4 = new Node("N4");
        assertEquals(i.next(), n4);
    }

    @Test
    public void testGetEdges() {
        //n4 -> n5 -> n6
        Graph g = pathGraph();
        assertEquals(g.getNode("N4").getEdges(), g.getEdges("N4"));
    }
}
