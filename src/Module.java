/*
 * File: Module.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P5
 * Vers: 1.0.0 11/07/2018 hah - initial coding
 */
import java.util.ArrayList;

/**
 *
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class Module implements Clockable {

    private Module subModule;
    private ArrayList<Light> lights;

    public int mUID;
    public String type;
    public int typeInt;
    public int rowDim;
    public int colDim;
    public int rowPos;
    public int colPos;

    public boolean state;
    public String status;
    public ModMode modMode;

    public Logger logger;

    /**
     *
     * @param type
     * @param rowDim
     * @param colDim
     * @param logger
     */
    public Module(String type, int rowDim, int colDim, Logger logger) {
        this.state = false;
        this.status = "OFF";
        this.logger = logger;
        this.type = type;

        if (this.type == "GRID") {
            this.typeInt = 10000;
        } else if (this.type == "STRING") {
            this.typeInt = 20000;
        } else {
            //Nothing Yet
        }

        this.rowDim = rowDim;
        this.colDim = colDim;
    }

    /**
     *
     * @param type
     * @param rowDim
     * @param logger
     */
    public Module(String type, int rowDim, Logger logger) {
        this.state = false;
        this.status = "OFF";
        this.logger = logger;
        this.type = type;

        if (this.type == "GRID") {
            this.typeInt = 10000;
        } else if (this.type == "STRING") {
            this.typeInt = 20000;
        } else {
            //Nothing Yet
        }

        this.rowDim = rowDim;
    }

    /**
     *
     * @param subModule
     */
    public void connect(Module subModule) {
        this.subModule = subModule;
    }

    /**
     *
     */
    @Override
    public void preClock() {

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
    public String toString() {
        if (state == true) {
            this.status = "ON";
            this.state = true;
        } else {
            this.status = "OFF";
            this.state = false;
        }
        return ("Heater:" + this.mUID + " = " + this.status);
    }
}
