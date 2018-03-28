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
public class Specification {
    private int length, width, height;
    private boolean door, window;

    public Specification(int length, int width, int height, boolean door, boolean window) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.door = door;
        this.window = window;
    }
    

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDoor() {
        return door;
    }

    public boolean isWindow() {
        return window;
    }
    
    
}
