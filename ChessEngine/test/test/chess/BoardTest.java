package test.chess;

import java.util.ArrayList;

import chess.Board;
import chess.Location;
import chess.Move;
import chess.Piece;

public class BoardTest {
	public static void main(String[] args) {
		Board board = new Board("2k3r1/pppq4/2n2p2/2b1pp2/Q3P2r/2NP1PKB/PP6/R4R2 w - - 2 24");
		Piece p;
		for(int r = 0; r < Board.MAX_RANKS; r++) {
			for(int f = 0; f < Board.MAX_FILES; f++) {
				p = board.getPiece(new Location(r, f));
				System.out.print(p == null ? " " : p);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println(board);
		System.out.println("Is Check: " + board.isCheck(board.getSideToMove()));
		
		ArrayList<Move> moves = board.getValidMoves();
		
		for(Move move : moves) {
			System.out.println(move);
		}
	}
}
