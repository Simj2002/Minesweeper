package org.example;

import java.util.*;
import java.util.Scanner;

public class HiddenTiles {
    public static void main(String[] args) {
        HiddenTiles tiles = new HiddenTiles();
        tiles.createHiddenTiles();
        System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
    }

    String bomb = "Bomb";
    String safe = "Safe";

    int minX = 1;
    int minY = 1;
    int maxX = 14;
    int maxY = 18;

    int numberOfBombs = 0;

    Random rand = new Random();


    String[][] grid = new String[15][19];

    public String[][] createHiddenTiles() {
        for(int i = 0; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = this.safe;
                //System.out.println(this.grid[i][j]);
            }
        }
        //System.out.println(this.grid);
        //System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));

        Random randomgenerator = new Random();
//        for(int bombNumber = 0; bombNumber < 39; bombNumber++) {
//            int randomNum = rand.nextInt((maxX - minX) + 1) + minX;
//            int randomNum1 = rand.nextInt((maxY - minY) + 1) + minY;
//            this.grid[randomNum][randomNum1] = this.bomb;
//            //this.grid[i][] = this.safe;
//            //System.out.println(this.grid[i][j]);
//        }
        Set<String> usedbombs = new HashSet<>();
        while(numberOfBombs < 40){
            int randomNum = rand.nextInt((maxX - minX) + 1) + minX;
            int randomNum1 = rand.nextInt((maxY - minY) + 1) + minY;

            String key = randomNum + "," + randomNum1;
            if(!usedbombs.contains(key)) {
                this.grid[randomNum][randomNum1] = this.bomb;
                usedbombs.add(key);
                numberOfBombs++;
            }
        }

        this.grid[0][0] = "Empty";

        // Changes top row to numbers 1 - 18
        for(int i = 1; i < this.grid[0].length; i++) {
            this.grid[0][i] = String.valueOf(i);
            this.grid[0][i] = String.format("%4d", i);
        }

        for(int j = 1; j < this.grid.length; j++) {
            this.grid[j][0] = String.valueOf(j);
            this.grid[j][0] = String.format("%6d", j);
        }

        return this.grid;
//        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }
}
