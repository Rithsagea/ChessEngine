package chess;

import chess.move.Move;

public class Board {
	
	public static final int MAX_RANKS = 8;
	public static final int MAX_FILES = 8;
	public static final String START_FEN = "rnbkqbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBKQBNR w KQkq - 0 0";
	
	private Piece[][] board;
	private ColorType sideToMove;
	
	//misc
	private boolean whiteCastleKing = false;
	private boolean whiteCastleQueen = false;
	private boolean blackCastleKing = false;
	private boolean blackCastleQueen = false;
	
	private Move enPassant = null;
	private int halfMoves = 0;
	private int fullMoves = 0;
	
	public Board() {
		this(START_FEN);
	}
	
	public Board(String fen) {
		board = new Piece[MAX_RANKS][MAX_FILES];
		String[] data = fen.split("[ \\/]");
		int f;
		for(int r = 0; r < MAX_RANKS; r++) {
			f = 0;
			for(char c : data[r].toCharArray()) {
				if(c >= '0' && c <= '9') {
					f += c - '0';
				} else {
					board[r][f] = new Piece(c);
					f++;
				}
			}
		}
		
		if(data[8].equals("b")) sideToMove = ColorType.BLACK;
		else sideToMove = ColorType.WHITE;
		
		if(data[9].contains("K")) whiteCastleKing = true;
		if(data[9].contains("Q")) whiteCastleQueen = true;
		if(data[9].contains("k")) blackCastleKing = true;
		if(data[9].contains("q")) blackCastleQueen = true;
		
		//en passant
		
		halfMoves = Integer.parseInt(data[11]);
		fullMoves = Integer.parseInt(data[12]);
	}
	
	public Piece getPiece(int rank, int file) {
		return board[rank][file];
	}
	
	public ColorType getSideToMove() {
		return sideToMove;
	}
	
	/**
	 * @return if the white player can castle king side
	 */
	public boolean whiteCastleKing() {
		return whiteCastleKing;
	}
	
	/**
	 * @return if the white player can castle queen side
	 */
	public boolean whiteCastleQueen() {
		return whiteCastleQueen;
	}
	
	/**
	 * @return if the black player can castle king side
	 */
	public boolean blackCastleKing() {
		return blackCastleKing;
	}
	
	/**
	 * @return if the black player can castle queen side
	 */
	public boolean blackCastleQueen() {
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
		
		b.append(' ');
		StringBuilder c = new StringBuilder();
		if(board.whiteCastleKing) c.append(PieceType.KING.toString().toUpperCase());
		if(board.whiteCastleQueen) c.append(PieceType.QUEEN.toString().toUpperCase());
		if(board.blackCastleKing) c.append(PieceType.KING.toString());
		if(board.blackCastleQueen) c.append(PieceType.QUEEN.toString());
		if(c.length() > 0) {
			b.append(c);
		} else {
			b.append('-');
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
