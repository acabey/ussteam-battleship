

public class Battleship {
    
    Scanner kbInput;
    
    String[][] boardOne;
    String[][] boardTwo;
    
    public static void clearScreen() {
        // TODO
    }
    
    /**
     * Print out a board to look nice
     */
    public static void printBoard(String[][] board) {
        
        for (int i = 0; i < board.length; i++) {
            System.out.print(i.toString() + " ");
        }
        
        System.out.println("");
        
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (x == 0) {
                    System.out.print(x.toString() + " ");
                }
                if (x < board.length-1) {
                    System.out.print(board[x][y] + " ");
                }
                else {
                    System.out.print(board[x][y]);
                }
            }
        }
    }
    
    public static int shipSize(String ship) {
        if (ship.toLower().equals("carrier")) {
            return 5;
        }
        else if (ship.toLower().equals("battleship")) {
            return 4;
        }
        else if (ship.toLower().equals("submarine")) {
            return 3;
        }
        else if (ship.toLower().equals("destroyer")) {
            return 2;
        }
    }
    
    public static boolean checkPlacement(int x, int y, String direction, String ship, String[][] board) {
        // TODO
        return true;
    }
    
    public static void placeShips(String[][] board) {
        /*
        Class of ship 	Size
         Carrier 	      5
         Battleship 	  4
         Submarine 	      3
         Destroyer 	      2
        */
        
        String[] ships = ["Carrier (5)", "Battleship (4)", "Submarine (3)", "Destroyer (2)"];
        
        for (String ship : ships) {
            
            clearScreen();
            printBoard(board);
            
            int x = -1;
            int y = -1;
            String direction = "";
            
            while (checkPlacement(x, y, direction, ship, board) == false) {
                System.out.println("Enter the X coordinate for your " + ship);
                
                while (x == -1) {
                    try {
                        x = Integer.parseInt(kbInput.nextLine());
                    }
                    catch (Exception e) {
                        System.out.println("Enter a valid coordinate (0-9): ");
                    }
                }
                
                System.out.println("Enter the Y coordinate for your " + ship);
                
                while (y == -1) {
                    try {
                        y = Integer.parseInt(kbInput.nextLine());
                    }
                    catch (Exception e) {
                        System.out.println("Enter a valid coordinate (0-9): ");
                    }
                }
                
                while (! (direction.toLower().equals("up") || direction.toLower().equals("down") || direction.toLower().equals("left") || direction.toLower().equals("right"))) {
                    System.out.println("Enter a direction (Up, Down, Left, Right)")
                    direction = kbInput.nextLine();
                }
            }
            
            
            // Position is correct, actually save the position
            if (direction.toLower().equals("up")) {
                
                for (int i = y; i > y - shipSize(ship); i--) {
                    board[x][i] = ship.charAt(0).toLower().toString();
                }
                
            }
            else if (direction.toLower().equals("down")) {
                
                for (int i = y; i < y + shipSize(ship); i++) {
                    board[x][i] = ship.charAt(0).toLower().toString();
                }
                
            }
            else if (direction.toLower().equals("left")) {
                
                for (int i = x; i > x - shipSize(ship); i--) {
                    board[i][y] = ship.charAt(0).toLower().toString();
                }
                
            }
            else if (direction.toLower().equals("right")) {
                
                for (int i = x; i < x + shipSize(ship); i++) {
                    board[i][y] = ship.charAt(0).toLower().toString();
                }
                
            }
            
        }
    }
    
    public static void initBoard() {
        boardOne = new String[10][10];
        boardTwo = new String[10][10];
    }
    
    public static void main(String[] args) {
        // Set up game
        kbInput = new Scanner(System.in);
        
        // - Make Board
        initBoard();
        // - Place Ships
        
        System.out.println("Player one, place your ships! (Player two look away)")
        
        placeShips(boardOne);
        // - - Ask for positions
        // - - Check positions
        // - - Save position
        
        
        // Play game
        // - Guess spot
        // - - Check if already guessed
        // - - Check if hit
        // - - Alert player hit | miss
        // - - Check if all ships dead
        // - Switch Players
        
        // Congratulate winner
    }
}