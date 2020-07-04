package seat.code.mower.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

/**
 * 
 * @author grebon
 *
 */
@Test
public class OrientationTest {

	private static final String INCORRECT_VALUE = "A";

	public void testValidOrientations() {

		assertThat(Orientation.values()).containsOnly(Orientation.EAST, Orientation.SOUTH, Orientation.WEST,
				Orientation.NORTH);

		assertThat(Orientation.of("E")).isEqualTo(Orientation.EAST);
		assertThat(Orientation.of("S")).isEqualTo(Orientation.SOUTH);
		assertThat(Orientation.of("W")).isEqualTo(Orientation.WEST);
		assertThat(Orientation.of("N")).isEqualTo(Orientation.NORTH);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = INCORRECT_VALUE
			+ " is not a valid Orientation value.")
	public void testExceptionWhenInvalidOrientations() {
		Orientation.of(INCORRECT_VALUE);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Orientation value must not be null.")
	public void testExceptionWhenNullOrientations() {
		Orientation.of(null);
	}

	public void testValueOrientations() {
		assertThat(Orientation.of("E").getCode()).isEqualTo("E");
		assertThat(Orientation.of("S").getCode()).isEqualTo("S");
		assertThat(Orientation.of("W").getCode()).isEqualTo("W");
		assertThat(Orientation.of("N").getCode()).isEqualTo("N");
	}
}
