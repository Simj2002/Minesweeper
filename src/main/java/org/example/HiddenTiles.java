package org.example;

import java.util.*;
import java.util.Scanner;

public class HiddenTiles {
    public static void main(String[] args) {
        HiddenTiles tiles = new HiddenTiles();
        tiles.createHiddenTiles();
        //System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
    }

    String bomb = "Bomb";
    String safe = "Safe";

    final int minX = 1;
    final int minY = 1;
    final int maxX = 8;
    final int maxY = 10;

    int numberOfBombs = 0;

    Random rand = new Random();


    String[][] grid = new String[9][11];

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
        while(numberOfBombs < 6){
            int randomNum = rand.nextInt((maxX - minX) + 1) + minX;
            int randomNum1 = rand.nextInt((maxY - minY) + 1) + minY;

            String key = randomNum + "," + randomNum1;
            if(!usedbombs.contains(key)) {
                this.grid[randomNum][randomNum1] = this.bomb;
                usedbombs.add(key);
                numberOfBombs++;
            }
        }

        this.grid = numberOnTiles(this.grid);

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

//        this.grid = numberOnTiles(this.grid);

//        for(int i = 0; i < this.grid.length; i++) {
//            for(int j = 0; j < this.grid[0].length; j++) {
//                System.out.print(padRight(this.grid[i][j], 6));
//            }
//            System.out.println();
//        }
        //this.grid = generateGrid();

        return this.grid;
//        System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));
    }

    //Add numbers in tiles to show how many bombs in area around tile
    public String[][] numberOnTiles(String[][] grid) {
        for(int row = 0; row < grid.length; row++) {
            for(int column = 0; column < grid[0].length; column++) {
                if(grid[row][column].equals("Bomb")) {
                    continue;
                }

                int count = 0;

                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        if(i == 0 && j == 0) {
                            continue;
                        }

                        int rowValue = row + i;
                        int columnValue = column + j;

                        if((rowValue >= minX && rowValue <= maxX) && (columnValue >= minY && columnValue <= maxY)) {
                               if(grid[rowValue][columnValue].equals("Bomb")) {
                                    count++;
                               }
                        }
                    }
                }
                this.grid[row][column] = String.valueOf(count);
                if(this.grid[row][column].equals("0")) {
                    this.grid[row][column] = this.safe;
                }
            }
        }
        return this.grid;
    }

    private String padRight(String input, int length) {
        return String.format("%-" + length + "s", input);
    }

    public String[][] generateGrid() {
        for(int i = 0; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid[0].length; j++) {
                System.out.print(padRight(this.grid[i][j], 6));
            }
            System.out.println();
        }
        return this.grid;
    }
}
