package hw6;

import java.io.*;
import java.util.*;

import hw5.*;
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

    /**
     * Returns a graph object describing the contents of the given file. (See
     * the HW6 description for details on this construction.)
     *
     * @param fileName The simple file name of the .TSV file to read from.
     * @param g        The graph to be filled in with the information from the tsv file
     * @spec.modifies g
     * @spec.effects populates g with data from MarvelParser
     * @throws MalformedDataException if the file cannot be parsed.
     */
    public static void buildGraph(Graph g, String fileName)
            throws MalformedDataException {
        Set<String> characters = new HashSet<>();
        Map<String, List<String>> books = new HashMap<>();
        MarvelParser.parseData(fileName, characters, books);
        for (String character : characters) {
            g.addNode(new Node(character));
        }
        for (String book : books.keySet()) {
            //For every book
            for (String character : books.get(book)) {
                //For every character in the book
                for (String otherCharacter : books.get(book)) {
                    //check if other character has edge to character yet
                    if (!character.equals(otherCharacter)) {
                        //if different characters, add a new edge with label book to new character
                        g.getNode(character).addEdge(g.getNode(otherCharacter), book);
                    }
                }
            }
        }
    }

    /**
     * Returns the shortest path from the given source character to the given
     * destination character via edges in the given graph.
     *
     * @param graph The graph in which to search for a path.
     * @param src   Name of the node in the grahp where the path must start.
     * @param dest  Name of the node in the graph where the path must end.
     * @return Returns the shortest (and lexicographically least) path from
     * the node with name matching src to the one with name matching dest.
     * @spec.requires graph is not null, src and dest name nodes in graph
     */
    public static List<String> shortestPath(
            Graph graph, String src, String dest) {

        Queue<Node> nextNodes = new LinkedList<>();
        Map<Node, List<String>> paths = new HashMap<>();
        nextNodes.add(graph.getNode(src));
        Node destNode = graph.getNode(dest);
        List<String> initialList = new ArrayList<>();
        paths.put(graph.getNode(src), initialList);

        //body
        while (!nextNodes.isEmpty()) {
            Node currentNode = nextNodes.remove();
            if (currentNode.equals(destNode)) {
                return paths.get(currentNode);
            }
            Set<Edge> currentEdges = currentNode.getEdges();
            List<Edge> sortedCurrentEdges = new ArrayList<>(currentEdges);
            Collections.sort(sortedCurrentEdges, new Comparator<>() {
                @Override
                public int compare(Edge o1, Edge o2) {
                    int destComp = o1.getDest().getLabel().compareTo(
                            o2.getDest().getLabel());
                    if (destComp != 0) {
                        return destComp;
                    } else {
                        return o1.getLabel().compareTo(o2.getLabel());
                    }
                }
            });
            for (Edge e : sortedCurrentEdges) {
                Node next = e.getDest();
                if (!paths.containsKey(next)) {
                    //if node has not been visited
                    List<String> currentPath = paths.get(currentNode);
                    List<String> nextPath = new ArrayList<>(currentPath);
                    // path format node1, book, node2, node2, book, node3...
                    nextPath.add(currentNode.getLabel());
                    nextPath.add(e.getLabel());
                    nextPath.add(next.getLabel());
                    paths.put(next, nextPath);
                    nextNodes.add(next);
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
        System.out.printf("Enter the name of the graph file (something.tsv): ");
        System.out.flush();
        String fileName = in.readLine().trim();

        // Construct the full file name from the simple name given.
        if (fileName.indexOf('/') < 0) {
            fileName = "src/main/resources/hw6/data/" + fileName; // NEED TO CHANGE THIS SOMEHOW?
        } else {
            System.err.printf("Error: file name must be simple (no '/'s)\n");
            System.exit(1);
        }

        // Build the graph from the file and report the time spent to the user.

        long startTime = System.currentTimeMillis();
        Graph graph = new Graph();
        buildGraph(graph, fileName);
        long endTime = System.currentTimeMillis();

        double duration = (endTime - startTime) / 1000.;
        System.out.printf("Parsed graph in %.1f seconds\n", duration);
        System.out.printf(" - %d characters\n", graph.getNodeSet().size());
        int edgeCount = 0;
        for (String nodeName : graph.getNodeSet()) {
            for (Edge e : graph.getNode(nodeName).getEdges()) {
                edgeCount++;
            }
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
        List<String> path = shortestPath(graph, src, dest);
        endTime = System.currentTimeMillis();

        duration = (endTime - startTime) / 1000.;
        System.out.printf("Found shortest path in %.1f seconds\n", duration);
        System.out.println();

        // Display the shortest path to the user.
        if (path == null) {
            System.out.printf("No path from %s to %s\n", src, dest);
        } else {
            System.out.printf("Shortest path:\n");
            System.out.printf(" from %s\n", src);
            for (int i = 0; i < path.size(); i += 3) {
                System.out.printf("  to  %s [in %s]\n",
                        path.get(i + 2), path.get(i + 1));
            }
        }
    }
}
