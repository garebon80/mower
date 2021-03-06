package seat.code.mower.api;

import seat.code.mower.domain.Field;
import seat.code.mower.domain.Mower;
import seat.code.mower.domain.Orientation;
import seat.code.mower.domain.Position;
import seat.code.mower.impl.MowerFileReaderService;
import seat.code.mower.impl.DefaultMowerParser;

/**
 *  
 * @author grebon
 * Factory for all the domain objects.
 * 
 */
public final class Factory {

	private Factory() {
		
	}
		

	/**
	 * Create a position.
	 * 
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 * @return The newly created position.
	 */
	public static Position newPosition(int x, int y) {
		return new Position(x, y);
	}

	/**
	 * Create a mower.
	 * 
	 * @param position The initial position.
	 * @return The newly created mower.
	 */
	public static Mower newMower(Position position) {
		return newMower(position, Orientation.NORTH);
	}

	/**
	 * Create a mower.
	 * 
	 * @param position The initial position.
	 * @param orientation The initial orientation.
	 * @return The newly created mower.
	 */
	public static Mower newMower(Position position, Orientation orientation) {
		return new Mower(position, orientation);
	}

	/**
	 * Create a parser.
	 * 
	 * @return The newly created parser.
	 */
	public static MowerParser newMowerParser() {
		return new DefaultMowerParser();
	}

	/**
	 * Create a file reader service.
	 * 
	 * @param fileName The filename of the configuration file.
	 * @param parser The parser you want to use.
	 * @return The newly created service.
	 */
	public static MowerReaderService newFileReaderService(String fileName, MowerParser parser) {
		return new MowerFileReaderService(fileName, parser);
	}

	/**
	 * Create a field.
	 * 
	 * @param horizontalSize The horizontal size.
	 * @param verticalSize The vertical size.
	 * @return The newly created field.
	 */
	public static Field newField(int horizontalSize, int verticalSize) {
		return new Field(horizontalSize, verticalSize);
	}

}
