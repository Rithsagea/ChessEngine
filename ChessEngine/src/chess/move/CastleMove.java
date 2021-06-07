package chess.move;

import chess.Board;
import chess.ColorType;
import chess.Location;
import chess.Piece;
import chess.PieceType;

public class CastleMove extends Move {

	private boolean longCastle;
	
	public CastleMove(Board board, Location start, Location end, boolean longCastle) {
		super(board, start, end, false);
		this.longCastle = longCastle;
		
		int r = piece.getColor() == ColorType.WHITE ? 7 : 0;
		if(longCastle) {
			for(int f = 0; f < start.getFile(); f++) {
				if(!checkPiece(board.getPiece(r, f), piece)) {
					legal = false;
					return;
				}
			}
			checkSquare(start.add(0, -1));
		} else {
			for(int f = 7; f >= start.getFile(); f--) {
				if(!checkPiece(board.getPiece(r, f), piece)) {
					legal = false;
					return;
				}
			}
			checkSquare(start.add(0, 1));
		}
	}
	
	@Override
	public void executeMove(Board board) {
		int r = piece.getColor() == ColorType.WHITE ? 7 : 0;
		board.setPiece(end, piece);
		board.setPiece(start, null);
		if(longCastle) {
			board.setPiece(new Location(r, 3), board.getPiece(new Location(r, 0)));
			board.setPiece(new Location(r, 0), null);
		} else {
			board.setPiece(new Location(r, 5), board.getPiece(new Location(r, 7)));
			board.setPiece(new Location(r, 7), null);
		}
	}
	
	@Override
	public String getNotation() {
		return longCastle ? "O-O-O" : "O-O";
	}
	
	private static boolean checkPiece(Piece check, Piece piece) {
		if(piece == null || check == null) return true;
		if(check.getColor() == piece.getColor() &&
				(check.getType() == PieceType.ROOK ||
				check.getType() == PieceType.KING)) return true;
		return false;
	}
}
