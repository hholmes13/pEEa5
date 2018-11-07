/*
 * File: PrintLogger.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

/**
 * Prints messages that contain information about the actions of a system
 * Decision to print an action are based on a given threshold level
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class PrintLogger extends Logger {

    /**
     * Create a PrintLogger object at DEBUG level
     */
    public PrintLogger() {
        this.threshold = DEBUG;
    }

    /**
     * Create a PrintLogger with specified threshold
     * @param threshold level that has to be met or exceeded for logging to
     * occur
     */
    public PrintLogger(int threshold) {
        this.threshold = threshold;
    }

    /**
     * Prints a string to stdout if level is greater than or equal to threshold 
     * The printing routine will add a newline to the logEntry The logger class 
     * defines common values for level
     * @param level value noting the type of the information
     * @param logEntry text to be part of the printed log
     */
    @Override
    public void log(int level, String logEntry) {
        if (level >= threshold) {
            System.out.println(logEntry);
        }
    }
}
