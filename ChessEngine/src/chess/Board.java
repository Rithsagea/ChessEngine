package chess;

import chess.move.Move;

public class Board {
	
	public static final int MAX_RANKS = 8;
	public static final int MAX_FILES = 8;
	
	private static final PieceType[] BACK_RANK = {
			PieceType.ROOK,
			PieceType.KNIGHT,
			PieceType.BISHOP,
			PieceType.KING,
			PieceType.QUEEN,
			PieceType.BISHOP,
			PieceType.KNIGHT,
			PieceType.ROOK
	};
	
	private Piece[][] board;
	private ColorType sideToMove;
	
	//misc
	private boolean whiteCastleKing = true;
	private boolean whiteCastleQueen = true;
	private boolean blackCastleKing = true;
	private boolean blackCastleQueen = true;
	
	private Move enPassant = null;
	private int halfMoves = 0;
	private int fullMoves = 0;
	
	public Board() {
		board = new Piece[MAX_RANKS][MAX_FILES];
		sideToMove = ColorType.WHITE; //white moves first
		
		//setup board with default pieces
		for(int x = 0; x < 8; x++) {
			board[0][x] = new Piece(BACK_RANK[x], ColorType.BLACK);
			board[7][x] = new Piece(BACK_RANK[x], ColorType.WHITE);
			
			board[1][x] = new Piece(PieceType.PAWN, ColorType.BLACK);
			board[6][x] = new Piece(PieceType.PAWN, ColorType.WHITE);
		}
	}
	
	public Piece getPiece(int rank, int file) {
		return board[rank][file];
	}
	
	public ColorType getSideToMove() {
		return sideToMove;
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
	
	public Move getEnPassant() {
		return enPassant;
	}
	
	public String toString() {
		return toFen(this);
	}
	
	public static String toFen(Board board) {
		StringBuilder b = new StringBuilder();
		
		Piece p;
		int e;
		for(int r = 0; r < Board.MAX_RANKS; r++) {
			e = 0;
			for(int f = 0; f < Board.MAX_FILES; f++) {
				p = board.getPiece(r, f);
				if(p == null) {
					e++;
				} else {
					if(e > 0) { // there are empty squares
						b.append(e);
						e = 0;
					}
					b.append(p);
				}
			}
			if(e > 0) b.append(e);
			if(r != Board.MAX_RANKS - 1) b.append('/');
		}
		
		b.append(' ');
		b.append(board.getSideToMove());
		
		StringBuilder c = new StringBuilder();
		if(board.canCastleKing(ColorType.WHITE)) c.append(PieceType.KING.toString().toUpperCase());
		if(board.canCastleQueen(ColorType.WHITE)) c.append(PieceType.QUEEN.toString().toUpperCase());
		if(board.canCastleKing(ColorType.BLACK)) c.append(PieceType.KING.toString());
		if(board.canCastleQueen(ColorType.BLACK)) c.append(PieceType.QUEEN.toString());
		if(c.length() > 0) {
			b.append(' ');
			b.append(c);
		}
		
		b.append(' ');
		b.append(board.enPassant == null ? '-' : board.enPassant);
		b.append(' ');
		b.append(board.halfMoves);
		b.append(' ');
		b.append(board.fullMoves);
		
		return b.toString();
	}
}
