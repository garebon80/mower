package seat.code.mower.api;

import java.io.BufferedReader;

import seat.code.mower.app.MowerConfiguration;
import seat.code.mower.exception.MowerParseException;

/**
 * 
 * @author grebon
 *
 * A parser is used to transform a configuration stream, read by
 * {@link MowerReaderService}, into a configuration object. All the
 * parsers must implement this interface.
 * 
 */
public interface MowerParser {

	/**
	 * Parse a stream.
	 * 
	 * @param reader The reader to the configuration stream.
	 * @return The configuration object.
	 * @throws MowerParseException If there is a parse error.
	 */
	MowerConfiguration parse(BufferedReader reader) throws MowerParseException;

}
