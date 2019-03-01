package hw6;

import hw5.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class MarvelPathsTest {
    private Graph<String, String> g = new Graph<>();

    @Before
    public void buildGraph() throws MarvelParser.MalformedDataException {
        MarvelPaths.buildGraph(g, "src/test/resources/hw6/data/test.tsv");
    }

    @Test
    public void testShortestPath() {
        List<String> pathAB = MarvelPaths.shortestPath(g, "a", "b"); //direct path
        assert pathAB.size() == 3;
        List<String> pathAC = MarvelPaths.shortestPath(g, "a", "c"); //1 step path
        assert pathAC.size() == 6;
        List<String> pathAD = MarvelPaths.shortestPath(g, "a", "d"); //defined no path
        assert pathAD == null;
        List<String> pathCA = MarvelPaths.shortestPath(g, "c", "a"); //works backwards
        assert pathCA.size() == 6;
    }
}

