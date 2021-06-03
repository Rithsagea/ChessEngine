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
	 * @param rank the row of the location (vertical)
	 * @param file the column of the location (horizontal)
	 */
	public Location(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}
	
	/**
	 * Gets the rank of the location
	 * @return the rank of the location (vertical)
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * Gets the file of the location
	 * @return the file of the location (horizontal)
	 */
	public int getFile() {
		return file;
	}
	
	/**
	 * Constructs a new location with the following transformations
	 * @param rank increase rank by this amount
	 * @param file increase file by this amount
	 * @return the new location
	 */
	public Location add(int rank, int file) {
		return new Location(this.rank + rank, this.file + file);
	}
	
	public boolean isValid() {
		return !(rank < 0 || rank > 7 || file < 0 || file > 7);
	}
	
	public String toString() {
		return String.format("%c%d", 'a' + file, 8 - rank);
	}
	
	/**
	 * Initializes a new location from notation
	 * @param str the location notation
	 * @return the location
	 */
	public static Location fromString(String str) {
		try {
			return new Location(str.charAt(1) + '0', str.charAt(0) - 'a');
		} catch(Exception e) {
			return null;
		}
	}
}
