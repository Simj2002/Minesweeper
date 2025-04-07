package org.example;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Game game = new Game();
        game.Gamer();
    }
    Scanner scanner = new Scanner(System.in);
    //HiddenTiles hiddenTiles = new HiddenTiles();
    boolean gameFinished = false;
    int rowValue;
    int colValue;

    public void Gamer() {
        Tiles tiles = new Tiles();
        String[][] initialTiles = tiles.createTiles();
        HiddenTiles hiddenTiles = new HiddenTiles();
        String[][] finalTiles = hiddenTiles.createHiddenTiles();

        System.out.println("This is the minesweeper grid:");

        while(!gameFinished) {
            System.out.println("This is the updated minesweeper grid:");
            //System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
            generateGrid(initialTiles);

            //INPUT VALIDATIONS
            while(true){
                System.out.println("Pick row number (1-8): ");
                if(scanner.hasNextInt()) {
                    this.rowValue = scanner.nextInt();

                    if(this.rowValue >= 1 && this.rowValue <= 8) {
                        break;
                    } else {
                        System.out.println("Invalid input, please try again with a number between 1-14");
                    }
                } else {
                    System.out.println("Invalid input, please try again with a number between 1-14");
                    scanner.next();
                }
            }

            while(true){
                System.out.println("Pick column number (1-10): ");
                if(scanner.hasNextInt()) {
                    this.colValue = scanner.nextInt();

                    if(this.colValue >= 1 && this.colValue <= 10) {
                        break;
                    } else {
                        System.out.println("Invalid input, please try again with a number between 1-18");
                    }
                } else {
                    System.out.println("Invalid input, please try again with a number between 1-18");
                    scanner.next();
                }
            }
//            System.out.println("Pick row number (1-14): ");
//            int row = scanner.nextInt();

//            System.out.println("Pick column number (1-18): ");
//            int column = scanner.nextInt();

            if(finalTiles[this.rowValue][this.colValue].equals("Bomb")) {
                gameFinished = true;
                //System.out.println(Arrays.deepToString(hiddenTiles.grid).replace("], ", "]\n"));
                generateGrid(finalTiles);
                System.out.println("The final grid looks like this ^");
                System.out.println("GGs, you hit a bomb");
            } else{
                //initialTiles[this.rowValue][this.colValue] = finalTiles[this.rowValue][this.colValue];

                if (finalTiles[this.rowValue][this.colValue].equals("Safe")) {
                    recursiveFunction(finalTiles, initialTiles, this.rowValue, this.colValue);
                }
                initialTiles[this.rowValue][this.colValue] = finalTiles[this.rowValue][this.colValue];
                //System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
            }
            //initialTiles[this.rowValue][this.colValue] = finalTiles[this.rowValue][this.colValue];
            int totalNonBombTiles = 0;
            int shownNonBombTiles = 0;
            for(int i = 1; i < finalTiles.length; i++) {
                for(int j = 1; j < finalTiles[0].length; j++) {
                    if(!initialTiles[i][j].equals("Tile") == finalTiles.equals("Bomb")) {
                        shownNonBombTiles++;
                        //System.out.println(shownNonBombTiles);
                    }

                    if(!finalTiles[i][j].equals("Safe") && !finalTiles[i][j].matches("\\d+")) {
                        totalNonBombTiles++;
                        //System.out.println(totalNonBombTiles);
                    }
                }
            }

            if(shownNonBombTiles == totalNonBombTiles){
                gameFinished = true;
                generateGrid(finalTiles);
                System.out.println("Game finished!");
            }
        }
    }

    private String padRight(String input, int length) {
        return String.format("%-" + length + "s", input);
    }

    public String[][] generateGrid(String[][] test) {
        for(int i = 0; i < test.length; i++) {
            for(int j = 0; j < test[0].length; j++) {
                if(i == 0 || j == 0) {
                    System.out.print(padRight(test[i][j], 7));
                } else if(test[i][j].equals("Tile")){
                    System.out.print("\u001B[36m" + padRight(test[i][j], 7) + "\u001B[0m");
                } else if(test[i][j].equals("Safe")){
                    System.out.print("\u001B[32m" + padRight(test[i][j], 7) + "\u001B[0m");
//                    System.out.print(padRight(test[i][j], 7));
                } else if(test[i][j].equals("Bomb")){
                    System.out.print("\u001B[31m" + padRight(test[i][j], 7) + "\u001B[0m");
                } else {
                    System.out.print(padRight(test[i][j], 7));
                }
            }
            System.out.println();
        }
        return test;
    }


    public void recursiveFunction(String[][] finalTiles, String[][] initialTiles, int rowValue, int colValue) {
//        if (!finalTiles[rowValue][colValue].equals("Safe") || initialTiles[rowValue][colValue].equals("Safe")) {
//            return;
//        }

        if(!(finalTiles[rowValue][colValue]).equals("Safe") && !(finalTiles[rowValue][colValue]).matches("\\d+")) {
            return;
        }

        if (!initialTiles[rowValue][colValue].equals("Tile")) {
            return;
        }

        initialTiles[rowValue][colValue] = finalTiles[rowValue][colValue];

        if(!(finalTiles[rowValue][colValue]).equals("Safe")){
            return;
        }

        for (int t = -1; t <= 1; t++) {
            for (int s = -1; s <= 1; s++) {
                if (t == 0 && s == 0) continue;

                int newRow = rowValue + t;
                int newCol = colValue + s;

                if (newRow >= 0 && newRow < finalTiles.length &&
                        newCol >= 0 && newCol < finalTiles[0].length) {
                    recursiveFunction(finalTiles, initialTiles, newRow, newCol);
                }
            }
        }
    }
}
