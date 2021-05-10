package test.chess;

import chess.Board;
import chess.ColorType;
import chess.Piece;
import chess.PieceType;

public class BoardTest {
	public static void main(String[] args) {
		Board board = new Board();
		Piece p;
		for(int r = Board.MAX_RANKS - 1; r >= 0; r--) {
			for(int f = 0; f < Board.MAX_FILES; f++) {
				p = board.getPiece(r, f);
				System.out.print(p == null ? " " : p.getType() == PieceType.PAWN ? "." : p.getType());
			}
			System.out.println();
		}
		
		System.out.println();
		for(int r = Board.MAX_RANKS - 1; r >= 0; r--) {
			for(int f = 0; f < Board.MAX_FILES; f++) {
				p = board.getPiece(r, f);
				System.out.print(p == null ? " " : p.getColor() == ColorType.WHITE ? "W" : "B");
			}
			System.out.println();
		}
	}
}
