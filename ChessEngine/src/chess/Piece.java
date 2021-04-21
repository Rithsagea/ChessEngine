package chess;

public class Piece {
	private PieceType type;
	private ColorType color;
	
	public Piece(PieceType type, ColorType color) {
		this.type = type;
		this.color = color;
	}
	
	public PieceType getType() {
		return type;
	}
	
	public ColorType getColor() {
		return color;
	}
}
