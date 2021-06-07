package test.chess;

import java.util.ArrayList;
import java.util.Scanner;

import chess.Board;
import chess.Location;
import chess.Piece;
import chess.move.Move;

public class BoardTest {
	
	private static Board board;
	
	private static void printBoard() {
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
	}
	
	public static void main(String[] args) {
		board = new Board("r3kbnr/pppqpppp/2n5/3p1b2/3P1B2/2N5/PPPQPPPP/R3KBNR w KQkq - 6 5");
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			printBoard();
			ArrayList<Move> moves = board.getValidMoves();
			for(int x = 0; x < moves.size(); x++) {
				System.out.println(x + ": " + moves.get(x));
			}
			System.out.print("Choose a move: ");
			int move = scanner.nextInt();
			board.makeMove(moves.get(move));
		}
	}
}
