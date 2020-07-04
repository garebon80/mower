package seat.code.mower.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seat.code.mower.api.MowerReaderService;
import seat.code.mower.app.MowerConfiguration;
import seat.code.mower.api.MowerParser;
import seat.code.mower.exception.MowerParseException;

/**
 * 
 * @author grebon
 * 
 * This service implementation is used to read and process a configuration file.
 * 
 */
public final class MowerFileReaderService implements MowerReaderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MowerFileReaderService.class);

	private final BufferedReader reader;
	private final MowerParser parser;

	private final String filename;

	/**
	 * The constructor.
	 * 
	 * @param fileName The name of the configuration file.
	 * @param parser The parser you want to use.
	 */
	public MowerFileReaderService(String fileName, MowerParser parser) {

		if (fileName == null) {
			throw new IllegalArgumentException("Filename argument must not be null.");
		}

		if (parser == null) {
			throw new IllegalArgumentException("Parser argument must not be null.");
		}

		try {
			this.reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			LOGGER.error("{}", e.getMessage());
			throw new IllegalArgumentException("File " + fileName + " cannot be opened.");
		}

		this.filename = fileName;
		this.parser = parser;
	}

	@Override
	public MowerConfiguration read() throws MowerParseException {

		try {
			return parser.parse(reader);
		} catch (MowerParseException e) {
			LOGGER.error(e.getMessage());
			throw new MowerParseException("File " + filename + " is malformed.");
		} finally {
			closeSource();
		}

	}

	private void closeSource() {
		try {
			reader.close();
		} catch (IOException e) {
			throw new IllegalStateException("File cannot be close.");
		}
	}

}
