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
     * 
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
     * @param rowPos
     * @param colPos 
     */
    public void connect(Module module, int rowPos, int colPos) {

        this.module = module;
        

        firstModules[colPos] = module;
        allConnectedModules[rowPos][colPos] = module;

        module.rowPos = rowPos;
        module.colPos = colPos;
        module.mUID = this.module.typeInt + ((this.module.rowPos + 1 ) * 1000) + ((this.module.colPos + 1) * 100);
        
        logger.log(Logger.INFO, "Connect Module " + module.toString() + "to Controller");
    }

    /**
     *
     * @param module
     */
    public void disconnect(Module module, int rowPos, int colPos) {
        this.module = module;

        firstModules[colPos] = null;
        allConnectedModules[rowPos][colPos] = null;
        
        logger.log(Logger.INFO, "Disconnect Module " + module.toString() + "from Controller");
    }

    /**
     *
     * @param mode
     */
    public void preClock(String mode) {
        this.mode = mode;
        int intMessage;
        String strBinMessage;

        switch (mode) {
            case "ALL OFF":
                intMessage = 100001;
                strBinMessage = Integer.toBinaryString(intMessage);

                for (int i = 0; i < this.firstModules.length - 1; i++) {
                    if (this.firstModules[i] != null) {
                        this.firstModules[i].readMessage(strBinMessage);
                    } else {
                        break;
                    }
                }

                break;
            case "ALL ON":
                intMessage = 100002;
                strBinMessage = Integer.toBinaryString(intMessage);

                for (int i = 0; i < this.firstModules.length - 1; i++) {
                    if (this.firstModules[i] != null) {
                        this.firstModules[i].readMessage(strBinMessage);
                    } else {
                        break;
                    }
                }
                break;
            case "CHECKERBOARD":
                //nope
                break;
            default:
                //
                break;
        }

    }

    /**
     *
     */
    public void clock() {
        for (int i = 0; i < firstModules.length - 1; i++) {
            if (firstModules[i] != null) {
                firstModules[i].readMessage("100000");
            } else {

            }

        }

    }
}
