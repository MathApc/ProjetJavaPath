package board;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int BOARD_SIZE = 81;
	private int size;
    private Box[][] boxes;

    public Board(int size) {
        this.size = size;
        this.boxes = new Box[size][size];
        initializeBoxes();
    }

    private void initializeBoxes() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                boxes[row][column] = new Box(row, column);
            }
        }
    }

    public Box getBox(int row, int column) {
        return boxes[row][column];
    }

    public List<Box> getAllBoxes() {
        List<Box> allBoxes = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                allBoxes.add(boxes[row][column]);
            }
        }
        return allBoxes;
    }

    public List<Box> getNeighboringBoxes(Box box) {
        List<Box> neighbors = new ArrayList<>();
        int row = box.getX();
        int column = box.getY();

        // Check top neighbor
        if (row > 0) {
            neighbors.add(boxes[row - 1][column]);
        }

        // Check bottom neighbor
        if (row < size - 1) {
            neighbors.add(boxes[row + 1][column]);
        }

        // Check left neighbor
        if (column > 0) {
            neighbors.add(boxes[row][column - 1]);
        }

        // Check right neighbor
        if (column < size - 1) {
            neighbors.add(boxes[row][column + 1]);
        }

        return neighbors;
    }

    public void placePawn(Box box, Pawn pawn) {
        box.setPawn(pawn);
        box.setPawn(true);
    }

    public void removePawn(Box box) {
        box.setPawn(null);
        box.setPawn(false);
    }

    public void placeBarrier(Box box1, Box box2) {
        // Assuming box1 and box2 are adjacent and form a valid barrier
        int row1 = box1.getX();
        int column1 = box1.getY();
        int row2 = box2.getX();
        int column2 = box2.getY();

        if (row1 == row2) {
            if (column1 < column2) {
                box1.setRight();
                box2.setLeft();
            } else {
                box1.setLeft();
                box2.setRight();
            }
        } else {
            if (row1 < row2) {
                box1.setBot();
                box2.setTop();
            } else {
                box1.setTop();
                box2.setBot();
            }
        }
    }

    public void removeBarrier(Box box1, Box box2) {
        // Assuming box1 and box2 are adjacent and form a valid barrier
        int row1 = box1.getX();
        int column1 = box1.getY();
        int row2 = box2.getX();
        int column2 = box2.getY();
        if (row1 == row2) {
            if (column1 < column2) {
                box1.setRight();
                box2.setLeft();
            } else {
                box1.setLeft();
                box2.setRight();
            }
        } else {
            if (row1 < row2) {
                box1.setBot();
                box2.setTop();
            } else {
                box1.setTop();
                box2.setBot();
            }
        }
    }
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }
    

}
  