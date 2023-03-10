package gol;

import java.util.Arrays;

public class Grid {

    private char[][] grid;
    private int gridWidth;
    private int gridHeight;
    private int startX;
    private int startY;

    // constructor to create empty grid
    public Grid(int gridWidth,int gridHeight,int startX, int startY) {

        this.grid = new char[gridHeight][gridWidth];
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.startX = startX;
        this.startY = startY;

    }

    // constructor to make a new grid copy
    public Grid(int gridWidth,int gridHeight,int startX, int startY, char[][] grid) {

        this.grid = grid;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.startX = startX;
        this.startY = startY;

    }

    // getter
    public char[][] getGrid() {
        return grid;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    // initialize grid
    public void initializeGrid(int row,char[] data) {

        // System.out.printf("dataRow is %d\n",row); // testing
        // System.out.printf("row + startY is %d\n",row + startY); // testing

        for (int i=0; i < data.length; i++) {
            this.grid[row + startY][i + startX] = data[i];
            // System.out.printf("now setting array row [%d] col [%d] with %c\n", row + startY, i + startX,data[i]); //testing
        }

    }

    // set Grid
    public void setGrid(int y, int x, char value) {

        // System.out.printf("setting grid[%d][%d] as value[%c]\n",y,x,value); // testing
        this.grid[y][x] = value;     
    }



    // print grid status
    public void printGrid() {

        for (int i = 0; i < gridHeight; i++) {

            System.out.println(Arrays.toString(grid[i]));

        }
        System.out.print('\n');

    }


    
}
