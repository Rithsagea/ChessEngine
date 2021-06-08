package chess.move;

import chess.Board;
import chess.ColorType;
import chess.Location;

public class EnPassantMove extends Move {

	public EnPassantMove(Board board, Location start, Location end) {
		super(board, start, end, false);
	}
	
	@Override
	public void executeMove(Board board) {
		if(board.getSideToMove() == ColorType.WHITE) { 
			board.setPiece(board.getEnPassant().add(1, 0), null);
		} else {
			board.setPiece(board.getEnPassant().add(-1, 0), null);
		}
		super.executeMove(board);
	}

}
