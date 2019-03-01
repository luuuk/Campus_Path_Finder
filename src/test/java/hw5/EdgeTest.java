package hw5;

import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {

    private Node<String, String> n1 = new Node<String, String>("n1");
    private Node<String, String> n2 = new Node<String, String>("n2");
    private Node<String, String> n3 = new Node<String, String>("n3");
    private Edge<String, String> e1 = new Edge<String, String>(n3, n3, "3 to 3");
    private Edge<String, String> e2 = new Edge<String, String>(n1, n3, "1 to 3");
    private Edge<String, String> e3 = new Edge<String, String>(n3, n1, "3 to 1");
    private Edge<String, String> e4 = new Edge<String, String>(n3, n3, "3 to 3");

    @Test
    public void testConstructor() {
        assertTrue(n1.addEdge(n1, "1 to 1"));
        assertTrue(n1.addEdge(n2, "1 to 2"));
    }

    @Test
    public void testGetLabel() {
        assertEquals(e1.getLabel(), "3 to 3");
    }

    @Test
    public void testGetOrigin() {
        assertEquals(e1.getOrigin(), n3);
    }

    @Test
    public void testGetDest() {
        assertEquals(e1.getDest(), n3);
    }

    @Test
    public void testEquals() {
        assertEquals(e1, e4);
        assertNotEquals(e1, e2);
        assertNotEquals(e1, e3);
    }
}
