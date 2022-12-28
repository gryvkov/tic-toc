package org.madbunny.tictoc;


import java.util.ArrayList;
import java.util.Iterator;

public class Desk {
    private static final ArrayList<Cell> cells = new ArrayList<>();

    private boolean turn = false;

    private int[][] sequences = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    public Desk() {
        cells.add(0, new Cell(0, 0));
        cells.add(1, new Cell(0, 1));
        cells.add(2, new Cell(0, 2));
        cells.add(3, new Cell(1, 0));
        cells.add(4, new Cell(1, 1));
        cells.add(5, new Cell(1, 2));
        cells.add(6, new Cell(2, 0));
        cells.add(7, new Cell(2, 1));
        cells.add(8, new Cell(2, 2));
    }

    public Iterator<Cell> getCells() {
        return cells.iterator();
    }

    public void fillCell(int x, int y) {
        for (Cell c : cells) {
            if (c.x == x && c.y == y && c.state == Cell.Value.EMPTY) {
                if (turn) {
                    c.state = Cell.Value.ZERO;
                    turn = false;
                } else {
                    c.state = Cell.Value.CROSS;
                    turn = true;
                }

            }
        }
    }


    public void clean() {
        for (Cell c : cells) {
            c.state = Cell.Value.EMPTY;
        }
    }

    public int[] findWinner() {
        int zero = 0;
        int cross = 0;

        for (int[] sq : sequences) {
            for (int i : sq) {
                Cell c = cells.get(i);
                if (c.state == Cell.Value.CROSS) {
                    cross++;
                } else if (c.state == Cell.Value.ZERO) {
                    zero++;
                }
            }
            if (zero == 3 || cross == 3) {
                return sq;
            } else {
                zero = 0;
                cross = 0;
            }
        }

        return null;
    }

}


class Cell {
    public final int x;
    public final int y;
    public Value state;

    public enum Value {EMPTY, CROSS, ZERO}

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.state = Value.EMPTY;
    }

}

