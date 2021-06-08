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
		background();
		rankAndFile();
		setPieces();
		showMouseLocation();
	}
	
	public boolean askUserForColor() {
		if(mousePressed) {
			if(mouseX >= 100 && mouseX <= 300 && mouseY >= 400 && mouseY <= 500) {
				//returns false for black picked
				return false;
			}
			if(mouseX >= 400 && mouseX <= 700 && mouseY >= 400 && mouseY <= 500) {
				//return true for white
				return true;
			}
		}
		return true;
	}
	
	public void switchToColor() {
		if(askUserForColor()) {
			
		}
		else {
			
		}
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
			text(temp+1, 777, i);
			temp++;
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
	
	public void showMouseLocation() {
		String locAsString = "";
		int f = 0, r = 0;
		
		Location loc = new Location(f, r);
		for(int i = 0; i < 800; i += 100) {
			r = 0;
			for(int j = 0; j < 800; j += 100) { 
				if(mouseX >= i && mouseX <= i + 100) {
					if(mouseY >= j && mouseY <= j + 100) {
						loc = new Location(r, f);
						locAsString = loc.toString();
					}
				}
				r++;
			}
			f++;
		}
		
		
		//light box rgb: 240, 217, 181
		//dark box rgb: 181, 136, 99
		
		fill(19, 44, 209); 
		textSize(30);
		text(locAsString, mouseX, mouseY);
	}
	
}
