package hw5;

import org.junit.Test;
import static org.junit.Assert.*;

public class EdgeTest {

    private Node n1 = new Node("n1");
    private Node n2 = new Node("n2");


    @Test
    public void testConstructor(){
        assertTrue(n1.addEdge(n1, "1 to 1"));
        assertTrue(n1.addEdge(n2, "1 to 2"));
    }
}
