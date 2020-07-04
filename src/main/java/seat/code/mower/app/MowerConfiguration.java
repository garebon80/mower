package seat.code.mower.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seat.code.mower.domain.Command;
import seat.code.mower.domain.Field;
import seat.code.mower.domain.Mower;

/**
 * 
 * 
 * @author grebon
 * This immutable class represents the configuration file once parsed.
 */
public class MowerConfiguration {

	private final Field field;
	
	private final List<MowerCommands> mowerCommands;

	/**
	 * The constructor.
	 * 
	 * @param field The field.
	 * @param mowers The mowers.
	 * @param commands The commands by mowers.
	 */
	public MowerConfiguration(Field field, List<Mower> mowers, List<List<Command>> commands) {
		this.field = new Field(field.getHorizontalSize(), field.getVerticalSize());

		ArrayList<MowerCommands> mowerCommandsGroup = new ArrayList<MowerCommands>();

		for (int i = 0; i < mowers.size(); i++) {
			MowerCommands mc = new MowerCommands(mowers.get(i), commands.get(i));
			mowerCommandsGroup.add(mc);
		}

		this.mowerCommands = mowerCommandsGroup;

	}

	/**
	 * Get the field as configured.
	 * 
	 * @return The filed configured.
	 */
	public Field getConfiguredField() {
		return new Field(field.getHorizontalSize(), field.getVerticalSize());
	}

	/**
	 * Get the associated mowers and commands as configured.
	 * 
	 * @return The associated mowers and their commands.
	 */
	public List<MowerCommands> getConfiguredMowerCommands() {
		return Collections.unmodifiableList(mowerCommands);
	}
	
	/**
	 * 
	 * @author grebon
	 * This class represents an associated couple of a Mower and its commands.
	 */
	public static final class MowerCommands {

		private final Mower mower;
		private final List<Command> commands;

		public MowerCommands(Mower mower, List<Command> commands) {
			this.mower = new Mower(mower.getPosition(), mower.getOrientation());
			this.commands = Collections.unmodifiableList(commands);
		}

		public Mower getMower() {
			return new Mower(mower.getPosition(), mower.getOrientation());
		}

		public List<Command> getCommands() {
			return Collections.unmodifiableList(commands);
		}

		@Override
		public String toString() {
			return new StringBuilder("MowerCommand:").append("\n").append(mower).append("\n").append(commands)
					.toString();
		}

	}

}
