package test.chess;

import java.util.ArrayList;

import chess.Board;
import chess.Location;
import chess.Move;
import chess.Piece;

public class BoardTest {
	public static void main(String[] args) {
		Board board = new Board("r2q1r1k/pb4p1/1p1b1pp1/2p5/2Bn3Q/2N5/PP3PPP/3RR1K1 b - - 1 19");
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
