package hw7;

import hw5.Graph;
import hw6.MarvelParser;
import hw6.MarvelPaths;
import hw7.MarvelPaths2;
import org.junit.*;

import java.util.*;

import static org.junit.Assert.*;

public class MarvelPaths2Test {
    private Graph<String, Double> g = new Graph<>();

    @Before
    public void testBuildGraph() throws MarvelParser.MalformedDataException {
        MarvelPaths2.buildGraph(g, "src/test/resources/hw7/data/test.tsv");
    }

    @Test
    public void testShortestPath() {
        //a to b = 1/2 direct
        assert MarvelPaths2.shortestPath(g, "a", "b").getWeight() == .5;
        //b to c = 1/4 direct
        assert MarvelPaths2.shortestPath(g, "b", "c").getWeight() == .25;
        //a to d = no path
        assertNull(MarvelPaths2.shortestPath(g, "a", "d"));
        //a to e = 2/3 via c
        assert MarvelPaths2.shortestPath(g, "a", "e").getWeight() == (2.0 / 3.0);
        //a to f = 4/3 via c (beats 3/2 via b)
        assert MarvelPaths2.shortestPath(g, "a", "f").getWeight() == (4.0 / 3.0);
        //a to a = 0
        assert MarvelPaths2.shortestPath(g, "a", "a").getWeight() == 0.0;
        //g to h = 1 (tie between routes)
        assert MarvelPaths2.shortestPath(g, "g", "h").getWeight() == 1.0;
    }
}
