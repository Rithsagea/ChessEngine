package chess;

/**
 * These represent different types of pieces that exist on a chessboard.
 * We could get away with just using an int, but by representing them
 * as enums, it's much more concise.
 * 
 * @author rithsagea
 */
public enum PieceType {
	KING("k"),
	QUEEN("q"),
	BISHOP("b"),
	KNIGHT("n"),
	ROOK("r"),
	PAWN("p");
	
	private final String symbol;
	
	PieceType(String symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		return symbol;
	}
	
	public static PieceType getPiece(char c) {
		switch(c) {
			case 'k': return KING;
			case 'q': return QUEEN;
			case 'b': return BISHOP;
			case 'n': return KNIGHT;
			case 'r': return ROOK;
			case 'p': return PAWN;
		}
		
		return null;
	}
}
