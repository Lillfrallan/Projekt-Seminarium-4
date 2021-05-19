package se.kth.iv1350.retailstore.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Handles the log of exceptions for developer.
 */
public class LogHandler extends IOException {
    private PrintWriter logWriter;

    /**
     * Creates a new file to log to.
     * @throws IOException is thrown if something goes wrong.
     */
    public LogHandler() throws IOException {
        FileWriter fileWriter = new FileWriter("caught_exceptions.txt");
        logWriter = new PrintWriter(fileWriter, true);
    }

    /**
     * Logs the caught exception to a file.
     * @param exception The caught exception.
     */
    public void logException(Exception exception) {
        String builder = messageCreator(exception);
        logWriter.println(builder);
    }

    private String messageCreator(Exception exception) {
        return "Time of occurrence: " + LocalDateTime.now() +
                "\nThe following exception occurred : " + exception.getClass().getSimpleName() +
                "\nException message: " + exception.getMessage() +
                "\nStack trace: " +
                Arrays.toString(exception.getStackTrace());
    }
}
