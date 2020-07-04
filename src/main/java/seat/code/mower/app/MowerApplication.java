package seat.code.mower.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import seat.code.mower.api.Factory;
import seat.code.mower.api.MowerReaderService;
import seat.code.mower.domain.Mower;
import seat.code.mower.exception.MowerParseException;

/**
 * 
 * @author grebon
 *
 */
@SpringBootApplication
public class MowerApplication {
	
	private static final int ERROR = -1;
	private static final int PARSE_ERROR = -2;

	private static final Logger LOGGER = LoggerFactory.getLogger(MowerApplication.class);
	private static final String BAD_ARGUMENT_MESSAGE = "You must enter an input file.";

	public static void main(String[] args) {		
		
		try {
			checkArgument(args);
			MowerReaderService fileInputService = Factory.newFileReaderService(args[0], Factory.newMowerParser());
			MowerConfiguration configuration = fileInputService.read();

			List<Mower> result = MowerRunner.INSTANCE.runMower(configuration);

			LOGGER.info("Here is the result: ");
			for (Mower mower : result) {
				LOGGER.info("{} {} {}", mower.getPosition().getX(), mower.getPosition().getY(), mower.getOrientation()
						.getCode());
			}

		} catch (MowerParseException mpe) {
			LOGGER.error("Please correct your configuration file: " + mpe.getMessage());
			System.exit(PARSE_ERROR);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			System.exit(ERROR);
		}
		
		
		SpringApplication.run(MowerApplication.class, args);
	}
	
	/**
	 * Checks if the arguments are valid
	 * @param args
	 */
	private static void checkArgument(String... args) {
		if (args.length != 1) {
			throw new IllegalArgumentException(BAD_ARGUMENT_MESSAGE);
		}
	}

}
