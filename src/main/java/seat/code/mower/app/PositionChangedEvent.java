package seat.code.mower.app;

import seat.code.mower.api.Movable;
import seat.code.mower.domain.Position;

/**
 *  
 * @author grebon
 * An event to communicate a position changing.
 * 
 */
public class PositionChangedEvent {

	private final Movable source;
	private final Position oldPosition;
	private final Position newPosition;

	public PositionChangedEvent(Movable source, Position oldPosition, Position newPosition) {
		this.source = source;
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
	}

	/**
	 * Get the source, the entity that send this event.
	 * 
	 * @return The source of the event.
	 */
	public Movable getSource() {
		return source;
	}

	/**
	 * Get the old position, the position before changing.
	 * 
	 * @return The old position.
	 */
	public Position getOldPosition() {
		return oldPosition;
	}

	/**
	 * Get the new position, the position after changing.
	 * 
	 * @return The new position.
	 */
	public Position getNewPosition() {
		return newPosition;
	}

}
