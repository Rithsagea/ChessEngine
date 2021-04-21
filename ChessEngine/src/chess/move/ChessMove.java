package chess.move;

import chess.Board;
import chess.Location;
import chess.PieceType;

public class ChessMove implements Move {

	private boolean check;
	private PieceType piece;
	private Location start;
	private Location end;
	
	public ChessMove(boolean check, PieceType piece, Location start, Location end) {
		this.check = check;
		this.piece = piece;
		this.start = start;
		this.end = end;
	}
	
	public boolean isCheck() {
		return check;
	}
	
	public PieceType getPiece() {
		return piece;
	}
	
	public Location getStart() {
		return start;
	}
	
	public Location getEnd() {
		return end;
	}
	
	@Override
	public String getNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLegal(Board board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void executeMove(Board board) {
		// TODO Auto-generated method stub
		
	}
}
