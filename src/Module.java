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
public class Module {

    private Module subModule;
    private Light[][] lights;

    public int mUID;
    public String type;
    public int typeInt;
    public int rowDim;
    public int colDim;
    public int rowPos;
    public int colPos;
    public boolean state;
    public String status;
    public Logger logger;

    private int nextMode;
    private int currentMode;

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

        lights = new Light[this.rowDim][this.colDim];

        this.mUID = 0;
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
        this.colDim = 1;
    }

    /**
     *
     * @param subModule
     */
    public void connect(Module subModule) {
        this.subModule = subModule;
    }

    public void readMessage(String strBinMessage) {
        if (strBinMessage == "00000") {
            this.currentMode = this.nextMode; //this is the take-action or clock message 
            
            if(this.subModule != null){
                this.subModule.readMessage(strBinMessage);
            }else{
            //do nothing
            }
            
            return;
        } else {

            int intDecMessage;
            String strDecMessage;
            char[] decMessageArray;
            String strmUID;
            char[] mUIDArray;

            intDecMessage = Integer.parseInt(strBinMessage, 2);
            strDecMessage = Integer.toString(intDecMessage);
            decMessageArray = strDecMessage.toCharArray();

            strmUID = Integer.toString(this.mUID);
            mUIDArray = strmUID.toCharArray();

            if (decMessageArray[0] == mUIDArray[0] && decMessageArray[1] == mUIDArray[1] && decMessageArray[2] == mUIDArray[2]) {
                this.nextMode = (Character.getNumericValue(decMessageArray[3])) * 10 + (Character.getNumericValue(decMessageArray[4]));
            } else if (decMessageArray[0] == mUIDArray[0] && decMessageArray[1] == '0' && decMessageArray[2] == '0') {
                this.nextMode = (Character.getNumericValue(decMessageArray[3])) * 10 + (Character.getNumericValue(decMessageArray[4]));
                this.subModule.readMessage(strBinMessage);
            } else if (decMessageArray[0] == '0' && decMessageArray[1] == mUIDArray[1] && decMessageArray[2] == '0') {
                this.nextMode = (Character.getNumericValue(decMessageArray[3])) * 10 + (Character.getNumericValue(decMessageArray[4]));
                this.subModule.readMessage(strBinMessage);
            } else if (decMessageArray[0] == '0' && decMessageArray[1] == '0' && decMessageArray[2] == mUIDArray[2]) {
                this.nextMode = (Character.getNumericValue(decMessageArray[3])) * 10 + (Character.getNumericValue(decMessageArray[4]));
                this.subModule.readMessage(strBinMessage);
            } else if (decMessageArray[0] == '0' && decMessageArray[1] == '0' && decMessageArray[2] == '0') {
                this.nextMode = (Character.getNumericValue(decMessageArray[3])) * 10 + (Character.getNumericValue(decMessageArray[4]));
                this.subModule.readMessage(strBinMessage);
            }
        }
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
