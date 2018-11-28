/*
 * File: Controller.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P5
 * Vers: 1.0.0 11/07/2018 hah - initial coding
 */

import java.util.ArrayList;

/**
 * Modeling Class for a controller that will take desired system behavior and
 * distribute commands to necessary components. Pre-clocking will be used to
 * reduce lag on command distribution
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class Controller implements Clockable {

    private Module module;

    private boolean presentSystemState;

    private Logger logger;

    private Module[] firstModules; //so controller can communicate with its direct modules

    private Module[][] allConnectedModules; //so controller knows whats connected

    public int maxConnections;
    public int maxModuleChainLength;

    /**
     * Create a controller
     * @param logger
     */
    public Controller(Logger logger) {
        this.maxConnections = 4;
        this.maxModuleChainLength = 4;
        this.logger = (logger != null) ? logger : new NullLogger();
        presentSystemState = false;
        firstModules = new Module[maxConnections];
        allConnectedModules = new Module[maxModuleChainLength][maxModuleChainLength];
    }

    /**
     * Provide the string “Controller with TS:{UID} = {temperature} and
     * Module:{UID} = {state}” example:
     * @return formatted string
     */
    @Override
    public String toString() {
        String moduleString = (module == null) ? "no module"
                : module.toString();

        return "Controller with " + moduleString;
    }

    /**
     * Connect a module to the controller. This will connect the first module in
     * a series of modules. Sub modules are connected to these first modules
     *
     * @param module
     */
    public void connect(Module module, int colPos, int rowPos) {
        
        
        this.module = module;
        logger.log(Logger.INFO, "Connect Module " + module);

        firstModules[colPos] = module;
        allConnectedModules[colPos][rowPos] = module;

        module.rowPos = rowPos;
        module.colPos = colPos;
        module.mUID = module.typeInt + (module.colPos * 1000) + (module.rowPos * 100);
    }

    /**
     *
     * @param module
     */
    public void disconnect(Module module, int colPos, int rowPos) {
        this.module = module;
        logger.log(Logger.INFO, "Disconnect Module " + module);

        firstModules[colPos] = null;
        allConnectedModules[colPos][rowPos] = null;
    }

    /**
     *
     */
    @Override
    public void preClock() throws MissingComponentException {

        for (Clockable object : firstModules) {
            logger.log(Logger.INFO, "Preclocking " + object);
            object.preClock();
        }

    }

    /**
     *
     */
    @Override
    public void clock() {
        // Clock
        for (Clockable object : firstModules) {
            logger.log(Logger.INFO, "Clocking " + object);
            object.clock();
        }
    }

}
