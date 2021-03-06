package chess.move;

import chess.Board;
import chess.ColorType;
import chess.Location;
import chess.Piece;
import chess.PieceType;

public class Move {

	protected boolean legal;
	protected boolean enPassant;
	protected Piece capture;
	protected Piece piece;
	protected Location start;
	protected Location end;
	
	protected Board board;
	
	/**
	 * Initializes a new chess move
	 * 
	 * TODO: Make it so that it automatically detects check captures and mate
	 * 
	 * @param piece		the piece that is moving
	 * @param start		where the piece starts from
	 * @param end		where the piece ends up
	 * @param enPassant	if this move is a double pawn move
	 */
	public Move(Board board, Location start, Location end, boolean enPassant) {
		this.start = start;
		this.end = end;
		this.enPassant = enPassant;
		this.board = board;
		
		piece = board.getPiece(start);
		capture = board.getPiece(end);
		
		if((piece != null && capture != null && capture.getColor() == piece.getColor()) ||
				!(start.isValid() && end.isValid())) {
			legal = false;
			return;
		}
		
		checkSquare(end);
	}
	
	protected void checkSquare(Location loc) {
		Piece temp = board.getPiece(loc);
		
		board.setPiece(loc, piece);
		board.setPiece(start, null);
		
		legal = !board.isCheck(board.getSideToMove());
		board.setPiece(loc, temp);
		board.setPiece(start, piece);
	}
	
	/**
	 * @return whether this move is a capture
	 */
	public Piece getCapture() {
		return capture;
	}
	
	/**
	 * @return the piece that is moving
	 */
	public Piece getPiece() {
		return piece;
	}
	
	/**
	 * @return where the piece starts from
	 */
	public Location getStart() {
		return start;
	}
	
	/**
	 * @return where the piece ends up
	 */
	public Location getEnd() {
		return end;
	}
	
	/**
	 * Gets the notation of this move in SAN (simplified algebraic notation)
	 * @return this move's notation
	 */
	public String getNotation() {
		return (piece.getType() == PieceType.PAWN ? "" : piece.getType().toString().toUpperCase())
				+ start + (capture != null ? "x" : "-") + end; 
		//TODO: add '+' for check and '#' for mate
	}
	
	/**
	 * @return if this move is legal
	 */
	public boolean isLegal() {
		return legal;
	}
	
	/**
	 * @return if this move is a double pawn move
	 */
	public boolean isEnPassant() {
		return enPassant;
	}
	
	/**
	 * Executes this move on the board passed in
	 * @param board the board to make this move on
	 */
	public void executeMove(Board board) {
		board.setPiece(end, piece);
		board.setPiece(start, null);
		
		if(enPassant) {
			if(piece.getColor() == ColorType.WHITE) 
				board.setEnPassant(new Location(5, start.getFile()));
			if(piece.getColor() == ColorType.BLACK)
				board.setEnPassant(new Location(2, start.getFile()));
		} else {
			board.setEnPassant(null);
		}
	}
	
	public String toString() {
		return getNotation();
	}
}
