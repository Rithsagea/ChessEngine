package chess;

public class Location {
	private int rank;
	private int file;
	
	public Location(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}
	
	public int getRank() {
		return rank;
	}
	
	public int getFile() {
		return file;
	}
}
