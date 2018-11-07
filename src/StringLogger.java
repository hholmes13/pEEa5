/*
 * File: StringLogger.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

import java.util.ArrayList;

/**
 * StringLogger stores log messages in ArrayList of strings
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class StringLogger extends Logger {

    private int threshold;
    private ArrayList<String> logEntries;

    /**
     * Constructor for StringLogger
     */
    public StringLogger() {
        
    }

    /**
     * Create a StringLogger with specific threshold
     * @param threshold level that has to be met or exceeded for logging to
     * occur
     */
    public StringLogger(int threshold) {
        logEntries = new ArrayList<>();
        this.threshold = threshold;
    }

    /**
     * Retrieve the logged strings
     * @return an ArrayList of strings is returned
     */
    public ArrayList<String> getLog() {
        return logEntries;
    }

    /**
     * Collect a string if level is greater than or equal to threshold. 
     * The logger class defines common values for level.
     * @param level value noting the type of the information
     * @param logEntry text to be part of the accumulated string log
     */
    @Override
    public void log(int level, String logEntry) {
        if (level >= threshold) {
            logEntries.add(logEntry);
        }
    }

    /**
     * Clear the accumulated log strings
     */
    public void clear() {
        logEntries.clear();
    }

}
