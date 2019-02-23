package hw7;

import java.io.*;


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
   *     console (System.in and System.out).
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
          (args.length > 0) ?  new FileReader(new File(args[0])) :
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
   * Creates a test driver that will read commands from r and write output to w
   *
   * @spec.requires r, w != null
   * @param r Object from which to read the test script.
   * @param w Object to which to write the output of the commands.
   */
  public HW7TestDriver(Reader r, Writer w) {
    // TODO: implement this method to record the reader and writer. How you do
    //       so will depend on whether you subclass HW5TestDriver or not.
  }

  /**
   * Executes the commands found in the input.
   * 
   * @spec.modifies this
   * @spec.effects Executes the commands read from the input and writes
   *      results to the output
   * @throws IOException if the input or output sources encounters I/O errors
   */
  public void runTests() throws IOException {
    // TODO: implement this method via one of these options
    //  1. copy code from HW6TestDriver
    //  2. change HW6TestDriver to support subclassing, then subclass it
  }
}
