package hw8;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class testCampusDataParser {

    @Test
    public void testParsePathData() throws IOException {
        List<CampusPath> paths = CampusDataParser.parsePathData("src/main/resources/hw8/campus_paths.tsv");
        for (CampusPath path : paths) {
            assert path.getDistance() > 0;
        }
    }

    @Test
    public void getTestParseBuildingData() throws IOException {
        List<CampusBuilding> buildings = CampusDataParser.parseBuildingData("src/main/resources/hw8/campus_buildings_new.tsv");
        for (CampusBuilding b : buildings) {
            System.out.println(b.toString());
        }
        assert buildings.size() == 51;
    }
}
