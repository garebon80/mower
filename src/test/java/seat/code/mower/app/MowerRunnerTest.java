package seat.code.mower.app;

import static seat.code.mower.api.Factory.newField;
import static seat.code.mower.api.Factory.newMower;
import static seat.code.mower.api.Factory.newPosition;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import seat.code.mower.app.MowerConfiguration.MowerCommands;
import seat.code.mower.domain.Command;
import seat.code.mower.domain.Mower;
import seat.code.mower.domain.Orientation;
import seat.code.mower.domain.Position;
import seat.code.mower.exception.MowerParseException;


/**
 * 
 * @author grebon
 *
 */
@Test
// In these tests, we only want to test the Runner, so configurations are mocked.
public class MowerRunnerTest {

	public void testSimpleSimulation() throws MowerParseException {

		MowerConfiguration mockConfiguration = mock(MowerConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(6, 6));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(new MowerCommands(newMower(newPosition(1, 2)), Arrays.asList(Command.LEFT,
						Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT,
						Command.FORWARD, Command.FORWARD))));

		List<Mower> result = MowerRunner.INSTANCE.runMower(mockConfiguration);

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getPosition()).isEqualTo(new Position(1, 3));
		assertThat(result.get(0).getOrientation()).isEqualTo(Orientation.NORTH);
	}

	public void testSimulationWhenMowerInitialPositionIsOutsideTheField() throws MowerParseException {
		MowerConfiguration mockConfiguration = mock(MowerConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(6, 6));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(new MowerCommands(newMower(newPosition(6, 6)), Arrays.asList(Command.LEFT,
						Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT,
						Command.FORWARD, Command.FORWARD))));

		List<Mower> result = MowerRunner.INSTANCE.runMower(mockConfiguration);

		assertThat(result).hasSize(0);
	}

	public void testSimulationWhenMowerIsInFrontOfABorder() throws MowerParseException {
		MowerConfiguration mockConfiguration = mock(MowerConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(6, 6));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(new MowerCommands(newMower(newPosition(5, 0), Orientation.EAST), Arrays.asList(
						Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD,
						Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD))));

		List<Mower> result = MowerRunner.INSTANCE.runMower(mockConfiguration);

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getPosition()).isEqualTo(new Position(5, 0));
		assertThat(result.get(0).getOrientation()).isEqualTo(Orientation.EAST);
	}

	public void testSimulationWhenMowerIsSurrounded() throws MowerParseException {
		// The last mower is the only moving, but can't because of surrounding.
		// Check that its position does not change, but that its orientation
		// just move to left.
		
		Position startPosition = newPosition(1, 1);
		
		MowerConfiguration mockConfiguration = mock(MowerConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(3, 3));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(
						new MowerCommands(newMower(newPosition(0, 0)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(0, 1)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(0, 2)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(1, 2)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(2, 2)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(2, 1)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(2, 0)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(newPosition(1, 0)), Arrays.asList(Command.LEFT)),
						new MowerCommands(newMower(startPosition), Arrays.asList(Command.FORWARD, Command.LEFT,
								Command.FORWARD, Command.LEFT, Command.FORWARD, Command.LEFT, Command.FORWARD))));

		List<Mower> result = MowerRunner.INSTANCE.runMower(mockConfiguration);

		assertThat(result).hasSize(9);
		assertThat(result.get(8).getPosition()).isEqualTo(startPosition);
		assertThat(result.get(8).getOrientation()).isEqualTo(Orientation.EAST);
	}

	public void testSecondMowerStartsAtTheFinishPositionOfFirstMower() throws MowerParseException {
		// Mower 1 starts at (0,2) and finishes at (1,0).
		// Mower 2 starts at (1,0)
		// So mower 2 can't be integrated in the simulation !
		MowerConfiguration mockConfiguration = mock(MowerConfiguration.class);
		when(mockConfiguration.getConfiguredField()).thenReturn(newField(3, 3));
		when(mockConfiguration.getConfiguredMowerCommands()).thenReturn(
				Arrays.asList(
						new MowerCommands(newMower(newPosition(0, 2)), Arrays.asList(Command.RIGHT, Command.FORWARD,
								Command.RIGHT, Command.FORWARD, Command.FORWARD)),
						new MowerCommands(newMower(newPosition(1, 0), Orientation.EAST), Arrays.asList(Command.FORWARD,
								Command.FORWARD, Command.FORWARD, Command.FORWARD, Command.FORWARD))));

		List<Mower> result = MowerRunner.INSTANCE.runMower(mockConfiguration);

		assertThat(result).hasSize(1);
		assertThat(result.get(0).getPosition()).isEqualTo(newPosition(1, 0));
		assertThat(result.get(0).getOrientation()).isEqualTo(Orientation.SOUTH);
	}

}
