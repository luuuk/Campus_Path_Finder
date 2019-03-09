package hw8;

import hw5.Node;
import hw7.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class testCampusModel {
    CampusModel model;

    @Before
    public void setUpModel() throws IOException {
        model = new CampusModel();
    }

    @Test
    public void testShortestPath() {
        List<CampusBuilding> buildings = model.getBuildings();
        Path<Coordinate, Double> pathBAGtoCMU = model.shortestPath(buildings.get(0), buildings.get(50));
        List<Node<Coordinate, Double>> pathList = pathBAGtoCMU.getPath();
        for (int i = 0; i < pathList.size() - 1; i++) {
            //System.out.println(pathList.get(i).getLabel().toString());
        }
        assert Math.round(pathBAGtoCMU.getWeight()) == 1769;
    }

    @Test
    public void testGetBuildings() {
        List<CampusBuilding> buildings = model.getBuildings();
        assert buildings.get(0).getLongName().equals("Bagley Hall (East Entrance)");
        assert buildings.get(0).getShortName().equals("BAG");
        assert buildings.get(0).getLocation().equals(new Coordinate(1914.5103,1709.8816));
        assert buildings.get(50).getLongName().equals("Communications Building");
        assert buildings.get(50).getShortName().equals("CMU");
        assert buildings.get(50).getLocation().equals(new Coordinate(2344.8512,1114.6251));
    }
}
