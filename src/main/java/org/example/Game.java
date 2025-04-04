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

    public void Gamer() {
        Tiles tiles = new Tiles();
        String[][] initialTiles = tiles.createTiles();
        HiddenTiles hiddenTiles = new HiddenTiles();
        String[][] finalTiles = hiddenTiles.createHiddenTiles();

        System.out.println("This is the minesweeper grid:");

        while(!gameFinished) {
            System.out.println("This is the updated minesweeper grid:");
            System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
            System.out.println("Pick row number (0-13): ");
            int row = scanner.nextInt();
            System.out.println("Pick column number (0-17): ");
            int column = scanner.nextInt();

            if(finalTiles[row][column].equals("Bomb")) {
                gameFinished = true;
                System.out.println(Arrays.deepToString(hiddenTiles.grid).replace("], ", "]\n"));
                System.out.println("The final grid looks like this ^");
                System.out.println("GGs, you hit a bomb");
            } else{
                initialTiles[row][column] = finalTiles[row][column];
                System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
            }
        }
//        System.out.println("Enter row number (0-13): ");
//        int row = scanner.nextInt();
//        System.out.println("Enter column number (0-17): ");
//        int column = scanner.nextInt();
    }

}
