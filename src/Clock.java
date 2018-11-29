/*
 * File: Clock.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P5
 * Vers: 1.0.0 11/07/2018 hah - initial coding
 */

import java.util.ArrayList;

/**
 * Pushes pre-clock and clock for all clockable objects in system
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class Clock {

    private long clockCount = 0;
    private Logger logger;
    public Controller controller;
    public String mode;

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

    /**
     * Info that all constructors use
     * @param aLogger 
     */
    private void setMeta(Logger aLogger) {
        logger = aLogger;
    }

   /**
    * preClock then clock all items
    * @param controller
    * @param mode 
    */
    public void run(Controller controller, String mode) {
        this.controller = controller;
        this.mode = mode;
        clockCount++;
        logger.log(Logger.TIMESTAMP, "--- Clocking to " + clockCount + " seconds.");
        // preClock
       
            logger.log(Logger.INFO, "Preclocking System");
            this.controller.preClock(this.mode);

        // Clock
        
            logger.log(Logger.INFO, "Clocking System");
            controller.clock();
    }

    /**
     * Run n times
     * 
     * @param n
     * @param controller
     * @param mode 
     */
    public void run(int n, Controller controller,String mode) {
        for (int i = 0; i < n; i++) {
            run(controller, mode );
        }
    }

}
