package chess.ui;

import chess.Board;
import chess.Location;
import chess.Piece;
import processing.core.PApplet;

public class ChessApp extends PApplet {
	//TODO flip board so that white is on bottom
	//TODO when a box is clicked give the file and rank
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
		Location location;
		for(int x = 7; x >= 0; x--) {
			for(int y = 7; y >= 0; y--) {
				location = new Location(y, x);
				piece = board.getPiece(location);
				if(piece != null)
					image(rsc.getIcon(piece), WIDTH / 8 * x, HEIGHT / 8 * y);
			}
		}
		
	}
}
