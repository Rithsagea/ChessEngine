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
		
		//rank and file
		int temp = 0;
		for(int i = 10; i < 720; i += 100) {
			if(temp % 2 == 0)
				fill(240, 217, 181);
			else
				fill(181, 136, 99);
			text(temp+1, i, 790);
			temp++;
		}
		char asciiVal = 97;
		for(int i = 790; i >= 90; i -= 100) {
			if(asciiVal % 2 != 0)
				fill(181, 136, 99);
			else
				fill(240, 217, 181);
			String str = new Character((char) asciiVal).toString();
			text(str, 777, i);
			asciiVal++;
		}
		
		//setting of pieces
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
		
		//get rank and file from mouse location
		String locAsString = "";
		int f = 7, r = 0;
		
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
			f--;
		}
		
		
		//light box rgb: 240, 217, 181
		//dark box rgb: 181, 136, 99
		
		
		fill(255, 100, 100); //might wanna change color (looks bad)
		textSize(30);
		text(locAsString, mouseX, mouseY);
	}
}
