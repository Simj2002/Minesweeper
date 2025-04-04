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

    int minX = 0;
    int minY = 0;
    int maxX = 13;
    int maxY = 17;

    int numberOfBombs = 0;

    Random rand = new Random();


    String[][] grid = new String[14][18];

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
        return this.grid;
//        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }
}
