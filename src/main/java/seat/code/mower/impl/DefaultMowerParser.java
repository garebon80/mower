package seat.code.mower.impl;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seat.code.mower.api.Factory;
import seat.code.mower.api.MowerParser;
import seat.code.mower.app.MowerConfiguration;
import seat.code.mower.domain.Command;
import seat.code.mower.domain.Field;
import seat.code.mower.domain.Mower;
import seat.code.mower.domain.Orientation;
import seat.code.mower.exception.MowerParseException;

/**
  * 
 * @author grebon
 *  
 * This is the default parser implementation. It is used to parse
 * stream defined in the specification. 
 * 
 */
public class DefaultMowerParser implements MowerParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMowerParser.class);

	private static final int FIELD_COORDINATES_COUNT = 2;
	private static final Pattern FIELD_COORDINATES = Pattern.compile("^(\\d+) (\\d+)$");
	private static final int MOWER_COORDINATES_COUNT = 3;
	private static final Pattern MOWER_COORDINATES = Pattern.compile("^(\\d) (\\d) ([ESWN])$");
	private static final int MOWER_COMMANDS_COUNT = 1;
	private static final Pattern MOWER_COMMANDS = Pattern.compile("^([LRM]+)$");
	
	/**
	 * 
	 * @param reader
	 * @param lineNumber
	 * @return MowerConfiguration
	 * @throws MowerParseException
	 */
	@Override
	public MowerConfiguration parse(BufferedReader reader) throws MowerParseException {

		int lineNumber = 0;

		LOGGER.debug("Begin parsing.");

		if (reader == null) {
			throw new IllegalArgumentException("Reader argument must not be null.");
		}

		try {

			String fieldCoordinates = reader.readLine();
			lineNumber++;

			Field field = parseFieldCoordinates(fieldCoordinates, lineNumber);

			String mowerCommands = null;
			String mowerCoordinates = reader.readLine();
			lineNumber++;

			List<Mower> mowers = new ArrayList<Mower>();
			List<List<Command>> mowersCommands = new ArrayList<List<Command>>();

			while (mowerCoordinates != null) {

				Mower mower = parseMowerCoordinates(mowerCoordinates, lineNumber);
				mowers.add(mower);

				mowerCommands = reader.readLine();
				lineNumber++;

				List<Command> commands = parseMowerCommands(mowerCommands, lineNumber);
				mowersCommands.add(commands);

				mowerCoordinates = reader.readLine();
				lineNumber++;
			}

			return new MowerConfiguration(field, mowers, mowersCommands);

		} catch (Exception e) {
			LOGGER.error("Parsing has stopped : {}", e.getMessage());
			throw new MowerParseException("Cannot parse the reader content at line " + lineNumber + ".");
		} finally {
			LOGGER.debug("End parsing.");
		}

	}

	/**
	 * 
	 * @param lineToParse
	 * @param lineNumber
	 * @return Field
	 * @throws MowerParseException
	 */
	private Field parseFieldCoordinates(String lineToParse, int lineNumber) throws MowerParseException {
		Matcher matcher = FIELD_COORDINATES.matcher(lineToParse);
		matcher.matches();
		if (matcher.groupCount() != FIELD_COORDINATES_COUNT) {
			throw new MowerParseException("Field coordinates are not well formed at line " + lineNumber + ".");
		}

		int xTopPosition = 0;
		int yTopPosition = 0;

		try {
			xTopPosition = Integer.parseInt(matcher.group(1));
			yTopPosition = Integer.parseInt(matcher.group(2));
		} catch (IllegalStateException e) {
			LOGGER.error("{}", e.getMessage());
			throw new MowerParseException("Field coordinates are not well formed at line " + lineNumber + ".");
		}

		LOGGER.debug("Parsed field coordinates are {} - {}.", xTopPosition, yTopPosition);
		return Factory.newField(xTopPosition + 1, yTopPosition + 1);
	}
	
	/**
	 * 
	 * @param lineToParse
	 * @param lineNumber
	 * @return Mower
	 * @throws MowerParseException
	 */
	private Mower parseMowerCoordinates(String lineToParse, int lineNumber) throws MowerParseException {
		Matcher matcher = MOWER_COORDINATES.matcher(lineToParse);
		matcher.matches();
		if (matcher.groupCount() != MOWER_COORDINATES_COUNT) {
			throw new MowerParseException("Mower coordinates are not well formed at line " + lineNumber + ".");
		}

		int xMowerPosition = 0;
		int yMowerPosition = 0;

		try {
			xMowerPosition = Integer.parseInt(matcher.group(1));
			yMowerPosition = Integer.parseInt(matcher.group(2));
		} catch (IllegalStateException e) {
			LOGGER.error("{}", e.getMessage());
			throw new MowerParseException("Mower coordinates are not well formed at line " + lineNumber + ".");
		}

		String mowerOrientation = matcher.group(3);
		LOGGER.debug("Parsed mower coordinates are {} - {} - {}.", xMowerPosition, yMowerPosition, mowerOrientation);

		return Factory.newMower(Factory.newPosition(xMowerPosition, yMowerPosition), Orientation.of(mowerOrientation));
	}

	/**
	 * 
	 * @param lineToParse
	 * @param lineNumber
	 * @return List<Command>
	 * @throws MowerParseException
	 */
	private List<Command> parseMowerCommands(String lineToParse, int lineNumber) throws MowerParseException {
		Matcher matcher = MOWER_COMMANDS.matcher(lineToParse);
		matcher.matches();
		if (matcher.groupCount() != MOWER_COMMANDS_COUNT) {
			throw new MowerParseException("Mower commands are not well formed at line " + lineNumber + ".");
		}

		String[] commands = null;
		try {
			commands = matcher.group(1).split("(?!^)");
		} catch (IllegalStateException e) {
			LOGGER.error("{}", e.getMessage());
			throw new MowerParseException("Mower commands are not well formed at line " + lineNumber + ".");
		}

		LOGGER.debug("Parsed commands are {}.", matcher.group(1));

		ArrayList<Command> mowerCommands = new ArrayList<Command>();
		for (String command : commands) {
			mowerCommands.add(Command.of(command));
		}

		return mowerCommands;
	}
}
