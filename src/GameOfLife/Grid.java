package GameOfLife;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
    //    public void addLine
    //    parameters of animation
    private int rows;
    private int cols;
    //    length of every single unit
    private int resolution;
    private int width;
    private int height;
    private int generation = 1;
    private Unit[][] units;

    public Grid(int width, int height, int resolution) {
        this.height = height;
        this.width = width;
        rows = height / resolution;
        cols = width / resolution;
        //        cols corresponds to x; rows corresponds to y
        units = new Unit[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                units[i][j] = new Unit(0, i * resolution, j * resolution);
            }
        }
        Random random = new Random();
        for (int i = 0; i < cols - 1; i++) {
            for (int j = 0; j < rows - 1; j++) {
                int r = random.nextInt(100);
                if (r > 100 * 0.85 - 1) units[i][j] = new Unit(1, i * resolution, j * resolution);
                else units[i][j] = new Unit(0, i * resolution, j * resolution);
            }
        }
    }

    public void addGosperGliderGun(int x, int y) {

        units[x + 5][y + 1] = new Unit(1, (x + 5) * resolution, (y + 1) * resolution);
        units[x + 5][y + 2] = new Unit(1, (x + 5) * resolution, (y + 2) * resolution);
        units[x + 6][y + 1] = new Unit(1, (x + 6) * resolution, (y + 1) * resolution);
        units[x + 6][y + 2] = new Unit(1, (x + 6) * resolution, (y + 2) * resolution);

        units[x + 3][y + 13] = new Unit(1, (x + 3) * resolution, (y + 13) * resolution);
        units[x + 3][y + 14] = new Unit(1, (x + 3) * resolution, (y + 14) * resolution);
        units[x + 4][y + 12] = new Unit(1, (x + 4) * resolution, (y + 12) * resolution);
        units[x + 4][y + 16] = new Unit(1, (x + 4) * resolution, (y + 16) * resolution);

        units[x + 5][y + 11] = new Unit(1, (x + 5) * resolution, (y + 11) * resolution);
        units[x + 5][y + 17] = new Unit(1, (x + 5) * resolution, (y + 17) * resolution);

        units[x + 6][y + 11] = new Unit(1, (x + 6) * resolution, (y + 11) * resolution);
        units[x + 6][y + 15] = new Unit(1, (x + 6) * resolution, (y + 15) * resolution);
        units[x + 6][y + 17] = new Unit(1, (x + 6) * resolution, (y + 17) * resolution);
        units[x + 6][y + 18] = new Unit(1, (x + 6) * resolution, (y + 18) * resolution);
        units[x + 7][y + 11] = new Unit(1, (x + 7) * resolution, (y + 11) * resolution);
        units[x + 7][y + 17] = new Unit(1, (x + 7) * resolution, (y + 17) * resolution);

        units[x + 8][y + 12] = new Unit(1, (x + 8) * resolution, (y + 12) * resolution);
        units[x + 8][y + 16] = new Unit(1, (x + 8) * resolution, (y + 16) * resolution);

        units[x + 9][y + 13] = new Unit(1, (x + 9) * resolution, (y + 13) * resolution);
        units[x + 9][y + 14] = new Unit(1, (x + 9) * resolution, (y + 14) * resolution);

        units[x + 1][y + 25] = new Unit(1, (x + 1) * resolution, (y + 25) * resolution);
        units[x + 2][y + 23] = new Unit(1, (x + 2) * resolution, (y + 23) * resolution);
        units[x + 2][y + 25] = new Unit(1, (x + 2) * resolution, (y + 25) * resolution);
        units[x + 3][y + 21] = new Unit(1, (x + 3) * resolution, (y + 21) * resolution);
        units[x + 3][y + 22] = new Unit(1, (x + 3) * resolution, (y + 22) * resolution);
        units[x + 4][y + 21] = new Unit(1, (x + 4) * resolution, (y + 21) * resolution);
        units[x + 4][y + 22] = new Unit(1, (x + 4) * resolution, (y + 22) * resolution);
        units[x + 5][y + 21] = new Unit(1, (x + 5) * resolution, (y + 21) * resolution);
        units[x + 5][y + 22] = new Unit(1, (x + 5) * resolution, (y + 22) * resolution);
        units[x + 6][y + 23] = new Unit(1, (x + 6) * resolution, (y + 23) * resolution);
        units[x + 6][y + 25] = new Unit(1, (x + 6) * resolution, (y + 25) * resolution);
        units[x + 7][y + 25] = new Unit(1, (x + 7) * resolution, (y + 25) * resolution);
        units[x + 3][y + 35] = new Unit(1, (x + 3) * resolution, (y + 35) * resolution);
        units[x + 3][y + 36] = new Unit(1, (x + 3) * resolution, (y + 36) * resolution);
        units[x + 4][y + 35] = new Unit(1, (x + 4) * resolution, (y + 35) * resolution);
        units[x + 4][y + 36] = new Unit(1, (x + 4) * resolution, (y + 36) * resolution);
    }

    //    add a glider pattern
    public void addGlider(int x, int y) {
        units[x][y] = new Unit(0, x * resolution, y * resolution);
        units[x + 1][y] = new Unit(0, (x + 1) * resolution, y * resolution);
        units[x + 2][y] = new Unit(1, (x + 2) * resolution, y * resolution);
        units[x][y + 1] = new Unit(1, x * resolution, (y + 1) * resolution);
        units[x + 1][y + 1] = new Unit(0, (x + 1) * resolution, (y + 1) * resolution);
        units[x + 2][y + 1] = new Unit(1, (x + 2) * resolution, (y + 1) * resolution);
        units[x][y + 2] = new Unit(0, x * resolution, (y + 2) * resolution);
        units[x + 1][y + 2] = new Unit(1, (x + 1) * resolution, (y + 2) * resolution);
        units[x + 2][y + 2] = new Unit(1, (x + 2) * resolution, (y + 2) * resolution);
    }

    public void addPatternFromFile(int x, int y, String path) throws IOException {
        PatternReader p = new PatternReader(path);
        ArrayList<String> list = p.getList();
        for (int i = 0; i < list.size(); i++) {
            String[] line = list.get(i).split("");
            for (int j = 0; j < line.length; j++) {
                if (line[j].equals("O"))
                    units[x + j][y + i] = new Unit(1, (x + j) * resolution, (y + i) * resolution);
            }
        }
    }

    public void randomInitialSet(double p) {
        deadInitialSet();
        Random random = new Random();
        for (int i = 0; i < cols - 1; i++) {
            for (int j = 0; j < rows - 1; j++) {
                int r = random.nextInt(100);
                if (r > 100 * p - 1) units[i][j] = new Unit(1, i * resolution, j * resolution);
                else units[i][j] = new Unit(0, i * resolution, j * resolution);
            }
        }
        generation = 1;
    }

    public void deadInitialSet() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                units[i][j] = new Unit(0, i * resolution, j * resolution);
            }
        }
        generation = 1;
    }

    public int countNeighbours(int i0, int j0) {
        //        compute the live neighbours around i0,j0
        int neighbours = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                //                deal with the edges
                int index1 = (i0 + i + cols) % cols;
                int index2 = (j0 + j + rows) % rows;
                neighbours += units[index1][index2].getState();
            }
        }
        //        subtract its own contribution
        neighbours -= units[i0][j0].getState();
        return neighbours;
    }

    public void setNextStates() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int neighbours = countNeighbours(i, j);
                //                implements the rule
                if (units[i][j].getState() == 1 && (neighbours < 2 || neighbours > 3))
                    units[i][j].setNextState(0);
                else if (units[i][j].getState() == 0 && neighbours == 3)
                    units[i][j].setNextState(1);
                else units[i][j].setNextState(units[i][j].getState());
            }
        }
    }

    public void evolutions() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                units[i][j].evolution();
            }
        }
        generation++;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getGeneration() {
        return generation;
    }

    public Unit[][] getUnits() {
        return units;
    }
}
