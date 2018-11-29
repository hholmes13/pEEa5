/*
 * File: TestP5.java
 * Author: Hunter Holmes hholmes1@uab.edu
 * Assignment:  P5
 * Vers: 1.0.0 11/07/2018 hah - initial coding
 */

/**
 *
 * @author Hunter Holmes hholmes1@uab.edu
 */
public class TestP5 {

    public static void main(String[] args) {
        Controller controller;
        Module m1;
        Module m2;
        Module m3;
        Logger logger;
        Clock clock;

        logger = new PrintLogger(00);
        controller = new Controller(logger);
        m1 = new Module("GRID", 10, 10, logger);
        m2 = new Module("STRING", 10, logger);
        m3 = new Module("GRID", 10, 10, logger);
        clock = new Clock();
        controller.connect(m1, 0, 0);
        m1.connect(m2);
        controller.connect(m3, 0, 1);

        System.out.println("m1 current mode: " + m1.modeString);
        System.out.println("m2 current mode: " + m2.modeString);
        System.out.println("m3 current mode: " + m3.modeString);

        clock.run(controller, "ALL ON");

        System.out.println("m1 current mode: " + m1.modeString);
        System.out.println("m2 current mode: " + m2.modeString);
        System.out.println("m3 current mode: " + m3.modeString);
    }
}
