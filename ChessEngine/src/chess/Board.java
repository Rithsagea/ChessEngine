package chess;

import java.util.ArrayList;

public class Board {
	
	public static final int MAX_RANKS = 8;
	public static final int MAX_FILES = 8;
	public static final String START_FEN = "rnbkqbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBKQBNR w KQkq - 0 0";
	
	private Piece[][] board;
	private ColorType sideToMove;
	private ArrayList<Move> movesMade;
	
	//misc
	private boolean whiteCastleKing = false;
	private boolean whiteCastleQueen = false;
	private boolean blackCastleKing = false;
	private boolean blackCastleQueen = false;
	
	private Location enPassant = null;
	private int halfMoves = 0;
	private int fullMoves = 0;
	
	/**
	 * Initializes a new board from the default starting position
	 */
	public Board() {
		this(START_FEN);
	}
	
	/**
	 * Initializes a new board from the provided FEN notation
	 * @param fen the notation for the board
	 */
	public Board(String fen) {
		board = new Piece[MAX_RANKS][MAX_FILES];
		movesMade = new ArrayList<>();
		
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
		
		enPassant = Location.fromString(data[10]);
		
		halfMoves = Integer.parseInt(data[11]);
		fullMoves = Integer.parseInt(data[12]);
	}
	
	/**
	 * Gets the piece from a specified rank and file
	 * @param rank the rank of the piece (vertical)
	 * @param file the file of the piece (horizontal)
	 * @return the piece
	 */
	public Piece getPiece(Location loc) {
		if(!loc.isValid()) return null;
		return board[loc.getRank()][loc.getFile()];
	}
	
	/**
	 * Get piece which returns null if there is an error
	 * @param rank the rank of the piece
	 * @param file the file of the piece
	 * @return the piece gotten or null if the location is invalid
	 */
	public Piece getPiece(int rank, int file) {
		return getPiece(new Location(rank, file));
	}
	
	/**
	 * Sets the piece for a specified square
	 * @param rank the rank of the square (vertical)
	 * @param file the file of the square (horizontal)
	 * @param piece the piece to set the square to
	 */
	public void setPiece(Location loc, Piece piece) {
		board[loc.getRank()][loc.getFile()] = piece;
	}
	
	/**
	 * Gets which side should make the next move
	 * @return the side to move
	 */
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
	
	/**
	 * Sets the location for the next player's en passant
	 * @param loc a location (null if not a double pawn move)
	 */
	public void setEnPassant(Location loc) {
		enPassant = loc;
	}
	
	/**
	 * Returns the location for the next player's en passant
	 * @return a location (null if not a double pawn move)
	 */
	public Location getEnPassant() {
		return enPassant;
	}
	
	public boolean isCheck(ColorType color) {
		for(int rank = 0; rank < MAX_RANKS; rank++) {
			for(int file = 0; file < MAX_FILES; file++) {
				if(board[rank][file] != null && 
						board[rank][file].getType() == PieceType.KING &&
						board[rank][file].getColor() == color) {
					//vertical horizontal
					if(isCheckSideways(color, rank, file)) return true;
					if(isCheckDiagonal(color, rank, file)) return true;
					if(isCheckKnight(color, rank, file)) return true;
					if(isCheckMisc(color, rank, file)) return true;
				}
			}
		}
		
		return false;
	}
	
	//helper methods for detecting check
	private boolean isCheckSideways(ColorType color, int rank, int file) {
		for(int i = rank + 1; i < MAX_RANKS; i++) {
			if(board[i][file] != null) {
				if(board[i][file].getColor() == color) break;
				if(board[i][file].getType() == PieceType.ROOK ||
				   board[i][file].getType() == PieceType.QUEEN) return true;
			}
		}
		
		for(int i = rank - 1; i >= 0; i--) {
			if(board[i][file] != null) {
				if(board[i][file].getColor() == color) break;
				if(board[i][file].getType() == PieceType.ROOK ||
				   board[i][file].getType() == PieceType.QUEEN) return true;
			}
		}
		
		for(int i = file + 1; i < MAX_FILES; i++) {
			if(board[rank][i] != null) {
				if(board[rank][i].getColor() == color) break;
				if(board[rank][i].getType() == PieceType.ROOK ||
				   board[rank][i].getType() == PieceType.QUEEN) return true;
			}
		}
		
		for(int i = file - 1; i >= 0; i--) {
			if(board[rank][i] != null) {
				if(board[rank][i].getColor() == color) break;
				if(board[rank][i].getType() == PieceType.ROOK ||
				   board[rank][i].getType() == PieceType.QUEEN) return true;
			}
		}
		
		return false;
	}
	
	private boolean isCheckDiagonal(ColorType color, int rank, int file) {
		for(int i = rank + 1, j = file + 1; i < MAX_RANKS && j < MAX_FILES; i++, j++) {
			if(board[i][j] != null) {
				if(board[i][j].getColor() == color) break;
				if(board[i][j].getType() == PieceType.BISHOP ||
				   board[i][j].getType() == PieceType.QUEEN) return true;
			}
		}
		
		for(int i = rank + 1, j = file - 1; i < MAX_RANKS && j >= 0; i++, j--) {
			if(board[i][j] != null) {
				if(board[i][j].getColor() == color) break;
				if(board[i][j].getType() == PieceType.BISHOP ||
				   board[i][j].getType() == PieceType.QUEEN) return true;
			}
		}
		
		for(int i = rank - 1, j = file + 1; i >= 0 && j < MAX_FILES; i--, j++) {
			if(board[i][j] != null) {
				if(board[i][j].getColor() == color) break;
				if(board[i][j].getType() == PieceType.BISHOP ||
				   board[i][j].getType() == PieceType.QUEEN) return true;
			}
		}
		
		for(int i = rank - 1, j = file - 1; i >= 0 && j >= 0; i--, j--) {
			if(board[i][j] != null) {
				if(board[i][j].getColor() == color) break;
				if(board[i][j].getType() == PieceType.BISHOP ||
				   board[i][j].getType() == PieceType.QUEEN) return true;
			}
		}
		
		return false;
	}
	
	private boolean isCheckKnight(ColorType color, int rank, int file) {
		if(checkPiece(color, PieceType.KNIGHT, rank + 2, file + 1)) return true;
		if(checkPiece(color, PieceType.KNIGHT, rank - 2, file + 1)) return true;
		if(checkPiece(color, PieceType.KNIGHT, rank + 2, file - 1)) return true;
		if(checkPiece(color, PieceType.KNIGHT, rank - 2, file - 1)) return true;
		if(checkPiece(color, PieceType.KNIGHT, rank + 1, file + 2)) return true;
		if(checkPiece(color, PieceType.KNIGHT, rank - 1, file + 2)) return true;
		if(checkPiece(color, PieceType.KNIGHT, rank + 1, file - 2)) return true;
		if(checkPiece(color, PieceType.KNIGHT, rank - 1, file - 2)) return true;
		
		
		return false;
	}
	
	private boolean isCheckMisc(ColorType color, int rank, int file) {
		if(checkPiece(color, PieceType.KING, rank + 1, file + 1)) return true;
		if(checkPiece(color, PieceType.KING, rank - 1, file + 1)) return true;
		if(checkPiece(color, PieceType.KING, rank + 1, file - 1)) return true;
		if(checkPiece(color, PieceType.KING, rank - 1, file - 1)) return true;
		if(checkPiece(color, PieceType.KING, rank, file + 1)) return true;
		if(checkPiece(color, PieceType.KING, rank, file - 1)) return true;
		if(checkPiece(color, PieceType.KING, rank + 1, file)) return true;
		if(checkPiece(color, PieceType.KING, rank - 1, file)) return true;
		
		if(checkPiece(color, PieceType.PAWN, rank + (color == ColorType.WHITE ? -1 : 1), file + 1)) return true;
		if(checkPiece(color, PieceType.PAWN, rank + (color == ColorType.WHITE ? -1 : 1), file - 1)) return true;
		
		return false;
	}
	
	private boolean checkPiece(ColorType color, PieceType type, int rank, int file) {
		Piece piece = getPiece(rank, file);
		
		return piece != null && piece.getColor() != color && piece.getType() == type;
	}
	
	/**
	 * Gets a list of all valid moves
	 * @return
	 */
	public ArrayList<Move> getValidMoves() {
		ArrayList<Move> moves = new ArrayList<>();
		
		for(int rank = 0; rank < MAX_RANKS; rank++) {
			for(int file = 0; file < MAX_FILES; file++) {
				if(board[rank][file] != null &&
				   board[rank][file].getColor() == sideToMove) {
					switch(board[rank][file].getType()) {
						case KING:
							break;
						case QUEEN:
							getBishopMove(new Location(rank, file), moves);
							getRookMove(new Location(rank, file), moves);
							break;
						case BISHOP:
							getBishopMove(new Location(rank, file), moves);
							break;
						case KNIGHT:
							getKnightMove(new Location(rank, file), moves);
							break;
						case ROOK:
							getRookMove(new Location(rank, file), moves);
							break;
						case PAWN:
							getPawnMove(new Location(rank, file), moves);
							break;
					}
				}
			}
		}
		
		return moves;
	}
	
	//helper methods to get moves
	private void getBishopMove(Location loc, ArrayList<Move> moves) {
		for(int r = loc.getRank() - 1, f = loc.getFile() - 1; r >= 0 && f >= 0; r--, f--) {
			if(board[r][f] == null) {
				moves.add(new Move(this,  loc, new Location(r, f), false));
			} else {
				if(board[r][f].getColor() != sideToMove) {
					moves.add(new Move(this, loc, new Location(r, f), false));
					break;
				}
			}
		}
		
		for(int r = loc.getRank() + 1, f = loc.getFile() + 1; r < MAX_RANKS && f < MAX_FILES; r++, f++) {
			if(board[r][f] == null) {
				moves.add(new Move(this,  loc, new Location(r, f), false));
			} else {
				if(board[r][f].getColor() != sideToMove) {
					moves.add(new Move(this, loc, new Location(r, f), false));
					break;
				}
			}
		}
		
		for(int r = loc.getRank() - 1, f = loc.getFile() + 1; r >= 0 && f < MAX_FILES; r--, f++) {
			if(board[r][f] == null) {
				moves.add(new Move(this,  loc, new Location(r, f), false));
			} else {
				if(board[r][f].getColor() != sideToMove) {
					moves.add(new Move(this, loc, new Location(r, f), false));
					break;
				}
			}
		}
		
		for(int r = loc.getRank() + 1, f = loc.getFile() - 1; r < MAX_RANKS && f >= 0; r++, f--) {
			if(board[r][f] == null) {
				moves.add(new Move(this,  loc, new Location(r, f), false));
			} else {
				if(board[r][f].getColor() != sideToMove) {
					moves.add(new Move(this, loc, new Location(r, f), false));
					break;
				}
			}
		}
	}
	
	private void getRookMove(Location loc, ArrayList<Move> moves) {
		for(int r = loc.getRank() - 1; r >= 0; r--) {
			if(board[r][loc.getFile()] == null) {
				moves.add(new Move(this,  loc, new Location(r, loc.getFile()), false));
			} else {
				if(board[r][loc.getFile()].getColor() != sideToMove) {
					moves.add(new Move(this, loc, new Location(r, loc.getFile()), false));
					break;
				}
			}
		}
		
		for(int r = loc.getRank() + 1; r < MAX_RANKS; r++) {
			if(board[r][loc.getFile()] == null) {
				moves.add(new Move(this,  loc, new Location(r, loc.getFile()), false));
			} else {
				if(board[r][loc.getFile()].getColor() != sideToMove) {
					moves.add(new Move(this, loc, new Location(r, loc.getFile()), false));
					break;
				}
			}
		}
		
		for(int f = loc.getFile() - 1; f >= 0; f--) {
			if(board[loc.getRank()][f] == null) {
				moves.add(new Move(this,  loc, new Location(loc.getRank(), f), false));
			} else {
				if(board[loc.getRank()][f].getColor() != sideToMove) {
					moves.add(new Move(this, loc, new Location(loc.getRank(), f), false));
					break;
				}
			}
		}
		
		for(int f = loc.getFile() + 1; f < MAX_FILES; f++) {
			if(board[loc.getRank()][f] == null) {
				moves.add(new Move(this,  loc, new Location(loc.getRank(), f), false));
			} else {
				if(board[loc.getRank()][f].getColor() != sideToMove) {
					moves.add(new Move(this, loc, new Location(loc.getRank(), f), false));
					break;
				}
			}
		}
	}
	
	private void getKnightMove(Location loc, ArrayList<Move> moves) {
		moves.add(new Move(this, loc, new Location(loc.getRank() + 2, loc.getFile() + 1), false));
		moves.add(new Move(this, loc, new Location(loc.getRank() - 2, loc.getFile() + 1), false));
		moves.add(new Move(this, loc, new Location(loc.getRank() + 2, loc.getFile() - 1), false));
		moves.add(new Move(this, loc, new Location(loc.getRank() - 2, loc.getFile() - 1), false));
		
		moves.add(new Move(this, loc, new Location(loc.getRank() + 1, loc.getFile() + 2), false));
		moves.add(new Move(this, loc, new Location(loc.getRank() - 1, loc.getFile() + 2), false));
		moves.add(new Move(this, loc, new Location(loc.getRank() + 1, loc.getFile() - 2), false));
		moves.add(new Move(this, loc, new Location(loc.getRank() - 1, loc.getFile() - 2), false));
	}
	
	private void getPawnMove(Location loc, ArrayList<Move> moves) {
		if(sideToMove == ColorType.WHITE) {
			if(getPiece(loc.add(0, -1)) == null) {
				moves.add(new Move(this, loc, loc.add(0, -1), false));
				if(loc.getFile() == 6 && getPiece(loc.add(0, -2)) == null) {
					moves.add(new Move(this, loc, loc.add(0, -2), true)); // en pesant
				}
			}
			
			//captures sideways
			if(checkPiece(sideToMove, loc.add(-1, -1)))
				moves.add(new Move(this, loc, loc.add(-1, -1), false));
			if(checkPiece(sideToMove, loc.add(1, -1)))
				moves.add(new Move(this, loc, loc.add(1, -1), false));
		} else {
			if(getPiece(loc.add(0, 1)) == null) {
				moves.add(new Move(this, loc, loc.add(0, 1), false));
				if(loc.getFile() == 6 && getPiece(loc.add(0, 2)) == null) {
					moves.add(new Move(this, loc, loc.add(0, 2), true)); // en pesant
				}
			}
			
			if(checkPiece(sideToMove, loc.add(-1, 1)))
				moves.add(new Move(this, loc, loc.add(-1, 1), false));
			if(checkPiece(sideToMove, loc.add(1, 1)))
				moves.add(new Move(this, loc, loc.add(1, 1), false));
		}
	}
	
	private boolean checkPiece(ColorType type, Location loc) {
		Piece piece = getPiece(loc);
		return piece != null && piece.getColor() == type;
	}
	
	/**
	 * Makes a move on the chess board
	 * @param move the move to make
	 * @return whether the move was successfully made
	 */
	public boolean makeMove(Move move) {
		if(!move.isLegal()) return false;
		
		move.executeMove(this);
		
		if(move.getCapture() != null) halfMoves = 0;
		else halfMoves++;
		
		if(sideToMove == ColorType.WHITE) {
			sideToMove = ColorType.BLACK;
		} else {
			fullMoves++;
			sideToMove = ColorType.WHITE;
		}
		
		movesMade.add(move);
		
		return true;
	}
	
	public String toString() {
		return toFen(this);
	}
	
	/**
	 * Converts a chess board into FEN notation
	 * @param board the board to convert
	 * @return the FEN notation
	 */
	public static String toFen(Board board) {
		StringBuilder b = new StringBuilder();
		
		Piece p;
		int e;
		for(int r = 0; r < Board.MAX_RANKS; r++) {
			e = 0;
			for(int f = 0; f < Board.MAX_FILES; f++) {
				p = board.getPiece(new Location(r, f));
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
