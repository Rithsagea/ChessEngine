package chess;

/**
 * An Immutable type representing a piece on the chess board.
 * TODO: add a check for pawn promotion, as promoted pieces
 * should only be worth a single point
 * @author rithsagea
 *
 */
public class Piece {
	private PieceType type;
	private ColorType color;
	
	/**
	 * Creates a new chess piece
	 * @param type the type of the piece
	 * @param color the color of the piece
	 */
	public Piece(PieceType type, ColorType color) {
		this.type = type;
		this.color = color;
	}
	
	public Piece(char c) {
		this.type = PieceType.getPiece(Character.toLowerCase(c));
		this.color = Character.isUpperCase(c) ? ColorType.WHITE : ColorType.BLACK;
	}
	
	/**
	 * Gets the type of chess piece
	 * @return the type of the piece
	 */
	public PieceType getType() {
		return type;
	}
	
	/**
	 * Gets the color of the chess piece
	 * @return the color of the chess piece
	 */
	public ColorType getColor() {
		return color;
	}
	
	public String toString() {
		if(color == ColorType.WHITE) return type.toString().toUpperCase();
		else return type.toString();
	}
}
