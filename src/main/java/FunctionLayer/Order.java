/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 * Remember to also set id on the order.
 * @author adamlass
 */
public class Order {
    private int id, length, width, height;
    private User owner;
    private boolean window, door, sent;

    public Order(int length, int width, int height, User owner, boolean window, boolean door, boolean sent) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.owner = owner;
        this.window = window;
        this.door = door;
        this.sent = sent;
    }

    public int getId() {
        return id;
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

    public User getOwner() {
        return owner;
    }

    public boolean isWindow() {
        return window;
    }

    public boolean isDoor() {
        return door;
    }

    public boolean isSent() {
        return sent;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
