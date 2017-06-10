import java.util.Scanner;

// Change to 'Battle'

public class Paul {
	
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
    /**
     * Print out a board to look nice
     */
    public static void printBoard(String[][] board) {
        
        for (int i = 0; i < board.length; i++) {
            System.out.print("" + i + " ");
        }
        
        System.out.println("");
        
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                if (x == 0) {
                    System.out.print("" + x + " ");
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
	
	public static int shipLength(String ship) {
		if (ship.toLowerCase().equals("carrier")) {
			return 5;
		}
		else if (ship.toLowerCase().equals("battleship")) {
			return 4;
		}
		else if (ship.toLowerCase().equals("submarine")) {
			return 3;
					
		}
		else if (ship.toLowerCase().equals("Destroyer")) {
			return 2;
		}
		
		else {
			return 0;
		}
	}
	
	
	public static boolean verifyPlacement(int x, int y, String direction, String shipType, String[][] board) {
		
		if (x > boardOne[0].length) {
			return false;
		} 
	
		if (y > boardOne.length) {
			return false;
		}
		if (direction.toLowerCase().equals("down")) {
			if (y + shipLength(shipType) > boardOne.length) {
				return false;
			}
			for (int i = y; i < y + shipLength(shipType); i++) {
				if (board[x][i] != null) return false;
			}
		}
		else if (direction.toLowerCase().equals("right")) {
			if (x + shipLength(shipType) > boardOne.length) {
				return false;
			}
			for (int i = x; i < x + shipLength(shipType); i++) {
				if (board[i][y] != null) return false;
			}
		} 
		
		return true;
		
	}
	
	public static void placeShips(String[][] board) {
		String[] ships = {"Carrier (5)", "Battleship (4)", "Submarine (3)", "Destroyer (2)"};
		
		for (int i = 0; i < ships.length; i++) {
					
			int x = -1;
			int y = -1;
			String direction = "";
			
			while (verifyPlacement(x,  y,  direction,  ships[i], board) == false) {
				
                System.out.println("Enter the X coordinate for your " + ships[i]);
                
                while (x == -1) {
                    try {
                        x = input.nextInt();
                        input.nextLine();
                        if (x < 0 || x > ships.length) {
                        	x = -1;
                        	System.out.println("Enter a valid coordinate (0-9): ");
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Enter a valid coordinate (0-9): ");
                    }
                }
                
                System.out.println("Enter the Y coordinate for your " + ships[i]);
                
                while (y == -1) {
                    try {
                        y = input.nextInt();
                        input.nextLine();
                        if (y < 0 || y > ships.length) {
                        	y = -1;
                        	System.out.println("Enter a valid coordinate (0-9): ");
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Enter a valid coordinate (0-9): ");
                    }
                }
                
                while (! ( direction.toLowerCase().equals("down") || direction.toLowerCase().equals("right"))) {
                    System.out.println("Enter a direction (Down | Right)");
                    direction = input.nextLine();
                } 
			}
						
			if (direction.toLowerCase().equals("down")) {
				for (int z = y; z < y + shipLength(ships[i]); z++) {
					board[x][z] = ships[i];
				}
			}
			else if (direction.toLowerCase().equals("right")) {
				for (int z = x; z < x + shipLength(ships[i]); z++) {
					board[z][y] = ships[i];
				}
			}
			else {
				System.out.println("Bad position");
			}
		}
		
		
	}
	
	static String[][] boardOne;
	static String[][] boardTwo;
	static Scanner input;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boardOne = new String[10][10];
		boardTwo = new String[10][10];
		input = new Scanner(System.in);
		
		placeShips(boardOne);
		
		placeShips(boardTwo);
		
		clearConsole();
		
		printBoard(boardOne);
		
		input.nextLine();
		
		clearConsole();
		
		printBoard(boardTwo);
		
		input.nextLine();
		
		
		
	}
}
