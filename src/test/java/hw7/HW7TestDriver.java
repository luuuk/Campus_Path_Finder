package hw7;

import hw5.Edge;
import hw5.Graph;
import hw5.Node;
import hw6.MarvelParser;

import java.io.*;
import java.util.*;


/**
 * A driver that reads test scripts from files and executes the commands
 * described. This can be used for testing Dijkstra's algorithm and your
 * construction of the new Marvel graph.
 *
 * <p>This is not an ADT.</p>
 */
public class HW7TestDriver {

    /**
     * Entry point for a program that reads a test script, either from System.in
     * or from a file provided, and executes the commands found there.
     *
     * @param args List of arguments passed at the command-line.
     * @spec.requires args it not null
     * @spec.effects Interacts with the user by reading from and writing to the
     * console (System.in and System.out).
     */
    public static void main(String args[]) {
        if (args.length > 1) {
            System.err.println("Usage: java HW7TestDriver [CMD_FILE]");
            System.exit(1);
        }

        try {
            // Create a test driver reading from a file, if a name was provided, or
            // from stdin, if not.
            HW7TestDriver driver = new HW7TestDriver(
                    (args.length > 0) ? new FileReader(new File(args[0])) :
                            new InputStreamReader(System.in),
                    new OutputStreamWriter(System.out));

            // Run the tests.
            driver.runTests();

        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }
    }

    /**
     * String -> Graph: maps the names of graphs to the actual graph
     **/
    private final Map<String, Graph<String, Double>> graphs = new HashMap<>();
    private final PrintWriter output;
    private final BufferedReader input;

    /**
     * Creates a test driver that will read commands from r and write output to w
     *
     * @param r Object from which to read the test script.
     * @param w Object to which to write the output of the commands.
     * @spec.requires r, w != null
     */
    public HW7TestDriver(Reader r, Writer w) {
        input = new BufferedReader(r);
        output = new PrintWriter(w);
    }

    /**
     * Executes the commands found in the input.
     *
     * @throws IOException if the input or output sources encounters I/O errors
     * @spec.modifies this
     * @spec.effects Executes the commands read from the input and writes
     * results to the output
     */
    public void runTests() throws IOException {
        String inputLine;
        while ((inputLine = input.readLine()) != null) {
            if ((inputLine.trim().length() == 0) ||
                    (inputLine.charAt(0) == '#')) {
                // echo blank and comment lines
                output.println(inputLine);
            } else {
                // separate the input line on white space
                StringTokenizer st = new StringTokenizer(inputLine);
                if (st.hasMoreTokens()) {
                    String command = st.nextToken();

                    List<String> arguments = new ArrayList<String>();
                    while (st.hasMoreTokens()) {
                        arguments.add(st.nextToken());
                    }

                    executeCommand(command, arguments);
                }
            }
            output.flush();
        }
    }

    private void executeCommand(String command, List<String> arguments) {
        try {
            if (command.equals("CreateGraph")) {
                createGraph(arguments);
            } else if (command.equals("AddNode")) {
                addNode(arguments);
            } else if (command.equals("AddEdge")) {
                addEdge(arguments);
            } else if (command.equals("ListNodes")) {
                listNodes(arguments);
            } else if (command.equals("ListChildren")) {
                listChildren(arguments);
            } else if (command.equals("LoadGraph")) {
                loadGraph(arguments);
            } else if (command.equals("FindPath")) {
                findPath(arguments);
            } else {
                output.println("Unrecognized command: " + command);
            }
        } catch (Exception e) {
            output.println("Exception: " + e.toString());
        }
    }

    private void createGraph(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new HW7TestDriver.CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
        graphs.put(graphName, new Graph<String, Double>());
        output.println("created graph " + graphName);
    }

    private void addNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new HW7TestDriver.CommandException("Bad arguments to addNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
        Graph<String, Double> g = graphs.get(graphName);
        g.addNode(new Node<String, Double>(nodeName));
        output.println("added node " + g.getNode(nodeName).getLabel() + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new HW7TestDriver.CommandException("Bad arguments to addEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        Double edgeLabel = Double.parseDouble(arguments.get(3));

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
                         Double edgeLabel) {
        Graph<String, Double> g = graphs.get(graphName);
        g.getNode(parentName).addEdge(g.getNode(childName), edgeLabel);
        String edgeWeight = String.format("%.3f", edgeLabel);
        output.println("added edge " + edgeWeight + " from " + parentName + " to " + childName +
                " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new HW7TestDriver.CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
        Graph<String, Double> g = graphs.get(graphName);
        output.print(graphName + " contains:");
        Set<String> nodeSet = g.getNodeSet();
        for (String nodeName : nodeSet) {
            output.print(" " + nodeName);
        }
        output.println();
    }

    private void listChildren(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new HW7TestDriver.CommandException("Bad arguments to listChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
        Graph<String, Double> g = graphs.get(graphName);
        Node<String, Double> n = g.getNode(parentName);
        output.print("the children of " + parentName + " in " + graphName + " are:");
        Set<String> edges = new TreeSet<>();
        for (Edge<String, Double> e : n.getEdges()) {
            String edgeWeight = String.format("%.3f", e.getLabel());
            edges.add(" " + e.getDest().getLabel() + "(" + edgeWeight + ")");
        }
        for (String e : edges) {
            output.print(e);
        }
        output.println();
    }

    private void loadGraph(List<String> arguments) throws hw6.MarvelParser.MalformedDataException {
        if (arguments.size() != 2) {
            throw new HW7TestDriver.CommandException("Bad arguments to LoadGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        String fileName = arguments.get(1);
        loadGraph(graphName, fileName);
    }

    private void loadGraph(String graphName, String fileName) throws hw6.MarvelParser.MalformedDataException {
        Graph<String, Double> g = new Graph<>();
        graphs.put(graphName, g);
        MarvelPaths2.buildGraph(g, "src/test/resources/hw7/data/" + fileName);
        output.println("loaded graph " + graphName);
    }

    private void findPath(List<String> args) {
        if (args.size() != 3) {
            throw new HW7TestDriver.CommandException("Bad arguments to FindPath: " + args);
        }
        String graphName = args.get(0);
        String originName = args.get(1).replace('_', ' ');
        String destName = args.get(2).replace('_', ' ');
        findPath(graphName, originName, destName);
    }

    private void findPath(String graphName, String originName, String destName) {
        Graph<String, Double> g = graphs.get(graphName);
        Set<String> nodeSet = g.getNodeSet();
        if (!nodeSet.contains(originName)) {
            output.println("unknown character " + originName);
        }
        if (!nodeSet.contains(destName)) {
            output.println("unknown character " + destName);
        }
        if (g.getNodeSet().contains(destName) && g.getNodeSet().contains(originName)) {
            Path<String, Double> path = MarvelPaths2.shortestPath(g, originName, destName);
            output.println("path from " + originName + " to " + destName + ":");
            if (path == null) {
                output.println("no path found");
            } else {
                List<Node<String, Double>> pathList = path.getPath();
                for (int i = 0; i < pathList.size() - 1; i++) {
                    //abc path format
                    Node<String, Double> origin = pathList.get(i);
                    Node<String, Double> dest = pathList.get(i + 1);
                    Edge<String, Double> edge = origin.hasEdgeTo(dest.getLabel());
                    String edgeWeight = String.format("%.3f", edge.getLabel());
                    output.println(origin.getLabel() + " to " + dest.getLabel() + " with weight " + edgeWeight);
                }
                String pathWeight = String.format("%.3f", path.getWeight());
                output.println("total cost: " + pathWeight);
            }
        }
    }

    /**
     * This exception results when the input file cannot be parsed properly
     **/
    static class CommandException extends RuntimeException {

        public CommandException() {
            super();
        }

        public CommandException(String s) {
            super(s);
        }

        public static final long serialVersionUID = 3495;
    }

}
