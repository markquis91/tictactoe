

public class Board {
		
	private static final String EMPTY_SQUARE = "";
	private final int BOARD_SIZE = 3;
	private String[][] board;

	/**
	 * This class is responsible for the functionality of the tic tac toe game board.
	 * Responsible for:
	 *   Displaying the board
	 *   Managing it's state
	 *   - Which squares are empty, which are filled
	 *   - Who filled it
	 *   - Making sure it's a valid game board
	 *   - Testing if the game board is full
	 */
	
	public Board() {
		this.board = new String[BOARD_SIZE][BOARD_SIZE];
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				setEmptySquare(row, column);
			}
		}
	}
		
	public void setEmptySquare(int row, int column) {
		if (board[row][column] == null) {
		board[row][column] = EMPTY_SQUARE;
		}
	}

	public void displayboard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				
				boolean isEmpty = isSquareEmpty(row, column);
				if (isEmpty) {
					System.out.print("|" + (row * 3 + column + 1) +"|");
				}
				else {
				    System.out.print("|"+board[row][column] +"|");
				}
			}
			System.out.println("\n");
		}
	}	
	
	public int getLength() {
		return BOARD_SIZE;
	}
			
	public boolean isSquareEmpty(int row, int column) {
		return board[row][column].equals(EMPTY_SQUARE);
	}

	public void setPlayerToken(int row, int column, String player) {
		if (row < BOARD_SIZE && column < getLength()) {
		board[row][column] = player;
		}
	}

	public void clearBoard() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				board[row][column] = EMPTY_SQUARE;
			}
		}
	}
	
	
	public String getToken(int row, int column) {
		return board[row][column];
	}
	

	public boolean emptySquaresExist() {
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int column = 0; column < BOARD_SIZE; column++) {
				boolean emptySquare = isSquareEmpty(row, column);
				if (emptySquare) {
					return true;
				}
			}
		}
		return false;
	}
}	 
