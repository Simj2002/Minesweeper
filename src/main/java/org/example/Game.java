package org.example;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Game game = new Game();
        game.Gamer();
    }
    Scanner scanner = new Scanner(System.in);
    HiddenTiles hiddenTiles = new HiddenTiles();
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
            System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));

            //INPUT VALIDATIONS
            while(true){
                System.out.println("Pick row number (1-14): ");
                if(scanner.hasNextInt()) {
                    this.rowValue = scanner.nextInt();

                    if(this.rowValue >= 1 && this.rowValue <= 14) {
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
                System.out.println("Pick column number (1-18): ");
                if(scanner.hasNextInt()) {
                    this.colValue = scanner.nextInt();

                    if(this.colValue >= 1 && this.colValue <= 18) {
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
                System.out.println(Arrays.deepToString(hiddenTiles.grid).replace("], ", "]\n"));
                System.out.println("The final grid looks like this ^");
                System.out.println("GGs, you hit a bomb");
            } else{
                initialTiles[this.rowValue][this.colValue] = finalTiles[this.rowValue][this.colValue];
                //System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
            }
        }
    }
}
