package chess.move;

import chess.Board;
import chess.Location;
import chess.Piece;
import chess.PieceType;

public class PromoteMove extends Move {

	protected Piece promotePiece;
	
	public PromoteMove(Board board, Location start, Location end, PieceType promote) {
		super(board, start, end, false);
		promotePiece = new Piece(promote, piece.getColor());
	}
	
	@Override
	public void executeMove(Board board) {
		board.setPiece(end, promotePiece);
		board.setPiece(start, null);
		board.setEnPassant(null);
	}
	
	@Override
	public String getNotation() {
		return super.getNotation() + "=" + promotePiece.getType().toString().toUpperCase();
	}
}
