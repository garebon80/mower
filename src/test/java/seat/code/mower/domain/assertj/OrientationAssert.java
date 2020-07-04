package seat.code.mower.domain.assertj;

import org.assertj.core.api.AbstractAssert;

import seat.code.mower.domain.Orientation;

// Assertions is needed if an assertion for Iterable is generated

/**
 * 
 * @author grebon
 * {@link Orientation} specific assertions - Generated by
 * CustomAssertionGenerator.
 */
public class OrientationAssert extends AbstractAssert<OrientationAssert, Orientation> {

	/**
	 * Creates a new </code>{@link OrientationAssert}</code> to make assertions
	 * on actual Orientation.
	 * 
	 * @param actual the Orientation we want to make assertions on.
	 */
	public OrientationAssert(Orientation actual) {
		super(actual, OrientationAssert.class);
	}

	/**
	 * An entry point for OrientationAssert to follow AssertJ standard
	 * <code>assertThat()</code> statements.<br>
	 * With a static import, one's can write directly :
	 * <code>assertThat(myOrientation)</code> and get specific assertion with code completion.
	 * 
	 * @param actual the Orientation we want to make assertions on.
	 * @return a new </code>{@link OrientationAssert}</code>
	 */
	public static OrientationAssert assertThat(Orientation actual) {
		return new OrientationAssert(actual);
	}

	/**
	 * Verifies that the actual Orientation's code is equal to the given one.
	 * 
	 * @param code the given code to compare the actual Orientation's code to.
	 * @return this assertion object.
	 * @throws AssertionError if the actual Orientation's code is not equal to the given one.
	 */
	public OrientationAssert hasCode(String code) {
		// check that actual Orientation we want to make assertions on is not
		// null.
		isNotNull();

		// we overrides the default error message with a more explicit one
		String errorMessage = "\nExpected code of:\n  <%s>\nto be:\n  <%s>\n but was:\n  <%s>";

		// check
		if (!actual.getCode().equals(code)) {
			failWithMessage(errorMessage, actual, code, actual.getCode());
		}

		// return the current assertion for method chaining
		return this;
	}

	/**
	 * Verifies that the actual Orientation's declaringClass is equal to the given one.
	 * 
	 * @param declaringClass the given declaringClass to compare the actual Orientation's declaringClass to.
	 * @return this assertion object.
	 * @throws AssertionError if the actual Orientation's declaringClass is not equal to the given one.
	 */
	public OrientationAssert hasDeclaringClass(Class<?> declaringClass) {
		// check that actual Orientation we want to make assertions on is not
		// null.
		isNotNull();

		// we overrides the default error message with a more explicit one
		String errorMessage = "\nExpected declaringClass of:\n  <%s>\nto be:\n  <%s>\n but was:\n  <%s>";

		// check
		if (!actual.getDeclaringClass().equals(declaringClass)) {
			failWithMessage(errorMessage, actual, declaringClass, actual.getDeclaringClass());
		}

		// return the current assertion for method chaining
		return this;
	}

}
