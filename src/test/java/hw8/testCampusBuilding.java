package hw8;

import hw5.Node;
import hw7.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class testCampusBuilding {
    List<CampusBuilding> buildings;

    @Before
    public void setUp() throws IOException {
        buildings = CampusDataParser.parseBuildingData("src/main/resources/hw8/campus_buildings_new.tsv");
    }
    //List<CampusPath> paths = CampusDataParser.parsePathData("src/main/resources/hw8/campus_paths.tsv");

    @Test
    public void testGetLocation(){
        assert buildings.get(0).getLocation().equals(new Coordinate(1914.5103,1709.8816));
        assert buildings.get(50).getLocation().equals(new Coordinate(2344.8512,1114.6251));
    }

    @Test
    public void testGetShortName(){
        assert buildings.get(0).getShortName().equals("BAG");
        assert buildings.get(50).getShortName().equals("CMU");
    }

    @Test
    public void testGetLongName(){
        assert buildings.get(0).getLongName().equals("Bagley Hall (East Entrance)");
        assert buildings.get(50).getLongName().equals("Communications Building");
    }
}
