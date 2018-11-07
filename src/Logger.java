/*
 * File: Logger.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

/**
 * Define abstract logger class
 * @author Hunter Holmes hholmes1@uab.edu
 */
public abstract class Logger {

    public static final int DEBUG = 0;
    public static final int INFO = 10;
    public static final int TIMESTAMP = 20;
    public static final int WARNING = 50;
    public static final int ERROR = 100;
    public static final int ALWAYS = 100000;
    protected int threshold;
    
    /**
     * Default Constructor
     */
    public Logger(){
        
    }

    /**
     * Set a new log threshold for actual logging
     * @param newThreshold level that must be met or exceeded for actual logging
     * when the logger is asked to do something
     */
    public void setLogThreshold(int newThreshold) {
        threshold = newThreshold;

    }

    /**
     * Log a message if 'level' is greater than or equal to logger's threshold.
     * The actual logging routine will add a newline to the logEntry if
     * appropriate.
     * @param level message's level
     * @param logEntry text to log (a newline will be added if appropriate)
     */
    public abstract void log(int level, String logEntry);
}
