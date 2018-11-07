/*
 * File: Clock.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P5
 * Vers: 1.0.0 11/07/2018 hah - initial coding
 */

import java.util.ArrayList;

/**
 *
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class Clock {

    private ArrayList<Clockable> clockableObjects;
    private long clockCount = 0;
    private Logger logger;

    /**
     * Create a clock with a null logger
     */
    public Clock() {
        setMeta(new NullLogger());
    }

    /**
     * Create a clock with a specified logger.
     *
     * @param logger logger to use. If null, a NullLogger will be created and
     * used
     */
    public Clock(Logger logger) {
        setMeta(logger);
    }

    // Info that all constructors use
    private void setMeta(Logger aLogger) {
        clockableObjects = new ArrayList<>();
        logger = aLogger;
    }

    /**
     * Add a clockable object to the list of items to be clocked
     * @param item clockable object
     */
    public void add(Clockable item) {
        if (item != null) {
            clockableObjects.add(item);
        }
    }

    /**
     * preClock then clock all items
     * @throws MissingComponentException
     */
    public void run() throws MissingComponentException {

        clockCount++;
        logger.log(Logger.TIMESTAMP, "--- Clocking to " + clockCount + " seconds.");
        // preClock
        for (Clockable object : clockableObjects) {
            logger.log(Logger.INFO, "Preclocking " + object);
            object.preClock();
        }

        // Clock
        for (Clockable object : clockableObjects) {
            logger.log(Logger.INFO, "Clocking " + object);
            object.clock();
        }
    }

    /**
     * Run n times
     * @param n number of times to preClock then clock
     * @throws MissingComponentException
     */
    public void run(int n) throws MissingComponentException {
        for (int i = 0; i < n; i++) {
            run();
        }
    }

}
