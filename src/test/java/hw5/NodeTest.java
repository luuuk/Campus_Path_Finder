package hw5;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class NodeTest {
    private Node<String, String> n1 = new Node<String, String>("N1");
    private Node<String, String> n2 = new Node<String, String>("N2");
    private Node<String, String> n3 = new Node<String, String>("N3");
    private Node<String, String> n4 = new Node<String, String>("N4");
    private Node<String, String> n5 = new Node<String, String>("N5");
    private Node<String, String> n6 = new Node<String, String>("N6");
    private Node<String, String> n7 = new Node<String, String>("N7");

    @Test
    public void testConstructorLabel() {
        assertNotNull(n1);
    }

    @Test
    public void testGetLabel() {
        assertEquals(n1.getLabel(), "N1");
    }

    @Test
    public void testAddEdge() {
        assertTrue(n1.addEdge(n2, "1 to 2"));
        assertNotNull(n1.hasEdgeTo("N2"));
        assertFalse(n1.addEdge(n2, "1 to 2"));
        assertTrue(n1.addEdge(n1, "1 to 1"));
    }

    @Test
    public void testRemoveEdge() {
        n3.addEdge(n2, "3 to 2");
        assertNotNull(n3.hasEdgeTo("N2"));
        assertTrue(n3.removeEdge("N2"));
        assertFalse(n3.removeEdge("N2"));
    }

    @Test
    public void testClearEdgesToNode() {
        n4.addEdge(n1, "4 to 1");
        n4.addEdge(n2, "4 to 2");
        assertNotNull(n4.hasEdgeTo("N2"));
        assertNotNull(n4.hasEdgeTo("N1"));
        n4.clearEdgesToNode("N1");
        assertNotNull(n4.hasEdgeTo("N2"));
        assertNull(n4.hasEdgeTo("N1"));
    }

    @Test
    public void testIsTouching() {
        assertFalse(n6.isTouching("N7"));
        n6.addEdge(n7, "6 to 7");
        assertTrue(n6.isTouching("N7"));
        assertFalse(n6.isTouching("N6"));
        n6.addEdge(n6, "6 to 6");
        assertTrue(n6.isTouching("N6"));
    }

    @Test
    public void testHasEdgeTo() {
        assertNull(n5.hasEdgeTo("N1"));
        n5.addEdge(n1, "5 to 1");
        assertNotNull(n5.hasEdgeTo("N1"));
    }

    @Test
    public void testGetEdges() {
        n4.addEdge(n1, "4 to 1");
        n4.addEdge(n4, "4 to 4");
        Set<Edge> expectedEdges = new HashSet<>();
        expectedEdges.add(new Edge<String, String>(n4, n1, "4 to 1"));
        expectedEdges.add(new Edge<String, String>(n4, n4, "4 to 4"));
        Set<Edge<String, String>> edgeSet = n4.getEdges();
        assertEquals(edgeSet, expectedEdges);
    }

    @Test
    public void testEquals() {
        Node n1 = new Node<String, String>("N1");
        Node n2 = new Node<String, String>("N1");
        Node n3 = new Node<String, String>("N3");
        assertEquals(n1, n2);
        assertNotEquals(n2, n3);
    }
}