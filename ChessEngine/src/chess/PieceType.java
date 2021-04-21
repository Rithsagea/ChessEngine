package chess;

/**
 * These represent different types of pieces that exist on a chessboard.
 * We could get away with just using an int, but by representing them
 * as enums, it's much more concise.
 * 
 * @author rithsagea
 */
public enum PieceType {
	KING,
	QUEEN,
	BISHOP,
	KNIGHT,
	ROOK,
	PAWN;
}
