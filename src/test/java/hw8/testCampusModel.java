package hw8;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class testCampusModel {
    CampusModel model;

    @Before
    public void setUpModel() throws IOException {
        model = new CampusModel();
    }

    @Test
    public void testShortestPath(){
        model.shortestPath("CSE", "MGH");
    }
}
