package hw8;

import hw5.Node;
import hw7.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class testCampusPath {
    List<CampusPath> paths;

    @Before
    public void setUp() throws IOException {
        paths = CampusDataParser.parsePathData("src/main/resources/hw8/campus_paths.tsv");
    }

    @Test
    public void testGetOrigin(){
        assert paths.get(0).getOrigin().equals(new Coordinate(1536.287,1786.613));
        assert paths.get(15).getOrigin().equals(new Coordinate(2157.9826,977.00687));
    }

    @Test
    public void testGetDestination(){
        assert paths.get(0).getDestination().equals(new Coordinate(1561.0528,1786.6467));
        assert paths.get(15).getDestination().equals(new Coordinate(2167.0297,1027.1083));
    }

    @Test
    public void testGetDistance(){
        assert paths.get(0).getDistance() == 51.9858177;
        assert paths.get(15).getDistance() == 105.48943605150306;
    }
}
