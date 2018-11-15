import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameController {

	/**
	 * This class is responsible for controlling the flow of the game. The controller is responsible for accepting
	 * user input, managing game moves and evaluating the gameboard for a winner.
	 * @param player
	 * @param moves
	 * @param board
	 */
	
	private final String tokenX = "X";
	private final String tokenO = "O";
	
	public String getTokenX() {
		return tokenX;
	}
		
	public String getTokenO() {
		return tokenO;
	}
			
	public void gameController() {
		try {
			Board board = new Board();
			
			menuPrompt();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			input = br.readLine();
			switch (input.toLowerCase()) {
			case "start":
				userPlayerSelectionPrompt();
				Player userSelection = userPlayerSelection();
				Player computerSelection = getComputerSelection(userSelection);

				boolean gameRunning = true;
				
				do {
					board.displayboard();

					checkUserInputAddToBoard(userSelection, board);

					// Determine if user has won
					String winner = gameEvaluator(board, userSelection);
					
					if (winner != null) {
						//gameRunning = false;

						// If user has won then print message and quit
						gameWinner(winner);
						board.displayboard();
						break;
					}

					boolean fullBoard = isBoardFull(board);
					// board is full, stop the game
					if (fullBoard) {
						boardFullPrompt();
						//gameRunning = false;
						break;
					}
					
					// if game is still running, let computer move
					if (gameRunning) {
						locateOpenSquareInsertValue(board, computerSelection, userSelection);
						winner = gameEvaluator(board, userSelection);
						if (winner != null) {
							//gameRunning = false;
							break;
						}
						
					} else if (gameRunning) {
						// Else is board full?
						fullBoard = isBoardFull(board);
						// board is full, stop the game
						if (fullBoard) {
							boardFullPrompt();
							//gameRunning = false;
							break;
						}
					}

				} while (gameRunning);
				break;
			case "end":
				gameOverPrompt();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private List<String> getPlayerTokens() {
	
		List<String> tokens = new ArrayList<String>();
		tokens.add("X");
		tokens.add("O");
		return tokens;
	}

	private boolean isBoardFull(Board board) {
		if (!board.emptySquaresExist()) {
			return board.emptySquaresExist();
		}
		return !board.emptySquaresExist();
	}
	
	private void gameWinner(String gameWinner) {
			playerWinnerPrompt(gameWinner); 
	}

	private void boardFullPrompt() {
		System.out.println("the board is full, Game Over!");
	}

	private void playerWinnerPrompt(String winningPlayer) {
		System.out.println("Player "+winningPlayer+" Wins!");
		
	}

	private void gameOverPrompt() {
		System.out.println("Game Over");
	}



	public Player userPlayerSelection() {
		Player player;
		boolean userSelectionControl = true;
		while (userSelectionControl) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String input;
				input = br.readLine();
				if (input != null && input.equals("1")) {
					String playerRaw = getTokenX();
					player = new Player(playerRaw);
					return player;
				} else if (input != null && input.equals("2")) {
					String playerRaw = getTokenO();
					player = new Player(playerRaw);
					return player;
				} else {
					System.out.println("Your player selection was incorrect, please select 1 or 2.");
					continue;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public Player getComputerSelection(Player player) {
		Player assignedComputerPlayer = assignComputerPlayerToken(player);
		return assignedComputerPlayer;
	}

	private Player assignComputerPlayerToken(Player player) {
		Player computerPlayer;
		String playerX = getTokenX();
		String playerO = getTokenO();
		if (player.getToken().equals(playerX)) {
			computerPlayer = new Player(playerO);
			return computerPlayer;
		} else if (player.getToken().equals(playerO)) {
			computerPlayer = new Player(playerX);
			return computerPlayer;
		}
		return null;
	}

	
	
	private void addUserInputToBoard(String userInputMove, Board board, Player player) {
		String userSelection = player.getToken();
		boolean userMoveComplete = false;
		
		while (!userMoveComplete) {
			
			Integer id = Integer.parseInt(userInputMove);
			int row = (id - 1) / board.getLength();
			int column = (id - 1) % board.getLength();
			
			boolean emptySquare = board.isSquareEmpty(row, column);
			
			if (emptySquare == true) {
				
				board.setPlayerToken(row, column, userSelection);
				
				userMoveComplete = true;
			
			} else {
				System.out.println("You cannot move into that square");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
				try {
				userInputMove = br.readLine();
				
				br.close();
				
				} catch (IOException e) {
					System.out.println("There is an error with your input.");
				}
			}
		}
	}

	
	public void locateOpenSquareInsertValue(Board board, Player computerSelection, Player userSelection) {
		String playerTwo = computerSelection.getToken();
		boolean emptySpaceFound = false;
		
		while (!emptySpaceFound) {

			int row = ThreadLocalRandom.current().nextInt(0, board.getLength());
			int column = ThreadLocalRandom.current().nextInt(0, board.getLength());

			if (board.isSquareEmpty(row, column)) {
				board.setPlayerToken(row, column, playerTwo);
				emptySpaceFound = true;
			}
		}
	}

	
	
	private void checkUserInputAddToBoard(Player userSelection, Board board) {
		userMovePrompt();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String userMoveInput = br.readLine();
			if (userMoveInput != null && userMoveInput.length() == 1) {
				addUserInputToBoard(userMoveInput, board, userSelection);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public String checkAllColumnsForWinner(Board board, List<Integer> columns, List<String> tokens) {
		String playerWinner;
			
			for (int x = 0; x < columns.size(); x++) {
				int column = columns.get(x);
				playerWinner = checkColumnForWinner(board, column, tokens);

				if(playerWinner != null) {
					return playerWinner;
				}
			
		}
		return null;
	}
	
	public String checkAllRowsForWinner(Board board, List<Integer> rowList, List<String> tokens) {
		String playerWinner;
		for (int i = 0; i < rowList.size(); i++) {
			int row = rowList.get(i);
			playerWinner = checkRowForWinner(board, row, tokens);
			if (playerWinner != null)
			{
				return playerWinner;
			}
		}
		return null;
	}

	public String gameEvaluator(Board board, Player player) {
		String playerWinner;
		
		List<String> tokens = getPlayerTokens();	
		
		playerWinner = checkColumnsAndRowsForWinnerIncrement(board, tokens);
		if (playerWinner != null) {
			return playerWinner;
		}

				
		return null;

	}
	 		
	
	//--------------------------------------------Increment Functions Begin-----------------------------------------
	public String checkColumnsAndRowsForWinnerIncrement(Board board, List<String> playerTokens) {
		int increment = 1;
		String playerWinner;
		Position startPoint;

		// error with Position object - need to investigate
		// Checking columns
		for (int i = 0; i < board.getLength(); i++) {
			int col = i;
			int row = 0;
			startPoint = new Position(row, col);
			// todo : (minor) increment should be defined here
			playerWinner = checkColSquares(board, playerTokens, startPoint, increment);
			if (playerWinner != null) {
				return playerWinner;
			}
		}

		// Checking rows
		for (int i = 0; i < board.getLength(); i++) {
			int row = i;
			int col = 0;
			startPoint = new Position(row, col);
			playerWinner = checkRowSquares(board, playerTokens, startPoint, increment);
			if (playerWinner != null) {
				return playerWinner;
			}
		}

			int row = 0;
			int col = 0;
			startPoint = new Position(row, col);
			playerWinner = checkLeftDiagonalColSquares(board, playerTokens, startPoint, increment);
			if (playerWinner != null ) {
				return playerWinner;
		}
			
		row = 0;
		// todo : what if the board size is 4 or 100? 2 should not be hardcoded
		col = 2;
		startPoint = new Position(row, col);
		playerWinner = checkRightDiagonalColSquares(board, playerTokens, startPoint, increment);
		if (playerWinner != null ) {
			return playerWinner;
		}
		
			return null;
			
	}

	// todo: checkColSquares and checkRowSquares should be one function, checkLine, that takes
	// a start position, row increment, column increment
	public String checkColSquares(Board board, List<String> tokens, Position startPoint, int increment) {
		boolean isWinner;
		for (int i = 0; i < tokens.size(); i++) {
			String playerToken = tokens.get(i);
			isWinner = checkColWinnerIncrement(board, playerToken, startPoint, increment);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}
	
	
	public String checkRowSquares(Board board, List<String> tokens, Position startPoint, int increment) {
		for (int i = 0; i < tokens.size(); i++) {
			String playerToken = tokens.get(i);
			boolean isWinner = checkRowWinnerIncrement(board, playerToken, startPoint, increment);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}
	
	public String checkLeftDiagonalColSquares(Board board, List<String> tokens, Position startPoint, int increment) {
		for (int i = 0; i < tokens.size(); i++) {
			String playerToken = tokens.get(i);
			boolean isWinner = checkLeftDiagonalColWinnerIncrement(board, playerToken, startPoint, increment);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}

	public String checkRightDiagonalColSquares(Board board, List<String> tokens, Position startPoint, int increment) {
		for (int i = 0; i < tokens.size(); i++) {
			String playerToken = tokens.get(i);
			boolean isWinner = checkRightDiagonalColWinnerIncrement(board, playerToken, startPoint, increment);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}

	public boolean checkColWinnerIncrement(Board board, String playerToken, Position startPoint, int increment) {
		int row = startPoint.getRow();
		int col = startPoint.getColumn();
		
		while (row < board.getLength()) {
			if (!board.getToken(row, col).equals(playerToken)) {
				return false;
			} else {
				row = row + increment;
			}		
		}
		return true;
	}
	
	public boolean checkRowWinnerIncrement(Board board, String playerToken, Position startPoint, int increment) {
		int row = startPoint.getRow();
		int col = startPoint.getColumn();
		while (col < board.getLength()) {
			if (!board.getToken(row, col).equals(playerToken)) {
				return false;
			} else {
				col = col + increment;
			}
		}
		return true;
	}
	
	public boolean checkLeftDiagonalColWinnerIncrement(Board board, String playerToken, Position startPoint, int increment) {
		int row = startPoint.getRow();
		int col = startPoint.getColumn();
		while (row < board.getLength()) {
			if (!board.getToken(row, col).equals(playerToken)) {
				return false;
			} else {
				col = col + increment;
				row = row + increment;
			}
		}
		return true;
	}
	
	public boolean checkRightDiagonalColWinnerIncrement(Board board, String playerToken, Position startPoint, int increment) {
		int row = startPoint.getRow();
		int col = startPoint.getColumn();
		while (row < board.getLength()) {
			if (!board.getToken(row, col).equals(playerToken)) {
				return false;
			} else {
				col = col - increment;
				row = row + increment;
			}
		}
		return true;
	}

	
	//---------------------------------------------Increment Functions End--------------------------------------------
	

	public boolean columnMatches(Board board, int colToCheck, String tokenToMatch) {
		
		for (int row = 0; row < board.getLength(); row++) {
			if (!board.getToken(row, colToCheck).equals(tokenToMatch)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean rowMatches(Board board, int rowToCheck, String tokenToMatch) {				

		// Verify every column matches, abort if you find a nonmatch
		for (int col = 0; col < board.getLength(); col++) {
			if (!board.getToken(rowToCheck, col).equals(tokenToMatch)) {
				return false;
			}
		}
		
		// Every column matches tokenToMatch
		return true;
	}
	
	
	
	
	public boolean leftDiagonalColumnMatches(Board board, String tokenToMatch) {
		for (int row = 0; row < board.getLength(); row++) {
			int col = row;
			if (!board.getToken(row, col).equals(tokenToMatch)) {
					return false;
				}
			}
		return true;
	}
	

	public boolean rightDiagonalColumnMatches(Board board, String tokenToMatch) {
		int col = 2;
		for (int row = 0; row < board.getLength(); row++) {
			if (!board.getToken(row, col).equals(tokenToMatch)) {
				return false;
			}
			col = col - 1;
		}
		return true;
	}
	
	public String checkRightDiagonalColumnForWinner(Board board, List<String> playerTokens) {
		for (int i = 0; i < playerTokens.size(); i++) {
			String playerToken = playerTokens.get(i);
			boolean isWinner = rightDiagonalColumnMatches(board, playerToken);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}
	
	public String checkLeftDiagonalColumnForWinner(Board board, List<String> playerTokens) {
		for (int i = 0; i < playerTokens.size(); i++) {
			String playerToken = playerTokens.get(i);
			boolean isWinner = leftDiagonalColumnMatches(board, playerToken);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
		
	}
	
	public String checkColumnForWinner(Board board, int columnToCheck, List<String> playerTokens) {
		
		//Check for each player token
		for (int i = 0; i < playerTokens.size(); i++) {
			String playerToken = playerTokens.get(i);
			
			//Is this player a winner?
			boolean isWinner = columnMatches(board, columnToCheck, playerToken);
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}
	
	public String checkRowForWinner(Board board, int rowToCheck, List<String> playerTokens)
	{
		// Check for each player token
		for (int i = 0; i < playerTokens.size(); i++) {
			String playerToken = playerTokens.get(i);

			// Is this player a winner?
			boolean isWinner = rowMatches(board, rowToCheck, playerToken);		
			if (isWinner) {
				return playerToken;
			}
		}
		return null;
	}
		

	private static void userPlayerSelectionPrompt() {
		System.out.println(
				"Please select your desired player. Player 1 will be X. To select player 1 type 1 and press enter."+
		" "+"Player 2 will be O. To be player 2 type 2 and press enter.");
	}

	private void userMovePrompt() {
		System.out.println("Please enter the placeholder value of the square you would like to move into.");

	}

	private static void menuPrompt() {
		System.out.println("Enter Start to begin a game enter.");
	}
}
