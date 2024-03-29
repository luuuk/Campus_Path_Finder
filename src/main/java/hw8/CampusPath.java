package hw8;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import java.util.Comparator;

/**
 * Stores a single row from campus_paths.tsv. The names of the methods are
 * chosen to match the names in the first line of that file.
 */
public class CampusPath {

    // Not an ADT

    @CsvCustomBindByName(converter = CoordinateConverter.class)
    private Coordinate origin;

    @CsvCustomBindByName(converter = CoordinateConverter.class)
    private Coordinate destination;

    @CsvBindByName
    private double distance;

    /**
     * Returns the starting point of the path segment.
     * @return the starting point of the path segment
     */
    public Coordinate getOrigin() {
        return origin;
    }

    /**
     * Returns the ending point of the path segment.
     * @return the ending point of the path segment
     */
    public Coordinate getDestination() {
        return destination;
    }

    /**
     * Returns the distance between the starting and ending points.
     * @return the distance of the path segment
     */
    public double getDistance() {
        return distance;
    }

    /**
     * sets destination of this
     * @param destination coordinate representing the destination
     */
    public void setDestination(Coordinate destination) {
        this.destination = destination;
    }

    /**
     * sets distance of this
     * @param distance distance between origin and destination
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * sets origin of this
     * @param origin coordinate representing the origin
     */
    public void setOrigin(Coordinate origin) {
        this.origin = origin;
    }
}
