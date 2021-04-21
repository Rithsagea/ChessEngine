package chess.move;

import chess.Board;

public interface Move {
	public String getNotation();
	public boolean isLegal(Board board);
	public void executeMove(Board board);
}
