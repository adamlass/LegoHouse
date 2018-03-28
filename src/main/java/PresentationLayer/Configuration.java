/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

/**
 *
 * @author adamlass
 */
public class Configuration {
    private int fourTwo, twoTwo, oneTwo;
    private boolean door, window;

    public Configuration(int fourTwo, int twoTwo, int oneTwo, boolean door, boolean window) {
        this.fourTwo = fourTwo;
        this.twoTwo = twoTwo;
        this.oneTwo = oneTwo;
        this.door = door;
        this.window = window;
    }

    public int getFourTwo() {
        return fourTwo;
    }

    public int getTwoTwo() {
        return twoTwo;
    }

    public int getOneTwo() {
        return oneTwo;
    }

    public boolean isDoor() {
        return door;
    }

    public boolean isWindow() {
        return window;
    }

    @Override
    public String toString() {
        return "Configuration{" + "fourTwo=" + fourTwo + ", twoTwo=" + twoTwo + ", oneTwo=" + oneTwo + ", door=" + door + ", window=" + window + '}';
    }
    
    
    
}
