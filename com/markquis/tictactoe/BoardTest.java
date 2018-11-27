package com.markquis.tictactoe;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	Board board = new Board();
	
	public void testStringInsertion() {
		String user = "X";
		assertEquals("X", user);
		
		assertTrue(board.isSquareEmpty(0, 0));
		
		board.setPlayerToken(0, 0, user);
		
		assertEquals("X", board.getToken(0, 0));
	}
}
