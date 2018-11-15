
public class Position {
	
	/*
	 * This position class is for an object that holds the row and column of a board space.
	 */
	
	private int row;
	private int column;
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
}
