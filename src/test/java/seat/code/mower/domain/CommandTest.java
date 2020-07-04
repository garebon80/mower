package seat.code.mower.domain;

import static org.assertj.core.api.Assertions.assertThat;
import seat.code.mower.domain.assertj.OrientationAssert;
import seat.code.mower.domain.assertj.PositionAssert;

import org.testng.annotations.Test;

/**
 * 
 * @author grebon
 *
 */
@Test
public class CommandTest {

	public void testValidCommands() {
		assertThat(Command.of("M")).isEqualTo(Command.FORWARD);
		assertThat(Command.of("L")).isEqualTo(Command.LEFT);
		assertThat(Command.of("R")).isEqualTo(Command.RIGHT);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Z is not a valid Command value.")
	public void testExceptionWhenInvalidCommands() {
		Command.of("Z");
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Command value must not be null.")
	public void testExceptionWhenNullCommands() {
		Command.of(null);
	}

	public void testValueCommands() {
		assertThat(Command.of("M").getCode()).isEqualTo("M");
		assertThat(Command.of("L").getCode()).isEqualTo("L");
		assertThat(Command.of("R").getCode()).isEqualTo("R");
	}

	@Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "There is no field attached to the mower.")
	public void testCannotMoveMowerWhenNotAttachedToField() {
		Position startPosition = new Position(2, 2);
		Mower mower = new Mower(startPosition, Orientation.NORTH);

		Command.execute(mower, Command.FORWARD);
	}

	public void testExecuteOneStepForwardCommandsWhenMowerIsOrientedToTheNorth() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(2, 3);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.NORTH);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	public void testExecuteOneStepForwardCommandsWhenMowerIsOrientedToTheSouth() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(2, 1);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.SOUTH);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	public void testExecuteOneStepForwardCommandsWhenMowerIsOrientedToTheEast() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(3, 2);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.EAST);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	public void testExecuteOneStepForwardCommandsWhenMowerIsOrientedToTheWest() {
		Position startPosition = new Position(2, 2);
		Position expectedPosition = new Position(1, 2);

		Field field = new Field(10, 10);
		Mower mower = new Mower(startPosition, Orientation.WEST);
		mower.linkTo(field);

		Command.execute(mower, Command.FORWARD);

		assertThat(mower.getPosition()).isEqualTo(expectedPosition);
	}

	public void testExecuteRotation() {
		Position startPosition = new Position(2, 2);
		Mower mower = new Mower(startPosition, Orientation.NORTH);
		Command.execute(mower, Command.LEFT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("W");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.WEST);
		Command.execute(mower, Command.LEFT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("S");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.SOUTH);
		Command.execute(mower, Command.LEFT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("E");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.EAST);
		Command.execute(mower, Command.LEFT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("N");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
		Command.execute(mower, Command.RIGHT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("E");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.EAST);
		Command.execute(mower, Command.RIGHT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("S");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.SOUTH);
		Command.execute(mower, Command.RIGHT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("W");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.WEST);
		Command.execute(mower, Command.RIGHT);
		OrientationAssert.assertThat(mower.getOrientation()).hasCode("N");
		assertThat(mower.getOrientation()).isEqualTo(Orientation.NORTH);
	}

	public void testOneStepForwardOutsideTheField() {
		Position startPosition = new Position(0, 0);
		Field field = new Field(3, 3);
		Mower mower = new Mower(startPosition, Orientation.WEST);
		mower.linkTo(field);
		Command.execute(mower, Command.FORWARD);
		assertThat(mower.getPosition()).isEqualTo(startPosition);
	}

	public void testOneStepForwardToANonEmptyArea() {
		Field field = new Field(3, 3);
		Mower movableMower = new Mower(new Position(0, 0), Orientation.NORTH);
		Mower fixedMower = new Mower(new Position(0, 1), Orientation.WEST);
		movableMower.linkTo(field);
		fixedMower.linkTo(field);

		Command.execute(movableMower, Command.FORWARD);
		PositionAssert.assertThat(movableMower.getPosition()).hasX(0);
		PositionAssert.assertThat(movableMower.getPosition()).hasY(0);
	}

}
