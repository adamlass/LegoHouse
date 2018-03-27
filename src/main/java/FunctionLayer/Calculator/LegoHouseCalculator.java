/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.Calculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author adamlass
 */
public class LegoHouseCalculator {

    //*****************Main*********************************************
    public static void main(String[] args) {
        LegoHouseCalculator sand = new LegoHouseCalculator(6, 6, 8, true, true);
        sand.build(new Position(0, 0));
//        System.out.println(sand.blocks);

        for (Block bl : sand.blocks) {
            System.out.println("*************");
            System.out.println(bl.length());

        }
    }

    //******************************************************************
    private int curHeight, height, x, y, blockId;
    private List<Block> blocks;
    private List<Block> curBlocks;
    private List<Position> edges;
    private List<Position> edges2;
    private int rotation;
    private Position rotatedPos;
    private boolean door, window;

    public LegoHouseCalculator(int height, int x, int y, boolean door, boolean window) {
        this.curHeight = 1;
        this.height = height;
        this.x = x;
        this.y = y;
        this.blocks = new ArrayList<>();
        this.curBlocks = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.edges2 = new ArrayList<>();
        this.rotation = 0;
        this.rotatedPos = null;
        this.blockId = 1;
        this.door = door;
        this.window = window;
    }

    public void build(Position pos) {
        if (curHeight > height) {
            return;
        }
        
        if(blocks.isEmpty()){
            addDoorsAndWindows();
        }

        if (!inGrid(pos)) {
            build(rotate(pos));
            return;
        }

        if (used(pos)) {
            if (reserved(pos)) {
                build(move(pos, 1));
            } else {
                curHeight++;
                rotation = 0;
                edges2.clear();

                genEdges(curBlocks);
                curBlocks.clear();

                //add window and door blocks here
                addDoorsAndWindows();

                build(new Position(0, 0));
            }
            return;
        }

        Block bestPlacement = bestPlacement(pos);
        blocks.add(bestPlacement);
        curBlocks.add(bestPlacement);

        Position nextPos = move(pos, bestPlacement.length());

        if (rotatedPos != null) {
            nextPos = rotatedPos;
        }

        build(nextPos);

    }

    private Block bestPlacement(Position pos) {
        ArrayList<Block> candidates = new ArrayList<>();
        Block cornor = null;
        Block startFour = null;

        Position pos3 = move(pos, 3);
        Position pos1 = move(pos, 1);
        Position pos0 = move(pos, 0);

        if (inGrid(pos3) && !used(pos3)) {
            candidates.add(genBlock(pos, 4));
        }

        if (inGrid(pos1) && !used(pos1)) {
            candidates.add(genBlock(pos, 2));

            cornor = cornorTest(pos);
            if (cornor != null) {
                candidates.add(cornor);

            }

            startFour = startTest(pos);
            if (startFour != null) {
                candidates.add(startFour);
            }
        }

        if (inGrid(pos0) && !used(pos0)) {
            candidates.add(genBlock(pos, 1));
        }

        while (candidates.size() != 1) {
            Block cand1 = candidates.get(0);
            Block cand2 = candidates.get(1);

            int cand1edges = edgeCount(edges(cand1.getPositions()));
            int cand2edges = edgeCount(edges(cand2.getPositions()));

            //Does both have the same numbers of edges?
            if (cand1edges == cand2edges) {
                //yes
                //Choose th biggest one
                if (cand1.length() > cand2.length()) {
                    candidates.remove(cand2);
                } else {
                    candidates.remove(cand1);
                }
            } else {
                //no
                //remove the one with the most edges.
                if (cand1edges > cand2edges) {
                    candidates.remove(cand1);
                } else {
                    candidates.remove(cand2);
                }
            }
        }

        Block chosen = candidates.get(0);
        if (chosen == cornor) {
            Position posBefore = move(pos, 1);
            rotate(pos);
            rotatedPos = move(posBefore, 4);
        } else {
            rotatedPos = null;
        }

        if (chosen == startFour) {
            rotatedPos = move(pos, 2);
        }

        chosen.setId(blockId++);
        return candidates.get(0);
    }

    private Block cornorTest(Position pos) {
        Block res = null;
        if (inGrid(move(pos, -1)) && !inGrid(move(pos, 2))) {

            Position movedPos = move(pos, 1);
            int originalRotation = rotation;
            rotate(pos);
            res = genBlock(movedPos, 4);

            for (Position curPos : res.getPositions()) {
                if (!inGrid(curPos) || used(curPos)) {
                    res = null;
                }
            }

            this.rotation = originalRotation;
        }
        return res;
    }

    private Block startTest(Position pos) {
        Block res = null;
        if (!inGrid(move(pos, -1)) && inGrid(move(pos, 2))) {

            Position movedPos = move(pos, 1);
            int originalRotation = rotation;
            rotate(pos);
            res = genBlock(movedPos, 4);

            for (Position curPos : res.getPositions()) {
                if (!inGrid(curPos) || used(curPos)) {
                    res = null;
                }
            }

            this.rotation = originalRotation;
        }
        return res;
    }

    private Block genBlock(Position pos, int length) {
        Block res = null;
        ArrayList<Position> tempList = new ArrayList<>();
        for (int j = 0; j < length; j++) {
            Position current = move(pos, j);

            tempList.add(current);
            tempList.add(neighbor(current));
        }
        return new Block(tempList, false, 0, length);
    }

    private boolean inGrid(Position pos) {
        //TODO 0-index might have trouble here?
        if (pos.getX() < x && pos.getY() < y) {
            if (pos.getX() >= 0 && pos.getY() >= 0) {
                return true;
            }
        }
        return false;
    }

    private boolean used(Position pos) {
        boolean res = false;
        if (!curBlocks.isEmpty()) {
            for (Block block : curBlocks) {
                ArrayList<Position> positions = block.getPositions();
                for (Position curPos : positions) {
                    if (curPos.equals(pos)) {
                        res = true;
                    }
                }
            }
        }
        return res;
    }

    private Position rotate(Position pos) {
        Position res = null;

        //finding the post-rotation position
        switch (rotation) {
            case 0:
                res = new Position(pos.getX() + 2, pos.getY() - 1);
                break;
            case 1:
                res = new Position(pos.getX() - 1, pos.getY() - 2);
                break;
            case 2:
                res = new Position(pos.getX() - 2, pos.getY() + 1);
                break;
            case 3:
                res = new Position(pos.getX() + 1, pos.getY() + 2);
                break;

        }

        //changing to the next rotation value
        rotation = (rotation + 1) % 4;

        return res;
    }

    private boolean reserved(Position pos) {

        for (Block block : curBlocks) {
            if (block.isReserved()) {
                for (Position curPos : block.getPositions()) {
                    if (pos.equals(curPos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Position move(Position pos, int i) {
        Position res = null;
        switch (rotation) {
            case 0:
                res = new Position(pos.getX(), pos.getY() + i);
                break;
            case 1:
                res = new Position(pos.getX() + i, pos.getY());
                break;
            case 2:
                res = new Position(pos.getX(), pos.getY() - i);
                break;
            case 3:
                res = new Position(pos.getX() - i, pos.getY());
                break;
        }
        return res;
    }

    private Position neighbor(Position pos) {
        Position res = null;
        switch (rotation) {
            case 0:
                res = new Position(pos.getX() + 1, pos.getY());
                break;
            case 1:
                res = new Position(pos.getX(), pos.getY() - 1);
                break;
            case 2:
                res = new Position(pos.getX() - 1, pos.getY());
                break;
            case 3:
                res = new Position(pos.getX(), pos.getY() + 1);
                break;
        }
        return res;
    }

    private ArrayList<Position> edges(ArrayList<Position> positions) {
        ArrayList<Position> res = new ArrayList<>();
        res.add(positions.get(0));
        res.add(positions.get(1));
        res.add(positions.get(positions.size() - 1));
        res.add(positions.get(positions.size() - 2));
        return res;
    }

//    private void genEdges(List<Block> curBlocks) {
//        for (Block block : curBlocks) {
//            edges.addAll(edges(block.getPositions()));
//        }
//    }
    private void genEdges(List<Block> curBlocks) {
        int[][] indexArray = genArray(curBlocks);

        for (int i = 0; i < x; i++) {
            int last = 0;
            for (int j = 0; j < y; j++) {
                int current = indexArray[i][j];

                if (last > 0 && current > 0) {
                    if (current != last) {
                        edges2.add(new Position(i, j));
                        edges2.add(new Position(i, j - 1));
                    }
                }
                last = current;
            }
        }

        for (int i = 0; i < y; i++) {
            int last = 0;
            for (int j = 0; j < x; j++) {
                int current = indexArray[j][i];

                if (last > 0 && current > 0) {
                    if (current != last) {
                        edges2.add(new Position(j, i));
                        edges2.add(new Position(j - 1, i));
                    }
                }
                last = current;
            }
        }
    }

    private int edgeCount(ArrayList<Position> posList) {
        int edgeCount = 0;
        for (Position pos : posList) {
            if (edges2.contains(pos)) {
                edgeCount++;
            }
        }
        return edgeCount;
    }

    private int[][] genArray(List<Block> curBlocks) {
        int[][] res = new int[this.x][this.y];

        for (Block block : curBlocks) {
            for (Position pos : block.getPositions()) {
                res[pos.getX()][pos.getY()] = block.getId();
            }
        }
        return res;
    }

    private List<Block> doorsWindows() {
        List<Block> res = new ArrayList<>();

        //if we should add a door block
        if (this.door) {
            if (curHeight >= 1 && curHeight <= 5) {
                Block doorPart = genBlock(new Position(0, 2), 4);
                doorPart.setReserved(true);
                res.add(doorPart);
            }
        }

        //if we should add a window block
        if (this.window) {
            if (curHeight >= 4 && curHeight <= 5) {
                this.rotation = 1;
                
                Block windowPart = genBlock(new Position(2, 1), 2);
                windowPart.setReserved(true);
                res.add(windowPart);
                
                this.rotation = 0;
            }
        }

        return res;
    }

    private void addDoorsAndWindows() {
        List<Block> blocks = doorsWindows();
        curBlocks.addAll(blocks);
        for(Block block : blocks){
            block.setId(blockId++);
        }
        this.blocks.addAll(blocks);
    }

}
