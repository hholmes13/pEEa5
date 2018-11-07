/*
 * File: NullLogger.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P3
 * Vers: 1.2.0 10/14/2019 hah - added blower functionality and MissingComponentException
 * Vers: 1.1.0 09/26/2018 hah - modifications and additions for P2, added loggers and clock
 * Vers: 1.0.0 09/17/2018 hah - initial coding
 */

/**
 * Ignore request to log
 * @author hholmes
 */
public class NullLogger extends Logger {
    
    /**
     * Ignore request to log
     * @param level     unused
     * @param logEntry  unused
     */
    @Override
    public void log(int level, String logEntry) {
    }
}
