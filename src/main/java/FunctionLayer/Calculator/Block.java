/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculator;

import java.util.ArrayList;

/**
 *
 * @author adamlass
 */
public class Block {

    private ArrayList<Position> positions;
    private boolean reserved;
    private int id, length;

    public Block(ArrayList<Position> positions, boolean reserved, int id, int length) {
        this.positions = positions;
        this.reserved = reserved;
        this.id = id;
        this.length = length;

    }

    @Override
    public String toString() {
        return "\n***************\nBlock\n" + "positions:\n" + positions + 
                "\n\nreserved:\n" + reserved + "\n\nid:\n"
                + id + "\n\nlength:\n" + length + "\n\n";
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public boolean isReserved() {
        return reserved;
    }

    public int getId() {
        return id;
    }

    public int length() {
        return length;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
    
    
}
