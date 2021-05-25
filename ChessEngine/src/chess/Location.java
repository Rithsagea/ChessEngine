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
		if(rank < 0 || rank > 7)
			throw new RuntimeException("Invalid Rank Number");
		if(file < 0 || file > 7)
			throw new RuntimeException("Invalid File Number");
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
	
	public String toString() {
		return String.format("%c%d", 'a' + file, rank + 1);
	}
	
	/**
	 * Initializes a new location from notation
	 * @param str the location notation
	 * @return
	 */
	public static Location fromString(String str) {
		try {
			return new Location(str.charAt(1) - '0', str.charAt(0) - 'a');
		} catch(Exception e) {
			return null;
		}
	}
}
