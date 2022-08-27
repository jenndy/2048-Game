import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game_2048 {

	// Maximum number reached
	public static int maxNum = 0;

	// Maximum number of moves made
	public static int maxMove = 0;

	// Booleans check move validity // set to true initially
	public static boolean move1 = true; // left
	public static boolean move2 = true; // right
	public static boolean move3 = true; // up
	public static boolean move4 = true; // down

	static Scanner scan = new Scanner(System.in);

	// Create a new 4x4 board with a 2D array
	static int[][] board = new int[4][4];

	public static void main(String[] args) throws InterruptedException {

		// instructions for the user
		System.out.println("Welcome to 2048!");
		System.out.println("Goal: Create the tile 2048.");
		System.out.println("Keys used: a, s, d, w, q, r");
		System.out.println("a = left");
		System.out.println("s = down");
		System.out.println("d = right");
		System.out.println("w = up");
		System.out.println("q = quit");
		System.out.println("r = restart");
		System.out.println("Same numbers will combine to a sum.");
		System.out.println("Game is lost when no more moves can be made.");
		System.out.println("Loading board...");

		// Delays so user can read instructions  
		TimeUnit.SECONDS.sleep(5);

		// Start game with two random pieces on the board
		placePiece(board);
		placePiece(board);

		// Print out the board to the console
		printBoard(board);

		boolean game = true;

		// Runs the game loop until the user quits or the game is lost
		while (game) {

			// Checks if game is lost // no more possible moves
			if (checkLeft() == false && checkRight() == false && checkUp() == false && checkDown() == false) {
				endOutput();
				break;
			}

			System.out.println("Enter your move (a, s, d, w, q, r): ");
			String dir = scan.next();

			switch (dir) {

			// Left
			case "a":

				checkLeft();

				if (move1 == true) {
					moveLeft(board);
					maxMove += 1;
					placePiece(board);
					printBoard(board);
					System.out.println("Key pressed: left (a).");
					System.out.println("Valid move.");
				} else {
					System.out.println("Key pressed: left (a).");
					System.out.println("Invalid move.");
				}
				continue; // jumps to top of while loop

			// Down
			case "s":

				checkDown();

				if (move4 == true) {
					transposeBoard(board);
					moveRight(board);
					transposeBoard(board);
					checkDown();
					maxMove += 1;
					placePiece(board);
					printBoard(board);
					System.out.println("Key pressed: down (s).");
					System.out.println("Valid move.");
				} else {
					System.out.println("Key pressed: down (s).");
					System.out.println("Invalid move.");
				}
				continue;

			// Right
			case "d":

				checkRight();

				if (move2 == true) {
					moveRight(board);
					maxMove += 1;
					placePiece(board);
					printBoard(board);
					System.out.println("Key pressed: right (d).");
					System.out.println("Valid move.");
				} else {
					System.out.println("Key pressed: right (d).");
					System.out.println("Invalid move.");
				}
				continue;

			// Up
			case "w":

				checkUp();

				if (move3 == true) {
					transposeBoard(board);
					moveLeft(board);
					transposeBoard(board);
					maxMove += 1;
					placePiece(board);
					printBoard(board);
					System.out.println("Key pressed: up (w).");
					System.out.println("Valid move.");
				} else {
					System.out.println("Key pressed: up (w).");
					System.out.println("Invalid move.");
				}
				continue;

			// Quit
			case "q":
				System.out.println("Key Pressed: quit (q).");
				System.out.println("Confirm quit (y/n): ");
				String quit = scan.next();

				if (quit.equals("y")) {
					System.out.println("Key Pressed: yes (y).");
					endOutput();
					break; // breaks out of switch
				}

				System.out.println("Key Pressed: no (n).");
				continue;

			// Restart
			case "r":
				System.out.println("Key pressed: restart (r).");
				System.out.println("Confirm restart (y/n): ");
				String restart = scan.next();

				if (restart.equals("y")) {
					System.out.println("Key Pressed: yes (y).");
					board = new int[4][4];
					placePiece(board);
					placePiece(board);
					printBoard(board);
				} else {
					System.out.println("Key Pressed: no (n).");
				}
				continue;

			default:
				System.out.println("Key Pressed: " + "(" + dir + ").");
				System.out.println("Invalid input.");
				continue;
			}

			break; // breaks out of while loop
		}
	}

	// Transpose matrix // for move up and down
	public static void transposeBoard(int[][] b) {
		int[][] temp = new int[4][4];

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {

				// Columns on board are stored as rows on temporary board / rows on board are stored as columns on temp 
				temp[i][j] = b[j][i];
			}
		}
		board = temp;
	}

	// Checks move right
	public static boolean checkRight() {

		move2 = false;
		int[][] temp = new int[4][4];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				temp[i][j] = board[i][j];
			}
		}
		if (moveRight(temp)) {
			move2 = true;
		}
		return move2;
	}

	// Checks move left
	public static boolean checkLeft() {

		move1 = false;
		int[][] temp = new int[4][4];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				temp[i][j] = board[i][j];
			}
		}
		if (moveLeft(temp)) {
			move1 = true;
		}
		return move1;
	}

	// Checks move up
	public static boolean checkUp() {

		move3 = false;
		int[][] temp = new int[4][4];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				temp[i][j] = board[j][i];
			}
		}
		if (moveLeft(temp)) {
			move3 = true;
		}
		return move3;
	}

	// Checks move down
	public static boolean checkDown() {

		move4 = false;
		int[][] temp = new int[4][4];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				temp[i][j] = board[j][i];
			}
		}
		if (moveRight(temp)) {
			move4 = true;
		}
		return move4;
	}

	// Shift x2 Merge x1 Shift x1 // reverse scan through columns 
	// Move pieces right
	public static boolean moveRight(int[][] board) {

		move2 = false;

		// Shift x2
		// Move over up to 2 empty spots
		for (int k = 0; k < 2; k++) {

			// Iterates through rows
			for (int i = 0; i < board.length; i++) {

				// Iterates through columns (in reverse) //starts at j = 2
				for (int j = board[i].length - 2; j >= 0; j--) {

					// Checks if the right space is empty
					if (board[i][j] != 0) {
						if (board[i][j + 1] == 0) {

							// Reassigns right / next value
							board[i][j + 1] += board[i][j];

							// Empties current spot
							board[i][j] = 0;

							move2 = true;
						}
					}
				}
			}
		}

		// Merge x1
		// Iterates through rows
		for (int i = 0; i < board.length; i++) {

			// Iterates through columns (in reverse) //starts at j = 2
			for (int j = board[i].length - 2; j >= 0; j--) {

				// Checks if right tile matches current tile
				if (board[i][j] != 0) {
					if (board[i][j + 1] == board[i][j]) {

						// Reassigns right / next value
						board[i][j + 1] += board[i][j];

						// Empties current spot
						board[i][j] = 0;

						move2 = true;
					}
				}
			}
		}

		// Shift x1
		// Move over 1 empty spot
		// Iterates through rows
		for (int i = 0; i < board.length; i++) {

			// Iterates through columns (in reverse) //starts at j = 2
			for (int j = board[i].length - 2; j >= 0; j--) {

				if (board[i][j] != 0) {
					// Checks if the right space is empty
					if (board[i][j + 1] == 0) {

						// Reassigns right / next value
						board[i][j + 1] += board[i][j];

						// Empties current spot
						board[i][j] = 0;

						move2 = true;
					}
				}
			}
		}

		return move2;
	}

	// Move pieces to the left
	public static boolean moveLeft(int[][] board) {

		move1 = false;

		// Shift x2
		//Move over up to 2 empty spots
		for (int k = 0; k < 2; k++) {

			// Iterates through rows
			for (int i = 0; i < board.length; i++) {

				// Iterates through columns in reverse (in reverse) //starts at j = 1
				for (int j = 1; j < board[i].length; j++) {

					if (board[i][j] != 0) {
						// Checks if left tile is empty
						if (board[i][j - 1] == 0) {

							// Reassigns left or next value
							board[i][j - 1] += board[i][j];

							// Empties current spot
							board[i][j] = 0;

							move1 = true;
						}
					}
				}
			}
		}

		// Merge x1
		// Iterates through rows
		for (int i = 0; i < board.length; i++) {

			// Iterates through columns in reverse (in reverse) //starts at j = 1
			for (int j = 1; j < board[i].length; j++) {

				if (board[i][j] != 0) {
					// Checks if right tile matches current tile
					if (board[i][j - 1] == board[i][j]) {

						// Reassigns left or next spot
						board[i][j - 1] += board[i][j];

						// Empties current spot
						board[i][j] = 0;

						move1 = true;
					}
				}
			}
		}

		// Shift x1
		// Move over 1 empty spot 
		// Iterates through rows
		for (int i = 0; i < board.length; i++) {

			// Iterates through columns in reverse (in reverse) //starts at j = 1
			for (int j = 1; j < board[i].length; j++) {

				if (board[i][j] != 0) {
					// Checks if left tile is empty
					if (board[i][j - 1] == 0) {

						// Reassigns left or next value
						board[i][j - 1] += board[i][j];

						// Empties current spot
						board[i][j] = 0;

						move1 = true;
					}
				}
			}
		}
		return move1;
	}

	// Output at the end of a game
	public static void endOutput() {
		System.out.println();
		System.out.println("GAME OVER!");
		System.out.println("Max Number: " + maxNum);
		System.out.println("Move Count: " + maxMove);
	}

	// Adds a 2 or 4 piece to the board to a random location, probability of 2 is
	// 0.8 and 4 is 0.2
	public static void placePiece(int[][] board) {

		Random loc = new Random();
		Random piece = new Random();

		// Checks if board has open spots
		boolean open = false;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {

				if (board[i][j] == 0) {
					open = true;
				}
			}
		}

		while (open) {

			// Random location in 2D array
			int row = loc.nextInt(4); // 0 to 3 inclusive
			int col = loc.nextInt(4); // 0 to 3 inclusive

			// Accounts for ratio between 2 or 4
			int prob = piece.nextInt(5) + 1; // 1 to 5 inclusive

			// Check if index is open
			if (board[row][col] == 0) {

				// Add a 2 or 4 with probability constraints
				if (prob == 1) {
					board[row][col] = 4;
				} else {
					board[row][col] = 2;
				}
				break;

			} else {

				continue;
			}
		}
	}

	// Prints the board to the console
	public static void printBoard(int[][] board) {

		// New lines simulate clearing the board on console
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}

		maxNumber(board);
		System.out.println("Max Number: " + maxNum);
		System.out.println("Max Moves: " + maxMove);

		// Loops through each row and each column of each row to print value
		System.out.println(" _     _      _      _     _" );
		for (int row = 0; row < board.length; row++) {
			System.out.print("|");
			for (int col = 0; col < board[row].length; col++) {
				System.out.printf("%5d", board[row][col]);
				System.out.print(" |");
			}
			System.out.println();
			System.out.println(" _     _      _      _     _" );
		}
	}

	// Finds the maximum number on the board
	public static int maxNumber(int[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] > maxNum) {
					maxNum = board[i][j];
				}
			}
		}
		return maxNum;
	}
}
