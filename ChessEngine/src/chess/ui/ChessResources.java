package chess.ui;

import chess.Piece;
import processing.core.PApplet;
import processing.core.PImage;
import chess.Location;

public class ChessResources extends PApplet{
	
	private PImage spriteSheet;
	private PImage background;
	
	private PImage[][] pieces = new PImage[2][6];
	private Location[][] boardValues = new Location[8][8];
	
	public ChessResources(ChessApp app) {
		spriteSheet = app.loadImage("resources/chess.png");
		background = app.loadImage("resources/board.png");
		background.resize(app.WIDTH, app.HEIGHT);
		
		int w = spriteSheet.width / 6;
		int h = spriteSheet.height / 2;
	
		for(int x = 1; x >= 0; x--) {
			for(int y = 5; y >= 0; y--) {
				pieces[x][y] = app.createImage(w, h, PApplet.ARGB);
				pieces[x][y].copy(spriteSheet, w * y, w * x, w, h, 10, 10, w, h);
				pieces[x][y].resize(app.PIECE_WIDTH, app.PIECE_HEIGHT);
			}
		}		
		
//		for(int r = 0; r < 8; r++) {
//			for(int c = 0; c < 8; c++) {
//				boardValues[r][c] = new Location()
//			}
//		}
		System.out.println(boardValues.toString());
	}
	
	public PImage getIcon(Piece piece) {
		return pieces[piece.getColor().ordinal()][piece.getType().ordinal()];
	}
	
	public PImage getBackground() {
		return background;
	}
	
	public Location getLocation() {
		
	}
}
