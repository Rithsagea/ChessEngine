package test.chess;

import chess.Board;
import chess.Location;
import chess.Piece;

public class BoardTest {
	public static void main(String[] args) {
		Board board = new Board();
		Piece p;
		for(int r = 0; r < Board.MAX_RANKS; r++) {
			for(int f = 0; f < Board.MAX_FILES; f++) {
				p = board.getPiece(new Location(r, f));
				System.out.print(p == null ? " " : p);
			}
			System.out.println();
		}
		
		System.out.print(board);
	}
}
