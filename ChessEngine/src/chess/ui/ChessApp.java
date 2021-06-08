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
	private boolean flipped = false;
	
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
		background();
		rankAndFile();
		setPieces();
		showMouseLocation();
	}
	
	public void rankAndFile() {

		//letters		
		char asciiVal = 97;
		for(int i = 10; i < 720; i += 100) {
			if(asciiVal % 2 != 0) 
				fill(240, 217, 181);
			else
				fill(181, 136, 99);
			String str = "" + asciiVal;
			text(str, i, 790);
			asciiVal++;
		}
		//numbers
		int temp = 0;
		for(int i = 730; i >= 30; i -= 100) {
			if(temp % 2 == 0) 
				fill(181, 136, 99);
			else
				fill(240, 217, 181);
			text(++temp, 777, i);
		}
	}
	
	public void background() {
		image(rsc.getBackground(), 0, 0);
	}
	
	public void setPieces() {
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
	}
	
	public Location getMouseLocation() {
		return new Location(mouseY / 100, mouseX / 100);
	}
	
	public void showMouseLocation() {
		//light box rgb: 240, 217, 181
		//dark box rgb: 181, 136, 99
		fill(19, 44, 209); 
		textSize(30);
		text(getMouseLocation().toString(), mouseX, mouseY);
	}
	
}
