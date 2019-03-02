package hw8;

import java.io.*;
import java.util.*;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


/**
 * Methods to parse the campus data files, campus_paths.tsv and
 * campus_buildings.tsv. This uses the Open CSV library for parsing.
 */
public class CampusDataParser {

  /**
   * Returns the rows from the given file, each of which should match the shape
   * defined by CampusPath.
   * @param fileName Path to the TSV file to read. (This should be a relative
   *     path from the root directory.)
   * @throws IOException if any I/O error occurs reading the file
   * @return List of CampusPath objects, one describing each row.
   */
  public static List<CampusPath> parsePathData(String fileName)
      throws IOException {
    FileReader reader = new FileReader(fileName);
    CsvToBean<CampusPath> csvToBean = new CsvToBeanBuilder<CampusPath>(reader)
        .withType(CampusPath.class)
        /* ... other options ... */
        .build();
    return csvToBean.parse();
  }

  // TODO: add similar code for parsing campus_buildings.tsv

}
