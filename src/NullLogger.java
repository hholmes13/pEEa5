/*
 * File: NullLogger.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P5
 * Vers: 1.0.0 11/07/2018 hah - initial coding
 */

/**
 * Ignore request to log
 * @author hholmes
 */
public class NullLogger extends Logger {

    /**
     * Ignore request to log
     * @param level unused
     * @param logEntry unused
     */
    @Override
    public void log(int level, String logEntry) {
    }
}
