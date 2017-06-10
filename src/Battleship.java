import java.util.Scanner;

public class Battleship {
    
    static Scanner kbInput;
    
    static String[][] boardOne;
    static String[][] boardTwo;
    
    static String playerOne;
    static String playerTwo; 
    
    public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		
		/*
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	    */
    }
    
    public static void initBoard() {
        boardOne = new String[10][10];
        boardTwo = new String[10][10];
        
        for (int x = 0; x < boardOne.length; x++) {
        	for (int y = 0; y < boardOne[0].length; y++) {
        		boardOne[x][y] = "-";
        		boardTwo[x][y] = "-";
        	}
        }
        
    }
    
    public static void printOwnBoard(String[][] board, String message) {
        /*
    	for (String[] row : board) {
    		for (String column : row) {
    			System.out.print(column + " ");
    		}
    		System.out.println("");
    	}
    	*/
    	
    	System.out.println(message);
    	
    	System.out.print(" ");
    	
        for (int i = 0; i < board.length; i++) {
            System.out.print(" "+ i);
        }
        
        System.out.println("");
        
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (x == 0) {
                    System.out.print("" + y + " ");
                }
                if (x < board.length-1) {
                    System.out.print(board[x][y] + " ");
                }
                else {
                    System.out.print(board[x][y]);
                }
            }
            System.out.println("");
        }
        
    }
    
    public static void printOtherBoard(String[][] board, String message) {
        /*
    	for (String[] row : board) {
    		for (String column : row) {
    			System.out.print(column + " ");
    		}
    		System.out.println("");
    	}
    	*/
    	
    	System.out.println(message);
    	
    	System.out.print(" ");
    	
        for (int i = 0; i < board.length; i++) {
            System.out.print(" "+ i);
        }
        
        System.out.println("");
        
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[0].length; x++) {
                if (x == 0) {
                    System.out.print("" + y + " ");
                }
                if (x < board.length-1) {
                	if (board[x][y].equals("X") || board[x][y].equals("O")) {
                		System.out.print(board[x][y] + " ");
                	}
                	else {
                		System.out.print("- ");
                	}
                }
                else {
                	if (board[x][y].equals("X") || board[x][y].equals("O")) {
                		System.out.print(board[x][y]);
                	}
                	else {
                		System.out.print("- ");
                	}
                }
            }
            System.out.println("");
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
        else if (ship.toLowerCase().equals("destroyer")) {
            return 2;
        }
        else {
        	return 0;
        }
    }
    
    public static boolean checkPlacement(int x, int y, String direction, String ship, String[][] board) {
		if (x > boardOne[0].length || x < 0) {
			System.out.println("Bad Placement X out of bound");
			return false;
		} 
	
		if (y > boardOne.length || y < 0) {
			System.out.println("Bad Placement Y out of bound");
			return false;
		}
		
		if (direction.toLowerCase().equals("down")) {
			if (y + shipLength(ship) > boardOne.length) {
				System.out.println("Bad Placement ship exceeds border");
				return false;
			}
			for (int i = y; i < y + shipLength(ship); i++) {
				if (!board[x][i].equals("-")) {
					System.out.println("Bad Placement");
					return false;
				}
			}
		}
		else if (direction.toLowerCase().equals("right")) {
			if (x + shipLength(ship) > boardOne.length) {
				System.out.println("Bad Placement");
				return false;
			}
			for (int i = x; i < x + shipLength(ship); i++) {
				if (!board[i][y].equals("-")) {
					System.out.println("Bad Placement");
					return false;
				}
			}
		} 
		else if (direction.toLowerCase().equals("left")) {
			if (x - shipLength(ship) < 0) {
				System.out.println("Bad Placement");
				return false;
			}
            for (int i = x; i > x - shipLength(ship); i--) {
                if (!board[i][y].equals("-")) {
                	return false;
                }
            }
            
        } 
		if (direction.toLowerCase().equals("up")) {
			if (y - shipLength(ship) < 0) {
				System.out.println("Bad Placement");
				return false;
			}
            for (int i = y; i > y - shipLength(ship); i--) {
                if (!board[x][i].equals("-")) {
                	return false;
                }
            }
            
        }
		
		
		System.out.println("Good Placement");
		return true;
    }
            
    public static boolean gameOver(String[][] board) {
    	// Game over if there are 14 xs on the board
    	int count = 0;
    	for (String[] row : board) {
    		for (String column : row) {
    			if (column.toLowerCase().equals("x")) {
    				count++;
    			}
    		}
    	}
    	return !(count < 14);
    }
    
    public static void placeShips(String[][] board, String player) {
        /*
        Class of ship 	Size
         Carrier 	      5
         Battleship 	  4
         Submarine 	      3
         Destroyer 	      2
        */
        
        String[] ships = {"Carrier", "Battleship", "Submarine", "Destroyer"};
        
        for (String ship : ships) {
            
            int x = -1;
            int y = -1;
            String direction = "";
            
            while (checkPlacement(x, y, direction, ship, board) == false) {
            	x = -1;
            	y = -1;
            	direction = "";
            	
            	clearScreen();
                printOwnBoard(board, "Player " + player + ", place your ships!");
            	
            	
                while (x < 0 || x > board.length) {
                	System.out.println("Enter the X coordinate for your " + ship + " (" + shipLength(ship) + ")");
                	try {
            			x = kbInput.nextInt();
            			if (kbInput.hasNextLine()) {
            				kbInput.nextLine();
            			}
            		}
            		catch (Exception e) {
            			if (kbInput.hasNextLine()) {
            				kbInput.nextLine();
            			}
            			System.out.println("X Exception! Enter a valid coordinate (0-9): ");
            		}
                }
                
                
                while (y < 0 || y > board.length) {
                	System.out.println("Enter the Y coordinate for your " + ship + " (" + shipLength(ship) + ")");
                	try {
            			y = kbInput.nextInt();
            			if (kbInput.hasNextLine()) {
            				kbInput.nextLine();
            			}
            		}
            		catch (Exception e) {
            			if (kbInput.hasNextLine()) {
            				kbInput.nextLine();
            			}
            			System.out.println("Y Exception! Enter a valid coordinate (0-9): ");
            		}
                }
                
                while (! (direction.toLowerCase().equals("up") || direction.toLowerCase().equals("down") || direction.toLowerCase().equals("left") || direction.toLowerCase().equals("right"))) {
                    System.out.println("Enter a direction (Up, Down, Left, Right)");
                    direction = kbInput.nextLine();
                }
            }
            
            
            // Position is correct, actually save the position
            if (direction.toLowerCase().equals("up")) {
                
                for (int i = y; i > y - shipLength(ship); i--) {
                    board[x][i] = ship.substring(0,1);
                }
                
            }
            else if (direction.toLowerCase().equals("down")) {
            	
                for (int i = y; i < y + shipLength(ship); i++) {
                    board[x][i] = ship.substring(0,1);
                }
                
            }
            else if (direction.toLowerCase().equals("left")) {
                
                for (int i = x; i > x - shipLength(ship); i--) {
                    board[i][y] = ship.substring(0,1);
                }
                
            }
            else if (direction.toLowerCase().equals("right")) {
                
                for (int i = x; i < x + shipLength(ship); i++) {
                    board[i][y] = ship.substring(0,1);
                }
                
            }
            
            
            
        }
    }

    public static boolean checkGuess(int x, int y, String[][] board) {
    	if (x < 0 || x > board.length || y < 0 || y > board[0].length || board[x][y].equals("X") || board[x][y].equals("O")) {
    		return false;
    	} 
    	return true;
    }
    
    public static void guessSpot(String[][] board) {
    	
    	clearScreen();
    	printOtherBoard(board, "");
    	
    	int x = -1;
    	int y = -1;
    	
    	while (checkGuess(x, y, board) == false) {
    		System.out.print("Guess an X coordinate: ");
    		try {
    			x = kbInput.nextInt();
    			if (kbInput.hasNextLine()) {
    				kbInput.nextLine();
    			}
    		}
    		catch (Exception e) {
    			if (kbInput.hasNextLine()) {
    				kbInput.nextLine();
    			}
    		}
    		
    		System.out.print("Guess a Y coordinate: ");
    		try {
    			y = kbInput.nextInt();
    			if (kbInput.hasNextLine()) {
    				kbInput.nextLine();
    			}
    		}
    		catch (Exception e) {
    			if (kbInput.hasNextLine()) {
    				kbInput.nextLine();
    			}
    		}
    	}
    	
    	
    	// My god this is spaghetti
    	
    	String message = "";
    	
    	if (board[x][y].equals("C")) {
    		board[x][y] = "X";
            message = "Hit enemy carrier!";
        }
        else if (board[x][y].equals("B")) {
        	board[x][y] = "X";
            message = "Hit enemy battleship!";
        }
        else if (board[x][y].equals("S")) {
        	board[x][y] = "X";
            message = "Hit enemy submarine!";
        }
        else if (board[x][y].equals("D")) {
        	board[x][y] = "X";
            message = "Hit enemy destroyer!";
        }
        else {
        	board[x][y] = "O";
            message = "Miss!";
        }
    	
    	clearScreen();
    	printOtherBoard(board, message);
    	
    	
    }
    
    public static void main(String[] args) {
        // Set up game
        kbInput = new Scanner(System.in);
        
        System.out.print("Player one enter your name: ");
        playerOne = kbInput.nextLine();
        System.out.print("Player two enter your name: ");
        playerTwo = kbInput.nextLine();
        
        // - Make Board
        initBoard();
        
        // - Place Ships
        // - - Ask for positions
        // - - Check positions
        // - - Save position
        
        placeShips(boardOne, playerOne);
        
        System.out.println("");
        System.out.println("Press Enter when you are ready");
        kbInput.nextLine();
        
        clearScreen();
        System.out.println(playerTwo + ", press Enter when you are ready to place");
        kbInput.nextLine();
        
        placeShips(boardTwo, playerTwo);
        
        System.out.println("");
        System.out.println("Press Enter when you are ready");
        kbInput.nextLine();
        
        // Play game
        // - Guess spot
        // - - Check if already guessed
        // - - Check if hit
        // - - Alert player hit | miss
        // - - Check if all ships dead
        // - Switch Players
        
        boolean playerOneTurn = true;
        while (!(gameOver(boardOne) || gameOver(boardTwo))) {
        	clearScreen();
        	if (playerOneTurn) {
        		
        		System.out.println("Player one ready?");
        		kbInput.nextLine();
        		
        		guessSpot(boardTwo);
        		
        		kbInput.nextLine();
        		
        		if (gameOver(boardTwo)) {
        			System.out.println(playerOne + " Wins!");
        		}
        		playerOneTurn = !playerOneTurn;
        	}
        	else {
        		
        		System.out.println("Player two ready?");
        		kbInput.nextLine();
        		
        		guessSpot(boardOne);
        		
        		kbInput.nextLine();
        		
        		
        		if (gameOver(boardOne)) {
        			System.out.println(playerTwo + " Wins!");
        		}
        		playerOneTurn = !playerOneTurn;
        	}
        }
        
        // Congratulate winner
    }
}