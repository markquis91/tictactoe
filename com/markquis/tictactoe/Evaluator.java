package com.markquis.tictactoe;

import java.util.List;

public class Evaluator {
	
		public String checkForWinner(Board board) {
			String playerWinner = checkWinningSquares(board);
			if (playerWinner != null) {
				return playerWinner;
			}
			return null;
		}

		public String checkWinningSquares(Board board) {
			String playerWinner;
			playerWinner = iterateColumnSquares(board);
			if (playerWinner != null) {
				return playerWinner;
			}
			
			playerWinner = iterateRowSquares(board);
			if (playerWinner != null) {
				return playerWinner;
			}
			
			playerWinner = iterateLeftDiagonalColumnSquares(board);
			if (playerWinner != null) {
				return playerWinner;
			}
			
			playerWinner = iterateRightDiagonalColumnSquares(board);
			if (playerWinner != null) {
				return playerWinner;
			}
			return null;
		}

		
		public String iterateColumnSquares(Board board) {
			for (int column = 0; column < Board.getLength(); column++) {
				int row = 0;
				Position startPoint = new Position(row, column);
				String playerWinner = getTokensAndCheckColumnsForWinner(startPoint, board);
				if (playerWinner != null) {
					return playerWinner;
				}
			}
			return null;
		}
		
		public String iterateRowSquares(Board board) {
			for (int row = 0; row < Board.getLength(); row++) {
				int column = 0;
				Position startPoint = new Position(row, column);
				String playerWinner = getTokensAndCheckRowsForWinner(startPoint, board);
				if (playerWinner != null) {
					return playerWinner;
				}
			}
			return null;
		}
		
		public String iterateLeftDiagonalColumnSquares(Board board) {
			int row = 0;
			int column = 0;
			Position startPoint = new Position(row, column);
			String playerWinner = getTokensAndCheckLeftDiagonalColumnSquares(startPoint, board);
			if (playerWinner != null) {
				return playerWinner;
			}
			return null;
		}
		
		public String iterateRightDiagonalColumnSquares(Board board) {
			int row = 0;
			int column = Board.getLength() - 1;
			Position startPoint = new Position(row, column);
			String playerWinner = getTokensCheckRightDiagonalColumnSquares(startPoint, board);
			if (playerWinner != null ) {
				return playerWinner;
			}
			return null;
		}

		/*
		// todo: checkColSquares and checkRowSquares should be one function, checkLine, that takes
		// a start position, row increment, column increment
		public String checkSquares(List<String> tokens, Position startPoint) {
			boolean isWinner;
			for (int i = 0; i < tokens.size(); i++) {
				String playerToken = tokens.get(i);
				isWinner = checkColWinnerIncrement(playerToken, startPoint);
				if (isWinner) {
					return playerToken;
				}
				isWinner = checkRowWinnerIncrement(playerToken, startPoint);
				if (isWinner) {
					return playerToken;
				}
			}
			return null;
		}
	*/

		public String getTokensAndCheckColumnsForWinner(Position startPoint, Board board) {
			List<String> tokens = Player.getPlayerTokens();
			for (String token : tokens) {
				String playerToken = token;
				boolean isWinner = checkColumnsForWinner(playerToken, startPoint, board);
				if (isWinner) {
					return playerToken;
				}
			}
			return null;
		}	
		
		public String getTokensAndCheckRowsForWinner(Position startPoint, Board board) {
			List<String> tokens = Player.getPlayerTokens();
			for (String token : tokens) {
				String playerToken = token;
				boolean isWinner = checkRowsForWinner(playerToken, startPoint, board);
				if (isWinner) {
					return playerToken;
				}
			}
			return null;
		}
		
		public String getTokensAndCheckLeftDiagonalColumnSquares(Position startPoint, Board board) {
			List<String> tokens = Player.getPlayerTokens();
			for (String token : tokens) {
				String playerToken = token;
				boolean isWinner = checkLeftDiagonalColumnsForWinner(playerToken, startPoint, board);
				if (isWinner) {
					return playerToken;
				}
			}
			return null;
		}

		public String getTokensCheckRightDiagonalColumnSquares(Position startPoint, Board board) {
			List<String> tokens = Player.getPlayerTokens();
			for (String token : tokens) {
				String playerToken = token;
				boolean isWinner = checkRightDiagonalColumnsForWinner(playerToken, startPoint, board);
				if (isWinner) {
					return playerToken;
				}
			}
			return null;
		}

		public boolean checkColumnsForWinner(String playerToken, Position startPoint, Board board) {
			int row = startPoint.getRow();
			int col = startPoint.getColumn();
			while (row < Board.getLength()) {
				if (!board.getToken(row, col).equals(playerToken)) {
					return false;
				} else {
					++row;
				}		
			}
			return true;
		}
		
		public boolean checkRowsForWinner(String playerToken, Position startPoint, Board board) {
			int row = startPoint.getRow();
			int column = startPoint.getColumn();
			while (column < Board.getLength()) {
				if (!board.getToken(row, column).equals(playerToken)) {
					return false;
				} else {
					++column;
				}
			}
			return true;
		}
		
		public boolean checkLeftDiagonalColumnsForWinner(String playerToken, Position startPoint, Board board) {
			int row = startPoint.getRow();
			int column = startPoint.getColumn();
			while (row < Board.getLength()) {
				if (!board.getToken(row, column).equals(playerToken)) {
					return false;
				} else {
					++column;
					++row;
				}
			}
			return true;
		}
		
		public boolean checkRightDiagonalColumnsForWinner(String playerToken, Position startPoint, Board board) {
			int row = startPoint.getRow();
			int column = startPoint.getColumn();
			while (row < Board.getLength()) {
				if (!board.getToken(row, column).equals(playerToken)) {
					return false;
				} else {
					--column;
					++row;
				}
			}
			return true;
		}
		

}
