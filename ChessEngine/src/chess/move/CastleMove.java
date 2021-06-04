package chess.move;

import chess.Board;
import chess.ColorType;
import chess.Location;

public class CastleMove extends Move {

	public CastleMove(Board board, Location start, Location end, boolean longCastle) {
		super(board, start, end, false);
		
		//check whole row cuz pain here
		
		if(piece.getColor() == ColorType.WHITE) {
			if(longCastle) {
				checkSquare(start.add(0, -1));
			} else {
				checkSquare(start.add(0, 1));
			}
		} else {
			if(longCastle) {
				checkSquare(start.add(0, 1));
			} else {
				checkSquare(start.add(0, -1));
			}
		}
	}
	
	@Override
	public void executeMove(Board board) {
		//pain
	}

}
