/*
 * File: Module.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P5
 * Vers: 1.0.0 11/07/2018 hah - initial coding
 */
import java.util.ArrayList;

/**
 * Modeling light module that can be connected, disconnected, and controlled
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
    public Logger logger;
    public String modeString;

    private int nextMode;
    private int currentMode;

    /**
     * Constructor for GRID Type
     * @param type
     * @param rowDim
     * @param colDim
     * @param logger
     */
    public Module(String type, int rowDim, int colDim, Logger logger) {
        this.logger = logger;
        this.type = type;
        this.modeString = "ALL OFF";

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
        for (int i = 0; i < this.rowDim; i++) {
            for (int j = 0; j < this.colDim; j++) {
                lights[i][j] = new Light();
            }
        }
        this.mUID = 0;
    }

    /**
     * Constructor for STRING Type
     * @param type
     * @param rowDim
     * @param logger
     */
    public Module(String type, int rowDim, Logger logger) {
        this.logger = logger;
        this.type = type;
        this.modeString = "ALL OFF";

        if (this.type == "GRID") {
            this.typeInt = 10000;
        } else if (this.type == "STRING") {
            this.typeInt = 20000;
        } else {
            //Nothing Yet
        }

        this.rowDim = rowDim;
        this.colDim = 1;

        lights = new Light[this.rowDim][this.colDim];
        for (int i = 0; i < this.rowDim; i++) {
            for (int j = 0; j < this.colDim; j++) {
                lights[i][j] = new Light();
            }
        }
    }

    /**
     * Method used to connect subsequent modules, beyond the ones
     * connected to the controller
     * @param subModule
     */
    public void connect(Module subModule) {
        this.subModule = subModule;
        this.subModule.colPos = this.colPos;
        this.subModule.rowPos = this.rowPos + 1;

        if (this.subModule.type == "GRID") {
            this.subModule.typeInt = 10000;
        } else if (this.subModule.type == "STRING") {
            this.subModule.typeInt = 20000;
        } else {
            //Nothing Yet
        }

        this.subModule.mUID = this.subModule.typeInt + ((this.subModule.rowPos + 1) * 1000) + ((this.subModule.colPos + 1) * 100);

        logger.log(Logger.INFO, "Connect Module " + this.subModule.toString() + "to Module " + this.toString());
    }

    /**
     * Allows a module to be removed
     */
    public void disconnect() {
        logger.log(Logger.INFO, "Disconnect Module " + subModule.toString() + "from Controller");
        this.subModule = null;

        

        
    }

    /**
     * Parses and deciphers message from controller
     * @param strBinMessage
     */
    public void readMessage(String strBinMessage) {
        if ("100000".equals(strBinMessage)) {
            this.currentMode = this.nextMode; //this is the take-action or clock message 
            this.takeAction();

            if (this.subModule != null) {
                this.subModule.readMessage(strBinMessage);
            } else {
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

            if (decMessageArray[1] == mUIDArray[0] && decMessageArray[2] == mUIDArray[1] && decMessageArray[3] == mUIDArray[2]) {
                this.nextMode = (Character.getNumericValue(decMessageArray[4])) * 10 + (Character.getNumericValue(decMessageArray[5]));
            } else if (decMessageArray[1] == mUIDArray[0] && decMessageArray[2] == '0' && decMessageArray[3] == '0') {
                this.nextMode = (Character.getNumericValue(decMessageArray[4])) * 10 + (Character.getNumericValue(decMessageArray[5]));

                if (this.subModule != null) {
                    this.subModule.readMessage(strBinMessage);
                } else {
                    //do nothing
                }

            } else if (decMessageArray[1] == '0' && decMessageArray[2] == mUIDArray[1] && decMessageArray[3] == '0') {
                this.nextMode = (Character.getNumericValue(decMessageArray[4])) * 10 + (Character.getNumericValue(decMessageArray[5]));

                if (this.subModule != null) {
                    this.subModule.readMessage(strBinMessage);
                } else {
                    //do nothing
                }

            } else if (decMessageArray[1] == '0' && decMessageArray[2] == '0' && decMessageArray[3] == mUIDArray[1]) {
                this.nextMode = (Character.getNumericValue(decMessageArray[4])) * 10 + (Character.getNumericValue(decMessageArray[5]));

                if (this.subModule != null) {
                    this.subModule.readMessage(strBinMessage);
                } else {
                    //do nothing
                }

            } else if (decMessageArray[1] == '0' && decMessageArray[2] == '0' && decMessageArray[3] == '0') {
                this.nextMode = (Character.getNumericValue(decMessageArray[4])) * 10 + (Character.getNumericValue(decMessageArray[5]));

                if (this.subModule != null) {
                    this.subModule.readMessage(strBinMessage);
                } else {
                    //do nothing
                }

            }
        }
    }

    /**
     * Applies mode when clock signal is sent from controller
     */
    public void takeAction() {
        switch (this.currentMode) {
            case 01:
                this.modeString = "ALL OFF";
                for (int i = 0; i < lights.length - 1; i++) {
                    for (int j = 0; j < lights[i].length - 1; j++) {
                        lights[i][j].state = false;
                    }
                }
                break;
            case 02:
                this.modeString = "ALL ON";
                for (int i = 0; i < lights.length - 1; i++) {
                    for (int j = 0; j <= lights[i].length - 1; j++) {
                        lights[i][j].state = true;
                    }
                }
                break;
            case 03:
                //
                break;
            default:
                break;
        }
    }

    /**
     * returns formatted module string
     */
    @Override
    public String toString() {
        return ("with ID " + Integer.toString(this.mUID) + " ");
    }
}
