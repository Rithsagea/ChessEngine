package chess;

public class Move {

	private boolean legal;
	private boolean check;
	private boolean mate;
	private Piece capture;
	private Piece piece;
	private Location start;
	private Location end;
	
	/**
	 * Initializes a new chess move
	 * 
	 * TODO: Make it so that it automatically detects check captures and mate
	 * 
	 * @param check		whether this move results in a check
	 * @param mate		whether this move results in checkmate
	 * @param piece		the piece that is moving
	 * @param start		where the piece starts from
	 * @param end		where the piece ends up
	 */
	public Move(boolean check, boolean mate, Piece piece, Location start, Location end, Board board) {
		this.check = check;
		this.mate = mate;
		this.piece = piece;
		this.start = start;
		this.end = end;
		
		capture = board.getPiece(end);
		legal = false; // TODO: fix this
	}
	
	/**
	 * @return whether this move results in a check
	 */
	public boolean isCheck() {
		return check;
	}
	
	/**
	 * @return whether this move is a capture
	 */
	public Piece getCapture() {
		return capture;
	}
	
	/**
	 * @return whether this move results in checkmate
	 */
	public boolean isMate() {
		return mate;
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
		return piece.toString() + start + (capture != null ? "x" : "-") + end
				+ (check ? "+" : (mate ? "#" : null)); //TODO: redo this later lol
	}
	
	/**
	 * @return if this move is legal
	 */
	public boolean isLegal() {
		return legal;
	}
	
	/**
	 * Executes this move on the board passed in
	 * @param board the board to make this move on
	 */
	public void executeMove(Board board) {
		board.setPiece(end, board.getPiece(start));
		board.setPiece(start, null);
	}
	
	public void takebackMove(Board board) {
		board.setPiece(end, capture);
		board.setPiece(start, piece);
	}
}
