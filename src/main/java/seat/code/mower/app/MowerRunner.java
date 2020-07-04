package seat.code.mower.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import seat.code.mower.api.PositionController;
import seat.code.mower.app.MowerConfiguration.MowerCommands;
import seat.code.mower.domain.Command;
import seat.code.mower.domain.Mower;

/**
 * 
 * 
 * @author grebon
 *
 * This runner is used to execute the Mower simulation. It executes
 * the configuration passed.
 * 
 */
public enum MowerRunner {

	INSTANCE;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MowerRunner.class);

	/**
	 * Run the simulation with the configuration passed as parameter.
	 * 
	 * @param configuration The configuration you want to process.
	 * @return The list of mowers at their final position.
	 */
	public List<Mower> runMower(MowerConfiguration configuration) {

		List<Mower> result = new ArrayList<Mower>();
		PositionController field = configuration.getConfiguredField();
		List<MowerCommands> configuredMowerCommands = configuration.getConfiguredMowerCommands();

		for (MowerCommands mowerCommands : configuredMowerCommands) {

			Mower mower = mowerCommands.getMower();

			//The mower is processed only if its initial position is valid, and if there is no mower yet.
			if (field.contains(mower.getPosition()) && field.isPositionEmpty(mower.getPosition())) {

				List<Command> commands = mowerCommands.getCommands();
				mower.linkTo(field);

				LOGGER.info("Putting a mower at position {} and orientated to {}", mower.getPosition(), mower.getOrientation());
				
				for (Command command : commands) {
					LOGGER.info("Executing command {}", command);
					Command.execute(mower, command);
				}

				LOGGER.info("-------------------------");
				
				result.add(mower);
			}

		}

		return result;
	}

}
