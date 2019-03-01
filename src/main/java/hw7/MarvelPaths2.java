package hw7;

import hw5.Edge;
import hw5.Graph;
import hw5.Node;
import hw6.MarvelParser;
import hw6.MarvelParser.MalformedDataException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Program that allows users to find the shortest path between characters in
 * the Marvel graph. The user is prompted to enter the name of the file
 * containing information about characters and comics along with the names of
 * two characters in question. If both characters exist, it will then display a
 * shortest path between the two characters.
 *
 * <p>This is not an ADT.</p>
 */
public class MarvelPaths2 {

    /**
     * Fills in a graph object describing the contents of the given file. (See
     * the HW6 description for details on this construction.)
     *
     * @param fileName The simple file name of the .TSV file to read from.
     * @param g        The graph to be filled in with the information from the tsv file
     * @throws MalformedDataException if the file cannot be parsed.
     * @spec.modifies g
     * @spec.effects populates g with data from MarvelParser
     */
    public static void buildGraph(Graph<String, Double> g, String fileName)
            throws MalformedDataException {
        Set<String> characters = new HashSet<>();
        Map<String, List<String>> books = new HashMap<>();
        MarvelParser.parseData(fileName, characters, books);
        //add each new character as a Node
        for (String character : characters) {
            g.addNode(new Node<>(character));
        }
        for (String book : books.keySet()) {
            //For every book
            for (String character : books.get(book)) {
                //For every character in the book
                for (String otherCharacter : books.get(book)) {
                    //check if other character has edge to character yet
                    if (!character.equals(otherCharacter)) {
                        //if there is not yet an edge between character and otherCharacter
                        if (!g.getNode(character).isTouching(otherCharacter)) {
                            //add edge to otherCharacter with weight 1.0
                            g.getNode(character).addEdge(g.getNode(otherCharacter), 1.0);
                        } else {
                            //if the characters are already touching
                            Edge<String, Double> e = g.getNode(character).hasEdgeTo(otherCharacter);
                            Double weight = e.getLabel();
                            g.getNode(character).removeEdge(otherCharacter);
                            g.getNode(character).addEdge(g.getNode(otherCharacter), Math.pow(Math.pow(weight, -1) + 1, -1));
                        }
                    }
                }
            }
        }
    }

    /**
     * Returns the shortest path from the given source character to the given
     * destination character via edges in the given graph.
     *
     * @param graph  The graph in which to search for a path.
     * @param origin Name of the node in the grahp where the path must start.
     * @param dest   Name of the node in the graph where the path must end.
     * @return Returns the shortest (and lexicographically least) path from
     * the node with name matching src to the one with name matching dest.
     * @spec.requires graph is not null, src and dest name nodes in graph
     */
    public static Path<String, Double> shortestPath(
            Graph<String, Double> graph, String origin, String dest) {

        Queue<Path<String, Double>> active = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Path<String, Double> o1, Path<String, Double> o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        Set<Node<String, Double>> finished = new HashSet<>();
        Node<String, Double> start = graph.getNode(origin);
        active.add(new Path<>(start));
        while (!active.isEmpty()) {
            Path<String, Double> minPath = active.remove();
            Node<String, Double> minDest = minPath.getDest();
            if (minDest.getLabel().equals(dest)) {
                return minPath;
            }
            if (finished.contains(minDest)) {
                continue;
            }
            finished.add(minDest);
            for (Edge<String, Double> e : minDest.getEdges()) {
                if (!finished.contains(e.getDest())) {
                    Path<String, Double> newPath = minPath.addNewNode(e.getDest(), e.getLabel());
                    active.add(newPath);
                }
            }
        }
        return null;
    }

    /**
     * Entry point for the program described in the class overview.
     *
     * @param args List of arguments passed at the command-line. Ignored.
     * @throws Exception for any I/O or malformed data error
     * @spec.requires args it not null
     * @spec.effects Interacts with the user by reading from and writing to the
     * console (System.in and System.out).
     */
    public static void main(String[] args) throws Exception {

        // Create a buffered reader so that we can read full lines of input.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Retrieve the name of the file to build the graph from.
        System.out.print("Enter the name of the graph file (something.tsv): ");
        System.out.flush();
        String fileName = in.readLine().trim();

        // Construct the full file name from the simple name given.
        if (fileName.indexOf('/') < 0) {
            fileName = "src/test/resources/hw7/data/" + fileName;
        } else {
            System.err.printf("Error: file name must be simple (no '/'s)\n");
            System.exit(1);
        }

        // Build the graph from the file and report the time spent to the user.

        long startTime = System.currentTimeMillis();
        Graph<String, Double> graph = new Graph<>();
        buildGraph(graph, fileName);
        long endTime = System.currentTimeMillis();

        double duration = (endTime - startTime) / 1000.;
        System.out.printf("Parsed graph in %.1f seconds\n", duration);
        System.out.printf(" - %d characters\n", graph.getNodeSet().size());
        int edgeCount = 0;
        for (String nodeName : graph.getNodeSet()) {
            edgeCount += graph.getNode(nodeName).getEdges().size();
        }
        System.out.printf(" - %d pairs appeared together\n", edgeCount / 2);
        System.out.println();

        // Read in the names of the two characters and check that they exist.

        System.out.printf("To find a path between two characters...\n");
        System.out.printf("Enter the first character's name: ");
        System.out.flush();
        String src = in.readLine().trim();
        if (!graph.getNodeSet().contains(src)) {
            System.err.printf("Error: no such node\n");
            System.exit(1);
        }

        System.out.printf("Enter the second character's name: ");
        System.out.flush();
        String dest = in.readLine().trim();
        if (!graph.getNodeSet().contains(dest)) {
            System.err.printf("Error: no such node\n");
            System.exit(1);
        }

        // Find the shortest path.

        startTime = System.currentTimeMillis();
        Path<String, Double> path = shortestPath(graph, src, dest);
        endTime = System.currentTimeMillis();

        duration = (endTime - startTime) / 1000.;
        System.out.printf("Found shortest path in %.1f seconds\n", duration);
        System.out.println();

        // Display the shortest path to the user
        if (path == null) {
            System.out.printf("No path from %s to %s\n", src, dest);
        } else {
            List<Node<String, Double>> pathList = path.getPath();
            System.out.printf("Shortest path:\n");
            System.out.printf(" from %s\n", src);
            for (int i = 0; i < pathList.size() - 1; i++) {
                Node<String, Double> origin = pathList.get(i);
                Node<String, Double> destination = pathList.get(i + 1);
                Edge<String, Double> edge = origin.hasEdgeTo(destination.getLabel());
                String edgeWeight = String.format("%.3f", edge.getLabel());
                System.out.printf("  to  %s [weight: %s]\n",
                        pathList.get(i + 1).getLabel(), edgeWeight);
            }
            System.out.print("Total weight: " + String.format("%.3f", path.getWeight()));
        }
    }
}
