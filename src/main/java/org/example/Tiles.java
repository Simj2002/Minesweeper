package org.example;
import java.util.Arrays;

public class Tiles {

    public static void main(String[] args) {
        Tiles tiles = new Tiles();
        tiles.createTiles();
    };

    String tiles = "Tiles";

    String[][] grid = new String[17][13];

    public void createTiles() {
        for(int i = 0; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = this.tiles;
                //System.out.println(this.grid[i][j]);
            }
        }
        //System.out.println(this.grid);
        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }

}
