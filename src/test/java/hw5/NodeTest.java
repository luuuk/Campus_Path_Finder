package hw5;

import org.junit.*;
import static org.junit.Assert.*;


public class NodeTest {
    private Node n1 = new Node("N1");
    private Node n2 = new Node("N2");
    private Node n3 = new Node("N3");
    private Node n4 = new Node("N4");
    private Node n5 = new Node("N5");

    @Test
    public void testConstructorLabel(){
        assertNotNull(n1);
    }

    @Test
    public void testGetLabel(){
        assertEquals(n1.getLabel(), "N1");
    }

    @Test
    public void testAddEdge(){
        assertTrue(n1.addEdge(n2, "1 to 2"));
        assertTrue(n1.hasEdge("1 to 2"));
        assertFalse(n1.addEdge(n2, "1 to 2"));
        assertTrue(n1.addEdge(n1, "1 to 1"));
    }

    @Test
    public void testRemoveEdge(){
        n3.addEdge(n2, "3 to 2");
        assertTrue(n3.hasEdge("3 to 2"));
        assertTrue(n3.removeEdge("3 to 2"));
        assertFalse(n3.removeEdge("3 to 2"));
    }

    @Test
    public void testClearEdges(){
        n4.addEdge(n1, "4 to 1");
        n4.addEdge(n2, "4 to 2");
        assertTrue(n4.hasEdge("4 to 2"));
        assertTrue(n4.hasEdge("4 to 1"));
        n4.clearEdges();
        assertFalse(n4.hasEdge("4 to 2"));
        assertFalse(n4.hasEdge("4 to 1"));
    }

    @Test
    public void testHasEdge(){
        assertFalse(n5.hasEdge("5 to 1"));
        n5.addEdge(n1, "5 to 1");
        assertTrue(n5.hasEdge("5 to 1"));
    }
}