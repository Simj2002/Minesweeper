package org.example;

import java.util.*;
import java.util.Scanner;

public class HiddenTiles {
    public static void main(String[] args) {
        HiddenTiles tiles = new HiddenTiles();
//        tiles.createHiddenTiles(8,10);
        System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
    }

    String bomb = "Bomb";
    String safe = "Safe";

    //Minimum and maximum value of grid (excluding coordinates)
    final int minX = 1;
    final int minY = 1;
    int maxX = 8;
    int maxY = 10;

    int numberOfBombs = 0;
    int numberOfBombsNeeded = 7;

    Random rand = new Random();

    //Create grid
    String[][] grid = new String[9][11];


    public String[][] createHiddenTiles(int maxX,int maxY,int mines) {
        this.numberOfBombsNeeded = mines;
        //Set all tiles to safe
        this.maxX = maxX;
        this.maxY = maxY;
        grid = new String[maxX + 1][maxY + 1];
        for(int i = 0; i < this.grid.length; i++) {
            for(int j = 0; j < this.grid[i].length; j++) {
                this.grid[i][j] = this.safe;
                //System.out.println(this.grid[i][j]);
            }
        }

        //Create hashset and store bombs as key values to stop duplicate bomb coordinates
        Set<String> usedbombs = new HashSet<>();
        while(numberOfBombs < this.numberOfBombsNeeded){
            //Generate random number between the grid coordinates for x and y
//            int randomNum = rand.nextInt((maxX)) + minX;
//            int randomNum1 = rand.nextInt((maxY)) + minY;
            int randomNum = rand.nextInt(this.maxX - minX + 1) + minX;
            int randomNum1 = rand.nextInt(this.maxY - minY + 1) + minY;

            //Make key then add bomb key to hashset if it doesn't already have key
            String key = randomNum + "," + randomNum1;
            if(!usedbombs.contains(key)) {
                this.grid[randomNum][randomNum1] = this.bomb;
                usedbombs.add(key);
                numberOfBombs++;
            }
        }

        //Add numbers around bombs
        this.grid = numberOnTiles(this.grid);

        //Add empty to top left corner
        this.grid[0][0] = "Empty";

        //Change top row to coordinate numbers 1 - 10
        for(int i = 1; i < this.grid[0].length; i++) {
            this.grid[0][i] = String.valueOf(i);
        }

        //Change left column to coordinate number 1 - 8
        for(int j = 1; j < this.grid.length; j++) {
            this.grid[j][0] = String.valueOf(j);
        }

        return this.grid;
    }

    //Add numbers in tiles to show how many bombs in area around tile
    public String[][] numberOnTiles(String[][] grid) {
        for(int row = 0; row < grid.length; row++) {
            for(int column = 0; column < grid[0].length; column++) {
                //we skip bomb as we do not want to change value of bomb
                if(grid[row][column].equals("Bomb")) {
                    continue;
                }

                //set counter to count bombs
                int count = 0;

                //Loop all 8 tiles around middle tile
                for(int i = -1; i <= 1; i++) {
                    for(int j = -1; j <= 1; j++) {
                        if(i == 0 && j == 0) {
                            continue;
                        }

                        int rowValue = row + i;
                        int columnValue = column + j;

                        //Make sure tile is in bounds
                        if((rowValue >= minX && rowValue <= maxX) && (columnValue >= minY && columnValue <= maxY)) {
                            //if bomb is found in any of the 8 tiles then count++
                               if(grid[rowValue][columnValue].equals("Bomb")) {
                                    count++;
                               }
                        }
                    }
                }
                //Add count number to tile and change 0's to "safe"
                this.grid[row][column] = String.valueOf(count);
                if(this.grid[row][column].equals("0")) {
                    this.grid[row][column] = this.safe;
                }
            }
        }
        return this.grid;
    }
}


//Random randomgenerator = new Random();
//        for(int bombNumber = 0; bombNumber < 39; bombNumber++) {
//            int randomNum = rand.nextInt((maxX - minX) + 1) + minX;
//            int randomNum1 = rand.nextInt((maxY - minY) + 1) + minY;
//            this.grid[randomNum][randomNum1] = this.bomb;
//            //this.grid[i][] = this.safe;
//            //System.out.println(this.grid[i][j]);
//        }

//int randomNum = rand.nextInt((maxX - minX) + 1) + minX;
//int randomNum1 = rand.nextInt((maxY - minY) + 1) + minY;

//        this.grid = numberOnTiles(this.grid);

//        for(int i = 0; i < this.grid.length; i++) {
//            for(int j = 0; j < this.grid[0].length; j++) {
//                System.out.print(padRight(this.grid[i][j], 6));
//            }
//            System.out.println();
//        }
//this.grid = generateGrid();

//    private String padRight(String input, int length) {
//        return String.format("%-" + length + "s", input);
//    }
//
//    public String[][] generateGrid() {
//        for(int i = 0; i < this.grid.length; i++) {
//            for(int j = 0; j < this.grid[0].length; j++) {
//                System.out.print(padRight(this.grid[i][j], 6));
//            }
//            System.out.println();
//        }
//        return this.grid;
//    }
