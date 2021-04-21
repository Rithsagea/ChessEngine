package chess.ui;

import chess.Board;
import chess.Piece;
import processing.core.PApplet;

public class ChessApp extends PApplet {
	
	public final int WIDTH = 800;
	public final int HEIGHT = 800;
	
	public final int PIECE_WIDTH = 90;
	public final int PIECE_HEIGHT = 90;
	
	//ui
	private ChessResources rsc;
	
	//data
	private Board board;
	
	@Override
	public void settings() {
		size(800, 800);
	}
	
	@Override
	public void setup() {
		rsc = new ChessResources(this);
		
		board = new Board();
	}
	
	@Override
	public void draw() {
		image(rsc.getBackground(), 0, 0);
		
		Piece piece;
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				piece = board.getPiece(y, x);
				if(piece != null)
					image(rsc.getIcon(piece), WIDTH / 8 * x, HEIGHT / 8 * y);
			}
		}
	}
}