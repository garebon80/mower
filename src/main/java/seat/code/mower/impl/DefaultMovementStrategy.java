package seat.code.mower.impl;

import seat.code.mower.api.MovementStrategy;
import seat.code.mower.domain.Movement;
import seat.code.mower.domain.Orientation;
import seat.code.mower.domain.Position;

/**
 * 
 * @author grebon
 * 
 * This default movement strategy is a simple application of a forward movement.
 */
public class DefaultMovementStrategy implements MovementStrategy {

	@Override
	public Position move(Movement movement, Position position, Orientation orientation) {

		Position target;

		switch (movement) {
		case FORWARD:
			switch (orientation) {
			case NORTH:
				target = new Position(position.getX(), position.getY() + movement.getStep());
				break;
			case EAST:
				target = new Position(position.getX() + movement.getStep(), position.getY());
				break;
			case SOUTH:
				target = new Position(position.getX(), position.getY() - movement.getStep());
				break;
			case WEST:
				target = new Position(position.getX() - movement.getStep(), position.getY());
				break;
			default:
				throw new IllegalArgumentException("orientation looks weird: " + orientation);
			}
			break;
		default:
			throw new IllegalArgumentException("Illegal command for a movement: " + movement);
		}

		return target;

	}

}
