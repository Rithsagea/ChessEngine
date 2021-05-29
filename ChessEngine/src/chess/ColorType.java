package chess;

/**
 * Represents different colored pieces on the chess board.
 * Maybe add red green blue and yellow if we want 4 player chess.
 * 
 * @author rithsagea
 *
 */
public enum ColorType {
	BLACK("b"),
	WHITE("w");
	
	private final String symbol;
	ColorType(String symbol) {
		this.symbol = symbol;
	}
	
	public String toString() {
		return symbol;
	}
	
	public static ColorType getInverse(ColorType color) {
		if(color == BLACK) return WHITE;
		if(color == WHITE) return BLACK;
		
		return null;
	}
}
