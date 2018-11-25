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
    
    private ArrayList<Module> firstModules; 

    /**
     * Create a controller
     * @param logger
     */
    public Controller(Logger logger) {
        this.logger = (logger != null) ? logger : new NullLogger();
        presentSystemState = false;
    }

    /**
     * Provide the string “Controller with TS:{UID} = {temperature} and
     * Heater:{UID} = {state}” example:
     * <code>Contoller with TS:10000 = 75.0 and Heater:20000 = ON</code> Use "no
     * xyz" if there is no corresponding object
     * @return formatted string
     */
    @Override
    public String toString() {
        String moduleString = (module == null) ? "no module"
                : module.toString();

        return "Controller with " + moduleString;
    }
    
    /**
     * Connect a module to the controller. This will connect the first
     * module in a series of modules. Sub modules are connected to these first 
     * modules
     *  
     * @param module 
     */
    public void connect(Module module){
        this.module = module;
        logger.log(Logger.INFO, "Connect Module " + module);
    }

    /**
     *
     */
    @Override
    public void clock() {

    }

    /**
     *
     */
    @Override
    public void preClock() {

    }
}
