package seat.code.mower.impl;

import org.testng.annotations.Test;

import static seat.code.mower.api.Factory.newPosition;
import seat.code.mower.domain.Movement;
import seat.code.mower.domain.Orientation;
import seat.code.mower.domain.Position;
import seat.code.mower.domain.assertj.PositionAssert;

/**
 * 
 * @author grebon
 *
 */
public class DefaultMovementStrategyTest {

	@Test
	public void testDefaultStrategyWhenOrientedToTheNorth() {
		DefaultMovementStrategy strategy = new DefaultMovementStrategy();
		Position target = strategy.move(Movement.FORWARD, newPosition(3, 3), Orientation.NORTH);
		PositionAssert.assertThat(target).hasX(3);
		PositionAssert.assertThat(target).hasY(4);
	}

	@Test
	public void testDefaultStrategyWhenOrientedToTheEast() {
		DefaultMovementStrategy strategy = new DefaultMovementStrategy();
		Position target = strategy.move(Movement.FORWARD, newPosition(3, 3), Orientation.EAST);
		PositionAssert.assertThat(target).hasX(4);
		PositionAssert.assertThat(target).hasY(3);
	}

	@Test
	public void testDefaultStrategyWhenOrientedToTheSouth() {
		DefaultMovementStrategy strategy = new DefaultMovementStrategy();
		Position target = strategy.move(Movement.FORWARD, newPosition(3, 3), Orientation.SOUTH);
		PositionAssert.assertThat(target).hasX(3);
		PositionAssert.assertThat(target).hasY(2);
	}

	@Test
	public void testDefaultStrategyWhenOrientedToTheWest() {
		DefaultMovementStrategy strategy = new DefaultMovementStrategy();
		Position target = strategy.move(Movement.FORWARD, newPosition(3, 3), Orientation.WEST);
		PositionAssert.assertThat(target).hasX(2);
		PositionAssert.assertThat(target).hasY(3);
	}

}
