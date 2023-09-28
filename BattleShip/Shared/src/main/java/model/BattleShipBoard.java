package model;

import Enums.CellStatus;
import Enums.Direction;

import java.util.Random;

public class BattleShipBoard {
    private final CellStatus[][] cells = new CellStatus[10][10];
    private final Ship[] ships = new Ship[10];
    private int count;

    public BattleShipBoard() {
        count = 3;
        randomizeShips();
        putShips();
    }

    public BattleShipBoard(BattleShipBoard board) {
        count = 3;
        hideShips(board.getCells());
    }

    private void hideShips(CellStatus[][] cells){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (cells[i][j] != CellStatus.INTACT) this.cells[i][j] = cells[i][j];
                if (cells[i][j] == CellStatus.INTACT) this.cells[i][j] = CellStatus.EMPTY;
            }
        }
    }

    public void randomizeShips() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cells[i][j] = CellStatus.EMPTY;
            }
        }
        Random random = new Random();
        int type = random.nextInt(6);
        switch (type) {
            case 0 -> {
                ships[0] = new Ship(0, 0, Direction.VERTICAL, 4);
                ships[1] = new Ship(2, 0, Direction.HORIZONTAL, 3);
                ships[2] = new Ship(7, 0, Direction.HORIZONTAL, 3);
                ships[3] = new Ship(4, 4, Direction.HORIZONTAL, 2);
                ships[4] = new Ship(7, 7, Direction.HORIZONTAL, 2);
                ships[5] = new Ship(9, 3, Direction.VERTICAL, 2);
                ships[6] = new Ship(1, 5, Direction.VERTICAL, 1);
                ships[7] = new Ship(2, 7, Direction.VERTICAL, 1);
                ships[8] = new Ship(5, 9, Direction.VERTICAL, 1);
                ships[9] = new Ship(9, 9, Direction.VERTICAL, 1);
            }
            case 1 -> {
                ships[0] = new Ship(0, 0, Direction.HORIZONTAL, 4);
                ships[1] = new Ship(5, 0, Direction.VERTICAL, 3);
                ships[2] = new Ship(7, 0, Direction.VERTICAL, 3);
                ships[3] = new Ship(4, 4, Direction.HORIZONTAL, 2);
                ships[4] = new Ship(7, 7, Direction.HORIZONTAL, 2);
                ships[5] = new Ship(9, 4, Direction.VERTICAL, 2);
                ships[6] = new Ship(1, 5, Direction.VERTICAL, 1);
                ships[7] = new Ship(2, 7, Direction.VERTICAL, 1);
                ships[8] = new Ship(0, 7, Direction.VERTICAL, 1);
                ships[9] = new Ship(0, 9, Direction.VERTICAL, 1);
            }
            case 2 -> {
                ships[0] = new Ship(8, 4, Direction.VERTICAL, 4);
                ships[1] = new Ship(1, 2, Direction.HORIZONTAL, 3);
                ships[2] = new Ship(1, 4, Direction.HORIZONTAL, 3);
                ships[3] = new Ship(7, 0, Direction.HORIZONTAL, 2);
                ships[4] = new Ship(1, 7, Direction.HORIZONTAL, 2);
                ships[5] = new Ship(5, 5, Direction.VERTICAL, 2);
                ships[6] = new Ship(0, 0, Direction.VERTICAL, 1);
                ships[7] = new Ship(2, 0, Direction.VERTICAL, 1);
                ships[8] = new Ship(4, 0, Direction.VERTICAL, 1);
                ships[9] = new Ship(6, 9, Direction.VERTICAL, 1);
            }
            case 3 -> {
                ships[0] = new Ship(5, 7, Direction.HORIZONTAL, 4);
                ships[1] = new Ship(5, 0, Direction.VERTICAL, 3);
                ships[2] = new Ship(7, 0, Direction.VERTICAL, 3);
                ships[3] = new Ship(0, 0, Direction.HORIZONTAL, 2);
                ships[4] = new Ship(0, 3, Direction.HORIZONTAL, 2);
                ships[5] = new Ship(3, 1, Direction.VERTICAL, 2);
                ships[6] = new Ship(1, 5, Direction.VERTICAL, 1);
                ships[7] = new Ship(2, 7, Direction.VERTICAL, 1);
                ships[8] = new Ship(0, 7, Direction.VERTICAL, 1);
                ships[9] = new Ship(0, 9, Direction.VERTICAL, 1);
            }
            case 4 -> {
                ships[0] = new Ship(3, 2, Direction.VERTICAL, 4);
                ships[1] = new Ship(2, 0, Direction.HORIZONTAL, 3);
                ships[2] = new Ship(7, 0, Direction.HORIZONTAL, 3);
                ships[3] = new Ship(5, 4, Direction.HORIZONTAL, 2);
                ships[4] = new Ship(7, 7, Direction.HORIZONTAL, 2);
                ships[5] = new Ship(9, 3, Direction.VERTICAL, 2);
                ships[6] = new Ship(1, 5, Direction.VERTICAL, 1);
                ships[7] = new Ship(2, 8, Direction.VERTICAL, 1);
                ships[8] = new Ship(5, 9, Direction.VERTICAL, 1);
                ships[9] = new Ship(9, 9, Direction.VERTICAL, 1);
            }
            case 5 -> {
                ships[0] = new Ship(0, 9, Direction.HORIZONTAL, 4);
                ships[1] = new Ship(5, 9, Direction.HORIZONTAL, 3);
                ships[2] = new Ship(9, 7, Direction.VERTICAL, 3);
                ships[3] = new Ship(9, 4, Direction.VERTICAL, 2);
                ships[4] = new Ship(9, 1, Direction.VERTICAL, 2);
                ships[5] = new Ship(6, 0, Direction.HORIZONTAL, 2);
                ships[6] = new Ship(1, 0, Direction.VERTICAL, 1);
                ships[7] = new Ship(1, 4, Direction.VERTICAL, 1);
                ships[8] = new Ship(4, 3, Direction.VERTICAL, 1);
                ships[9] = new Ship(4, 6, Direction.VERTICAL, 1);
            }
        }

    }

    public void putShips() {
        for (int i = 0; i < 10; i++) {
            Ship ship = ships[i];
            if (ship.getDirection() == Direction.HORIZONTAL) {
                for (int j = 0; j < ship.getLength(); j++) {
                    if (cells[ship.getI() + j][ship.getJ()] != CellStatus.DESTROYED)
                        cells[ship.getI() + j][ship.getJ()] = CellStatus.INTACT;
                }
            }
            if (ship.getDirection() == Direction.VERTICAL) {
                for (int j = 0; j < ship.getLength(); j++) {
                    if (cells[ship.getI()][ship.getJ() + j] != CellStatus.DESTROYED)
                        cells[ship.getI()][ship.getJ() + j] = CellStatus.INTACT;
                }
            }
        }
    }

    public CellStatus[][] getCells() {
        return cells;
    }

    public boolean destroyed(int i, int j) {
        boolean answer = false;
        if (cells[i][j] == CellStatus.INTACT) {
            answer = true;
            cells[i][j] = CellStatus.HIT;
        }
        if (cells[i][j] == CellStatus.EMPTY) {
            answer = false;
            cells[i][j] = CellStatus.DESTROYED;
        }
        for (int k = 0; k < 10; k++) {
            if (ships[k].destroy(i, j)) destroyAround(ships[k]);
        }
        return answer;
    }

    public void destroyAround(Ship ship) {
        if (ship.getDirection() == Direction.VERTICAL) {
            for (int j = 0; j < ship.getLength(); j++) {
                destroyAround(ship.getI(), ship.getJ() + j);
            }
        }
        if (ship.getDirection() == Direction.HORIZONTAL) {
            for (int j = 0; j < ship.getLength(); j++) {
                destroyAround(ship.getI() + j, ship.getJ());
            }
        }
    }

    public void destroyAround(int i, int j) {
        destroy(i, j + 1);
        destroy(i, j - 1);
        destroy(i + 1, j + 1);
        destroy(i + 1, j);
        destroy(i + 1, j - 1);
        destroy(i - 1, j + 1);
        destroy(i - 1, j);
        destroy(i - 1, j - 1);

    }

    public void destroy(int i, int j) {
        if (0 <= i && i <= 9 && 0 <= j && j <= 9 && cells[i][j] != CellStatus.HIT) cells[i][j] = CellStatus.DESTROYED;
    }

    public Ship[] getShips() {
        return ships;
    }

    public int getCount() {
        return count;
    }

    public void setCount() {
        count--;
    }
}
