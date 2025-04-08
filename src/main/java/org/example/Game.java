package org.example;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Game game = new Game();
        game.Gamer();
    }

    Scanner scanner = new Scanner(System.in);

    //boolean to end game when you win or lose
    boolean gameFinished = false;

    //coordinate values
    int rowValue;
    int colValue;

    //Minimum and maximum value of grid (excluding coordinates)
    final int minX = 1;
    final int minY = 1;
    int maxX = 8;
    int maxY = 10;

    int numOfBombs = 0;

    //User chooses either "Flag" or "Tile"
    String choice = "";


    public void Gamer() {
        while(true){
            System.out.println("Enter either easy, medium or hard");
            String difficulty = scanner.next().trim();

            if (!difficulty.equalsIgnoreCase("Easy") && !difficulty.equalsIgnoreCase("Medium") && !difficulty.equalsIgnoreCase("Hard")) {
                System.out.println("Invalid choice. Please enter difficulty: Easy, Medium, Hard");
                continue;
            }

            if(difficulty.equals("easy")){
                this.maxX = 8;
                this.maxY = 10;
                this.numOfBombs = 3;
                break;
            } else if(difficulty.equals("medium")){
                this.maxX = 15;
                this.maxY = 19;
                this.numOfBombs = 20;
                break;
            } else if(difficulty.equals("hard")){
                this.maxX = 26;
                this.maxY = 34;
                this.numOfBombs = 40;
                break;
            }
        }


        //Create initial tiles grid
        Tiles tiles = new Tiles();
        String[][] initialTiles = tiles.createTiles(maxX+1,maxY+1);
        //Create final tiles grid
        HiddenTiles hiddenTiles = new HiddenTiles();
        String[][] finalTiles = hiddenTiles.createHiddenTiles(maxX,maxY, numOfBombs);


        while(!gameFinished) {
            System.out.println("This is the minesweeper grid:");

            generateGrid(initialTiles);

            while(true) {
                System.out.println("Enter whether you want to pick a tile or add a flag: \"Flag\" for adding flag, \"Tile\" for picking a tile");
                choice = scanner.next().trim();

                //Input validation
                if (!choice.equalsIgnoreCase("Flag") && !choice.equalsIgnoreCase("Tile")) {
                    System.out.println("Invalid choice. Please enter either \"Flag\" or \"Tile\".");
                    continue;
                }

                //User enters row coordinate and input validation used as well
                int selectedRow;
                while (true) {
                    System.out.println("Pick row number (1-" + (maxX) + "): ");
                    if (scanner.hasNextInt()) {
                        selectedRow = scanner.nextInt();
                        if (selectedRow >= this.minX && selectedRow <= maxX) {
                            break;
                        } else {
                            System.out.println("Invalid input, please try again with a number between 1 and 8.");
                        }
                    } else {
                        System.out.println("Invalid input, please enter a number between 1 and " + (maxY) + ".");
                        scanner.next();
                    }
                }

                //User enters column coordinate and input validation used as well
                int selectedCol;
                while (true) {
                    System.out.println("Pick column number (1-" + (this.maxY) + "): ");
                    if (scanner.hasNextInt()) {
                        selectedCol = scanner.nextInt();
                        if (selectedCol >= minY && selectedCol <= maxY) {
                            break;
                        } else {
                            System.out.println("Invalid input, please try again with a number between 1 and 10.");
                        }
                    } else {
                        System.out.println("Invalid input, please enter a number between 1 and " + (this.maxY) + ".");
                        scanner.next();
                    }
                }

                this.rowValue = selectedRow;
                this.colValue = selectedCol;

                //Insert a flag if user chooses flag
                if (choice.equalsIgnoreCase("Flag")) {
                    initialTiles[rowValue][colValue] = "Flag";
                }
                break;
            }


            //Skip loop if choice and tile is a Flag
            if(initialTiles[this.rowValue][this.colValue].equals("Flag") && this.choice.equalsIgnoreCase("Flag")) {
                continue;
            //End game if user hits a bomb
            } else if(finalTiles[this.rowValue][this.colValue].equals("Bomb")) {
                gameFinished = true;
                generateGrid(finalTiles);
                System.out.println("The final grid looks like this ^");
                System.out.println("GGs, you hit a bomb");
            }else{
                //if user hits a safe tile, do the recursive function
                if (finalTiles[this.rowValue][this.colValue].equals("Safe")) {
                    recursiveFunction(finalTiles, initialTiles, this.rowValue, this.colValue);
                }
                //Otherwise return just the number
                initialTiles[this.rowValue][this.colValue] = finalTiles[this.rowValue][this.colValue];
            }
            //initialTiles[this.rowValue][this.colValue] = finalTiles[this.rowValue][this.colValue];

            //Win conditions
            int totalNonBombTiles = 0;
            int shownNonBombTiles = 0;
            for(int i = 1; i < finalTiles.length; i++) {
                for(int j = 1; j < finalTiles[0].length; j++) {
                    //tiles and flag tiles in initial grid compared to bomb tiles in final grid - non bomb tiles increase counter
                    if((!initialTiles[i][j].equals("Tile") == finalTiles[i][j].equals("Bomb")) || (!initialTiles[i][j].equals("Flag") == finalTiles[i][j].equals("Bomb"))) {
                        shownNonBombTiles++;
                        //System.out.println(shownNonBombTiles);
                    }

                    //Find how many bombs in final grid
                    if(!finalTiles[i][j].equals("Safe") && !finalTiles[i][j].matches("\\d+")) {
                        totalNonBombTiles++;
                        //System.out.println(totalNonBombTiles);
                    }
                }
            }

            //If both initial grid and final grid contain same number on counter - user wins
            if(shownNonBombTiles == totalNonBombTiles){
                gameFinished = true;
                generateGrid(finalTiles);
                System.out.println("Game finished! You have won! GGs!");
            }
        }
    }

    //Method to add padding to the grid
    private String padRight(String input, int length) {
        return String.format("%-" + length + "s", input);
    }

    //Generate grid with padding and colour
    public String[][] generateGrid(String[][] gridTest) {
        for(int i = 0; i < gridTest.length; i++) {
            for(int j = 0; j < gridTest[0].length; j++) {
                if(i == 0 || j == 0) {
                    System.out.print(padRight(gridTest[i][j], 7));
                } else if(gridTest[i][j].equals("Tile")){
                    //Add cyan colour
                    System.out.print("\u001B[36m" + padRight(gridTest[i][j], 7) + "\u001B[0m");
                } else if(gridTest[i][j].equals("Safe")){
                    //Add green colour
                    System.out.print("\u001B[32m" + padRight(gridTest[i][j], 7) + "\u001B[0m");
                } else if(gridTest[i][j].equals("Bomb")){
                    //Add red colour
                    System.out.print("\u001B[31m" + padRight(gridTest[i][j], 7) + "\u001B[0m");
                } else if(gridTest[i][j].equals("Flag")){
                    //Add yellow colour
                    System.out.print("\u001B[33m" + padRight(gridTest[i][j], 7) + "\u001B[0m");
                }else {
                    //Else white colour
                    System.out.print(padRight(gridTest[i][j], 7));
                }
            }
            System.out.println();
        }
        return gridTest;
    }


    public void recursiveFunction(String[][] finalTiles, String[][] initialTiles, int rowValue, int colValue) {
        //Don't continue if tile is not "Safe" or a number
        if(!(finalTiles[rowValue][colValue]).equals("Safe") && !(finalTiles[rowValue][colValue]).matches("\\d+")) {
            return;
        }

        //Don't check if tile is already shown
        if (!initialTiles[rowValue][colValue].equals("Tile")) {
            return;
        }

        initialTiles[rowValue][colValue] = finalTiles[rowValue][colValue];

        //Stop once there is a number
        if(!(finalTiles[rowValue][colValue]).equals("Safe")){
            return;
        }

        for (int t = -1; t <= 1; t++) {
            for (int s = -1; s <= 1; s++) {
                if (t == 0 && s == 0) continue;

                int newRow = rowValue + t;
                int newCol = colValue + s;

                if (newRow >= 0 && newRow < finalTiles.length && newCol >= 0 && newCol < finalTiles[0].length) {
                    recursiveFunction(finalTiles, initialTiles, newRow, newCol);
                }
            }
        }
    }
}





//if(finalTiles[this.rowValue][this.colValue].equals("Bomb")) {
//gameFinished = true;
////System.out.println(Arrays.deepToString(hiddenTiles.grid).replace("], ", "]\n"));
//generateGrid(finalTiles);
//                System.out.println("The final grid looks like this ^");
//                System.out.println("GGs, you hit a bomb");
//            } else if(initialTiles[this.rowValue][this.colValue].equals("Flag") && this.choice.equalsIgnoreCase("Flag")) {
//        continue;
//        }else{
//        //initialTiles[this.rowValue][this.colValue] = finalTiles[this.rowValue][this.colValue];
//
//        if (finalTiles[this.rowValue][this.colValue].equals("Safe")) {
//recursiveFunction(finalTiles, initialTiles, this.rowValue, this.colValue);
//                }
//initialTiles[this.rowValue][this.colValue] = finalTiles[this.rowValue][this.colValue];
//        //System.out.println(Arrays.deepToString(tiles.grid).replace("], ", "]\n"));
//        }

//INPUT VALIDATIONS
//            while(true){
//                System.out.println("Pick row number (1-8): ");
//                if(scanner.hasNextInt()) {
//                    this.rowValue = scanner.nextInt();
//
//                    if(this.rowValue >= 1 && this.rowValue <= 8) {
//                        break;
//                    } else {
//                        System.out.println("Invalid input, please try again with a number between 1-14");
//                    }
//                } else {
//                    System.out.println("Invalid input, please try again with a number between 1-14");
//                    scanner.next();
//                }
//            }
//
//            while(true){
//                System.out.println("Pick column number (1-10): ");
//                if(scanner.hasNextInt()) {
//                    this.colValue = scanner.nextInt();
//
//                    if(this.colValue >= 1 && this.colValue <= 10) {
//                        break;
//                    } else {
//                        System.out.println("Invalid input, please try again with a number between 1-18");
//                    }
//                } else {
//                    System.out.println("Invalid input, please try again with a number between 1-18");
//                    scanner.next();
//                }
//            }
//            System.out.println("Pick row number (1-14): ");
//            int row = scanner.nextInt();

//        if (!finalTiles[rowValue][colValue].equals("Safe") || initialTiles[rowValue][colValue].equals("Safe")) {
//            return;
//        }
