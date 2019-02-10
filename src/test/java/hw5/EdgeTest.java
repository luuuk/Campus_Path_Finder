package hw5;

import org.junit.Test;
import static org.junit.Assert.*;

public class EdgeTest {

    private Node n1 = new Node("n1");
    private Node n2 = new Node("n2");
    private Node n3 = new Node("n3");
    private Edge e1 = new Edge(n3, n3, "3 to 3");

    @Test
    public void testConstructor(){
        assertTrue(n1.addEdge(n1, "1 to 1"));
        assertTrue(n1.addEdge(n2, "1 to 2"));
    }

    @Test
    public void testGetLabel(){
        assertEquals(e1.getLabel(), "3 to 3");
    }

    @Test
    public void testGetOrigin(){
        assertEquals(e1.getOrigin(), n3);
    }

    @Test
    public void testGetDest(){
        assertEquals(e1.getDest(), n3);
    }
}
