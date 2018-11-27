package com.markquis.tictactoe;

public class Position {
	
	/**
	 * This position class is for an object that holds the row and column of a board space.
	 */
	
	private int row;
	private int column;
	
	public Position(int row, int column) {
		setRow(row);
		setColumn(column);
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public void setRow(int row) throws GameExceptions {
			if (row < Board.getLength()) {
				this.row = row;
			}
	}

	public void setColumn(int column) {
		if (row < Board.getLength()) {
		this.column = column;
		}
	}
}
