package hw8;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

/**
 * Stores a single row from campus_buildings.tsv. The names of the methods are
 * chosen to match the names in the first line of that file.
 */
public class CampusBuilding implements Comparable<CampusBuilding> {
    // Not an ADT

    @CsvBindByName
    private String shortName;

    @CsvBindByName
    private String longName;

    @CsvCustomBindByName(converter = CoordinateConverter.class)
    private Coordinate location;

    /**
     * Returns the coordinate location of the building.
     *
     * @return the location of the building represented by a coordinate point
     */
    public Coordinate getLocation() {
        return location;
    }

    /**
     * Returns the short name of the building
     *
     * @return the short name of the building
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Returns the long name of the building
     *
     * @return long name of the building
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Returns a string representation of the building
     *
     * @return a string representing the building
     */
    public String toString() {
        return shortName + ": " + longName;
    }

    /**
     * Implements comparable interface for this using the short names of buildings
     *
     * @param o the CampusBuilding this is compared to
     * @return greater than 0 if this is greater than o, 0 if they are the same, less than 0 otherwise
     */
    public int compareTo(CampusBuilding o) {
        return this.getShortName().compareTo(o.getShortName());
    }
}
