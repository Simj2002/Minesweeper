package org.example;
import java.util.Arrays;

public class Tiles {

    public static void main(String[] args) {
        Tiles tiles = new Tiles();
        tiles.createTiles();
        System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
    };

    String tiles = "Tile";

    String[][] grid = new String[9][11];

    public String[][] createTiles() {
        for(int i = 1; i < this.grid.length; i++) {
            for(int j = 1; j < this.grid[i].length; j++) {
                this.grid[i][j] = this.tiles;
                //System.out.println(this.grid[i][j]);
            }
        }

//        for(int i = 0; i < 1; i++) {
//            for(int j = 0; j < this.grid[i].length; j++) {
//                this.grid[i][j] = String.valueOf(j + 1);
//            }
//        }
        this.grid[0][0] = "Empty";

        // Changes top row to numbers 1 - 18
        for(int i = 1; i < this.grid[0].length; i++) {
            this.grid[0][i] = String.valueOf(i);
            //this.grid[0][i] = String.format("%4d", i);
        }

        for(int j = 1; j < this.grid.length; j++) {
            this.grid[j][0] = String.valueOf(j);
            //this.grid[j][0] = String.format("%6d", j);
        }

        //System.out.println(this.grid);
//        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
        return this.grid;
    }

}
