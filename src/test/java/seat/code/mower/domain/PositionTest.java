package seat.code.mower.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import seat.code.mower.domain.assertj.PositionAssert;

/**
 * 
 * @author grebon
 *
 */
@Test
public class PositionTest {

	public void testPositions() {
		assertThat(new Position(0, 0).toString()).isEqualTo("[0;0]");
		assertThat(new Position(-1, 20).toString()).isEqualTo("[-1;20]");
		assertThat(new Position(13, 6).toString()).isEqualTo("[13;6]");
	}

	public void testPositionEquality() {
		PositionAssert.assertThat(new Position(0, 0)).isEqualTo(new Position(0, 0));
		PositionAssert.assertThat(new Position(-1, 20)).isEqualTo(new Position(-1, 20));
		PositionAssert.assertThat(new Position(13, 6)).isEqualTo(new Position(13, 6));
	}
}
