/*
 * File: Clockable.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P5
 * Vers: 1.0.0 11/07/2018 hah - initial coding
 */

/**
 *
 * @author Hunter Holmes hholmes1@uab.edu
 */
public interface Clockable {

    /**
     * Take actions based on notifications that the clock is about to happen.
     * The controller's latest actions were issued on second ago. Generally used
     * to compute dynamics before letting new control decisions occur
     * @throws MissingComponentException
     */
    public void preClock() throws MissingComponentException;

    /**
     * Take actions for new second Generally used to allow the controller to
     * make new decisions
     */
    public void clock();
}
