package seat.code.mower.api;

import seat.code.mower.app.MowerConfiguration;
import seat.code.mower.exception.MowerParseException;

/**
 * 
 * @author grebon
 * 
 * This interface must be implemented by Input Reader (File reader,
 * Network reader, ...). This service reads the input configuration of
 * the Mower Application.
 * 
 */
public interface MowerReaderService {

	/**
	 * Read the configuration.
	 * 
	 * @return The configuration object.
	 * @throws MowerParseException If there is a parse error, an exception is thrown.
	 */
	MowerConfiguration read() throws MowerParseException;

}
