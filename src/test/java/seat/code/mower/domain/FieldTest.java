package seat.code.mower.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import seat.code.mower.api.Factory;
import seat.code.mower.api.Grid;

/**
 * 
 * @author grebon
 *
 */
@Test
public class FieldTest {

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Horizontal grid size must be positive.")
	public void testFieldCannotBeZeroHorizontalSized() {
		Factory.newField(0, 10);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Vertical grid size must be positive.")
	public void testFieldCannotBeZeroVerticalSized() {
		Factory.newField(10, 0);
	}

	public void testFieldContainsPositions() {
		Grid field = Factory.newField(2, 3);
		assertThat(field.contains(new Position(0, 0))).isTrue();
		assertThat(field.contains(new Position(0, 1))).isTrue();
		assertThat(field.contains(new Position(0, 2))).isTrue();
		assertThat(field.contains(new Position(1, 0))).isTrue();
		assertThat(field.contains(new Position(1, 1))).isTrue();
		assertThat(field.contains(new Position(1, 2))).isTrue();
	}

	public void testFieldDoesNotContainPosition() {
		Grid field = Factory.newField(2, 3);
		assertThat(field.contains(new Position(2, 3))).isFalse();
		assertThat(field.contains(new Position(4, 2))).isFalse();
		assertThat(field.contains(new Position(-1, 0))).isFalse();
		assertThat(field.contains(new Position(0, -1))).isFalse();
	}

	public void testStringRepresentationOfField() {
		assertThat(Factory.newField(2, 2).toString()).isEqualTo("Field with 2 x 2 size");
		assertThat(Factory.newField(1, 4).toString()).isEqualTo("Field with 1 x 4 size");
		assertThat(Factory.newField(3, 10).toString()).isEqualTo("Field with 3 x 10 size");
	}

}
