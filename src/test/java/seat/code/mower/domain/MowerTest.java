package seat.code.mower.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import seat.code.mower.api.Factory;
import seat.code.mower.api.PositionController;
import seat.code.mower.domain.assertj.MowerAssert;

/**
 * 
 * @author grebon
 *
 */
@Test
public class MowerTest {

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Position must not be null.")
	public void testMowerCannotHaveNullPosition() {
		new Mower(null, Orientation.of("N"));
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Orientation must not be null.")
	public void testMowerCannotHaveNullOrientation() {
		new Mower(new Position(0, 0), null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Movement strategy must not be null.")
	public void testMowerCannotHaveNullOrientationStrategy() {
		new Mower(new Position(0, 0), Orientation.of("N"), null);
	}

	public void testMowerEquality() {
		MowerAssert.assertThat(Factory.newMower(Factory.newPosition(2, 3), Orientation.NORTH)).isEqualTo(
				Factory.newMower(Factory.newPosition(2, 3), Orientation.NORTH));
		MowerAssert.assertThat(Factory.newMower(Factory.newPosition(12, 5), Orientation.WEST)).isEqualTo(
				Factory.newMower(Factory.newPosition(12, 5), Orientation.WEST));
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Cannot link the mower to a null field.")
	public void testMowerCannotBeLinkedToNullController() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.linkTo(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Cannot unlink the mower from a null field.")
	public void testMowerCannotBeUnlinkFromNullController() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.linkTo(Factory.newField(1, 1));
		mower.unlinkFrom(null);
	}

	@Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "This mower is already linked. You must unlink it before.")
	public void testMowerCannotBeLinkTwoTimes() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.linkTo(Factory.newField(1, 1));
		mower.linkTo(Factory.newField(1, 1));
	}

	@Test(expectedExceptions = IllegalStateException.class, expectedExceptionsMessageRegExp = "This mower is not linked. You must link it before.")
	public void testMowerCannotBeUnlinkIfNotLinkBefore() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.unlinkFrom(Factory.newField(1, 1));
	}

	public void testLinkThenUnlinkMowerFromPositionController() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		PositionController field = Factory.newField(1, 1);
		mower.linkTo(field);
		mower.unlinkFrom(field);
		assertThat(mower.getPositionController()).isNull();
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Target position must not be null.")
	public void testCannotChangePositionToNullPosition() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.changeMyPositionTo(null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Target orientation must not be null.")
	public void testCannotChangeOrientationToNullOrientation() {
		Mower mower = Factory.newMower(Factory.newPosition(0, 0));
		mower.changeMyOrientationTo(null);
	}

}
