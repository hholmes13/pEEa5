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
    
    private ArrayList<Module> subModules;
    
    private static long mUIDSource = 10000;
    
    private long mUID;
    public boolean state;
    public String status;
    
    public Logger logger;
    
    public Module(){
        
    }
    
    /**
     * 
     */
    @Override
    public void clock(){
        
    }
    
    /**
     * 
     */
    @Override
    public void preClock(){
        
    }
}
