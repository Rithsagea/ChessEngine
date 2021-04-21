package chess;

/**
 * Represents a location on the chess board
 * @author rithsagea
 *
 */
public class Location {
	private int rank;
	private int file;
	
	/**
	 * Creates a new Location at (rank, file)
	 * @param rank the row of the location
	 * @param file the column of the location
	 */
	public Location(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}
	
	/**
	 * Gets the rank of the location
	 * @return the rank of the location
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * Gets the file of the location
	 * @return the file of the location
	 */
	public int getFile() {
		return file;
	}
	
	public String toString() {
		return String.format("%c%d", 'a' + file, rank + 1);
	}
}
