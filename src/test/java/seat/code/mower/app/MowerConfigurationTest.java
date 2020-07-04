package seat.code.mower.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.annotations.Test;

import seat.code.mower.api.Factory;
import seat.code.mower.api.Grid;

import seat.code.mower.app.MowerConfiguration.MowerCommands;
import seat.code.mower.domain.Command;
import seat.code.mower.domain.Orientation;
import seat.code.mower.domain.assertj.MowerAssert;
import seat.code.mower.domain.assertj.PositionAssert;
import seat.code.mower.exception.MowerParseException;
import seat.code.mower.impl.DefaultMowerParser;

/**
 * 
 * @author grebon
 *
 */
@Test
public class MowerConfigurationTest {

	private static final String REGULAR_CONFIGURATION = "5 5\n1 2 N\nLMLMLMLMM";

	public void testFieldPropertiesForRegularConfiguration() throws MowerParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
				REGULAR_CONFIGURATION.getBytes())));
		MowerConfiguration configuration = new DefaultMowerParser().parse(reader);

		Grid configuredField = configuration.getConfiguredField();
		PositionAssert.assertThat(configuredField.getTopRightPosition()).hasX(5);
		PositionAssert.assertThat(configuredField.getTopRightPosition()).hasY(5);
	}

	public void testMowersPropertiesForRegularConfiguration() throws MowerParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
				REGULAR_CONFIGURATION.getBytes())));
		MowerConfiguration configuration = new DefaultMowerParser().parse(reader);

		List<MowerCommands> configuredMowerCommands = configuration.getConfiguredMowerCommands();
		MowerCommands mowerCommands = configuredMowerCommands.get(0);
		assertThat(configuredMowerCommands).hasSize(1);
		MowerAssert.assertThat(mowerCommands.getMower()).hasPosition(Factory.newPosition(1, 2));
		MowerAssert.assertThat(mowerCommands.getMower()).hasOrientation(Orientation.of("N"));
	}

	public void testCommandsPropertiesForRegularConfiguration() throws MowerParseException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(
				REGULAR_CONFIGURATION.getBytes())));
		MowerConfiguration configuration = new DefaultMowerParser().parse(reader);

		List<MowerCommands> configuredMowerCommands = configuration.getConfiguredMowerCommands();
		MowerCommands mowerCommands = configuredMowerCommands.get(0);
		assertThat(mowerCommands.getCommands()).hasSize(9);
		assertThat(mowerCommands.getCommands()).containsExactly(Command.of("L"), Command.of("M"), Command.of("L"),
				Command.of("M"), Command.of("L"), Command.of("M"), Command.of("L"), Command.of("M"), Command.of("M"));
		
	}
}
