package org.example;
import java.util.Arrays;

public class Tiles {

    public static void main(String[] args) {
        Tiles tiles = new Tiles();
        tiles.createTiles(9,11);
        System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
    }

    String tiles = "Tile";

    //+1 for coordinates
    String[][] grid = new String[9][11];

    //Create grid of tiles
    public String[][] createTiles(int maxX, int maxY) {
        this.grid = new String[maxX][maxY];
        for(int i = 1; i < this.grid.length; i++) {
            for(int j = 1; j < this.grid[i].length; j++) {
                this.grid[i][j] = this.tiles;
                //System.out.println(this.grid[i][j]);
            }
        }

        //Set top left corner to empty (not apart of grid)
        this.grid[0][0] = "Empty";

        //Change top row from numbers 1 - 10
        for(int i = 1; i < this.grid[0].length; i++) {
            this.grid[0][i] = String.valueOf(i);
            //this.grid[0][i] = String.format("%4d", i);
        }

        //Change first column from numbers 1-8
        for(int j = 1; j < this.grid.length; j++) {
            this.grid[j][0] = String.valueOf(j);
            //this.grid[j][0] = String.format("%6d", j);
        }

        return this.grid;
    }
}


//System.out.println(this.grid);
//        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
