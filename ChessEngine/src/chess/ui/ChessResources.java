package chess.ui;

import chess.Piece;
import processing.core.PApplet;
import processing.core.PImage;

public class ChessResources {
	
	private PImage spriteSheet;
	private PImage background;
	
	private PImage[][] pieces = new PImage[2][6];
	
	public ChessResources(ChessApp app) {
		spriteSheet = app.loadImage("resources/chess.png");
		background = app.loadImage("resources/board.png");
		background.resize(app.WIDTH, app.HEIGHT);
		
		int w = spriteSheet.width / 6;
		int h = spriteSheet.height / 2;
	
		for(int x = 0; x < 2; x++) {
			for(int y = 0; y < 6; y++) {
				pieces[x][y] = app.createImage(w, h, PApplet.ARGB);
				pieces[x][y].copy(spriteSheet, w * y, w * x, w, h, 10, 10, w, h);
				pieces[x][y].resize(app.PIECE_WIDTH, app.PIECE_HEIGHT);
			}
		}		
	}
	
	public PImage getIcon(Piece piece) {
		return pieces[piece.getColor().ordinal()][piece.getType().ordinal()];
	}
	
	public PImage getBackground() {
		return background;
	}
}
