package chess;

public class Board {
	
	public static final int MAX_RANKS = 8;
	public static final int MAX_FILES = 8;
	
	private static final PieceType[] BACK_RANK = {
			PieceType.ROOK,
			PieceType.KNIGHT,
			PieceType.BISHOP,
			PieceType.QUEEN,
			PieceType.KING,
			PieceType.BISHOP,
			PieceType.KNIGHT,
			PieceType.ROOK
	};
	
	private Piece[][] board;
	
	//misc
	private boolean whiteCastleKing = true;
	private boolean whiteCastleQueen = true;
	private boolean blackCastleKing = true;
	private boolean blackCastleQueen = true;
	
	public Board() {
		board = new Piece[MAX_RANKS][MAX_FILES];
		
		//setup board with default pieces
		for(int x = 0; x < 8; x++) {
			board[0][x] = new Piece(BACK_RANK[x], ColorType.WHITE);
			board[7][x] = new Piece(BACK_RANK[x], ColorType.BLACK);
			
			board[1][x] = new Piece(PieceType.PAWN, ColorType.WHITE);
			board[6][x] = new Piece(PieceType.PAWN, ColorType.BLACK);
		}
	}
	
	public Piece getPiece(int rank, int file) {
		return board[rank][file];
	}
	
	public boolean canCastleKing(ColorType color) {
		if(color == ColorType.WHITE) {
			return whiteCastleKing;
		}
		
		return blackCastleKing;
	}
	
	public boolean canCastleQueen(ColorType color) {
		if(color == ColorType.WHITE) {
			return whiteCastleQueen;
		}
		
		return blackCastleQueen;
	}
}
