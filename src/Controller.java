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
public class Controller {

    private Module module;
    private boolean presentSystemState;
    private Logger logger;
    private Module[] firstModules; //so controller can communicate with its direct modules
    private Module[][] allConnectedModules; //so controller knows whats connected
    public int maxConnections;
    public int maxModuleChainLength;
    public String mode;

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
    public void connect(Module module, int rowPos, int colPos) {

        this.module = module;
        logger.log(Logger.INFO, "Connect Module " + module);

        firstModules[colPos] = module;
        allConnectedModules[rowPos][colPos] = module;

        module.rowPos = rowPos;
        module.colPos = colPos;
        module.mUID = module.typeInt + (module.rowPos * 1000) + (module.colPos * 100);
    }

    /**
     *
     * @param module
     */
    public void disconnect(Module module, int rowPos, int colPos) {
        this.module = module;
        logger.log(Logger.INFO, "Disconnect Module " + module);

        firstModules[colPos] = null;
        allConnectedModules[rowPos][colPos] = null;
    }

    /**
     *
     * @param mode
     */
    public void preClock(String mode) {
        this.mode = mode;

    }

    /**
     *
     */
    public void clock() {
        for (int i = 0; i < firstModules.length - 1; i++) {

            firstModules[i].readMessage("00000");
        }

    }
}
