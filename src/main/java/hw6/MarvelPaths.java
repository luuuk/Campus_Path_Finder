package hw6;

import java.io.*;

import hw6.MarvelParser.MalformedDataException;

/**
 * Program that allows users to find the shortest path between characters in
 * the Marvel graph. The user is prompted to enter the name of the file
 * containing information about characters and comics along with the names of
 * two characters in question. If both characters exist, it will then display a
 * shortest path between the two characters.
 *
 * <p>This is not an ADT.</p>
 */
public class MarvelPaths {

  // TODO: Add a static method, following the outline below, that builds a
  //        graph from the contents of the file with the given (simple) name.
  //
  // /**
  //  * Returns a graph object describing the contents of the given file. (See
  //  * the HW6 description for details on this construction.)
  //  *
  //  * @param fileName The simple file name of the .TSV file to read from.
  //  * @throws MalformedDataException if the file cannot be parsed.
  //  * @return A graph object constructed from the contents of given file.
  //  */
  // public static YOUR_GRAPH_CLASS buildGraph(String fileName)
  //    throws MalformedDataException {
  //   ... includes a call to MarvelPaths.parseData ...
  // }

  // TODO: Add a static method, following the outline below, that finds the
  //       shortest path between two nodes using breadth-first search.
  //
  // /**
  //  * Returns the shortest path from the given source character to the given
  //  * destination character via edges in the given graph.
  //  *
  //  * @param graph The graph in which to search for a path.
  //  * @param src Name of the node in the grahp where the path must start.
  //  * @param dest Name of the node in the graph where the path must end.
  //  * @spec.requires graph is not null, src and dest name nodes in graph
  //  * @return Returns the shortest (and lexicographically least) path from
  //  *     the node with name matching src to the one with name matching dest.
  //  */
  // public static SOME_PATH_REPRESENTATION shortestPath(
  //     YOUR_GRAPH_CLASS graph, String src, String dest) {
  //  ... implement BFS as described in HW6 ...
  // }

  /**
   * Entry point for the program described in the class overview.
   *
   * @param args List of arguments passed at the command-line. Ignored.
   * @spec.requires args it not null
   * @spec.effects Interacts with the user by reading from and writing to the
   *     console (System.in and System.out).
   * @throws Exception for any I/O or malformed data error
   */
  public static void main(String[] args) throws Exception {

    // Create a buffered reader so that we can read full lines of input.
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    // Retrieve the name of the file to build the graph from.
    System.out.printf("Enter the name of the graph file (something.tsv): ");
    System.out.flush();
    String fileName = in.readLine().trim();

    // Construct the full file name from the simple name given.
    if (fileName.indexOf('/') < 0) {
      fileName = "src/main/resources/hw6/data/" + fileName;
    } else {
      System.err.printf("Error: file name must be simple (no '/'s)\n");
      System.exit(1);
    }

    // Build the graph from the file and report the time spent to the user.

    long startTime = System.currentTimeMillis();
    // TODO: Fill this in to call your method above for building the graph.
    // YOUR_GRAPH_CLASS graph = buildGraph(fileName);
    long endTime = System.currentTimeMillis();

    double duration = (endTime - startTime) / 1000.;
    System.out.printf("Parsed graph in %.1f seconds\n", duration);
    // TODO: Fill these in with calls to get the number of nodes and edges
    // System.out.printf(" - %d characters\n", number of nodes);
    // System.out.printf(" - %d pairs appeared together\n", (number of edges)/2);
    System.out.println();

    // Read in the names of the two characters and check that they exist.

    System.out.printf("To find a path between two characters...\n");
    System.out.printf("Enter the first character's name: ");
    System.out.flush();
    String src = in.readLine().trim();
    // TODO: Fill this in with a call to check for a node with this name
    // if (!graph has node src) {
    //   System.err.printf("Error: no such node\n");
    //   System.exit(1);
    // }

    System.out.printf("Enter the second character's name: ");
    System.out.flush();
    String dest = in.readLine().trim();
    // TODO: Fill this in with a call to check for a node with this name
    // if (!graph has node dest) {
    //   System.err.printf("Error: no such node\n");
    //   System.exit(1);
    // }

    // Find the shortest path.

    startTime = System.currentTimeMillis();
    // TODO: Fill this in to call your method above for finding shortest path.
    // SOME_PATH_REPRESENTATION path = shortestPath(graph, src, dest);
    endTime = System.currentTimeMillis();

    duration = (endTime - startTime) / 1000.;
    System.out.printf("Found shortest path in %.1f seconds\n", duration);
    System.out.println();

    // Display the shortest path to the user.
    if (true /* TODO: change to: path == null */) {
      System.out.printf("No path from %s to %s\n", src, dest);
    } else {
      System.out.printf("Shortest path:\n");
      System.out.printf(" from %s\n", src);
      // TODO: Fill this in to loop though the edges in the path.
      // for (each edge in the path (in order)) {
      //   System.out.printf("  to  %s [in %s]\n",
      //       name of target node of edge, label of edge);
      // }
    }
  }
}
