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
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				location = new Location(y, x);
				piece = board.getPiece(location);
				if(piece != null)
					image(rsc.getIcon(piece), WIDTH / 8 * x, HEIGHT / 8 * y);
			}
		}
		
		String locAsString = "";
		
		int r = 0, f = 0;
		
		Location loc = new Location(r, f);
		for(int i = 0; i < 800; i += 100) {
			f = 0;
			for(int j = 0; j < 800; j += 100) { 
				if(mouseX >= i && mouseX <= i + 100) {
					if(mouseY >= j && mouseY <= j + 100) {
						loc = new Location(r, f);
						locAsString = loc.toString();
					}
				}
				f++;
			}
			r++;
		}
		
		fill(255, 0, 0); //might wanna change color (looks bad)
		textSize(30);
		text(locAsString, mouseX, mouseY);
	}
}
