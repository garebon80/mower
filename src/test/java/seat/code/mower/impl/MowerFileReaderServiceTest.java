package seat.code.mower.impl;

import org.testng.annotations.Test;

/**
 * 
 * @author grebon
 *
 */
@Test
public class MowerFileReaderServiceTest {

	public static final String VALID_FILENAME = "src/main/resources/mower.txt";
	public static final String BAD_FILENAME = "mower.txt";

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Filename argument must not be null.")
	public void testExceptionWhenFilenameIsNull() {
		new MowerFileReaderService(null, new DefaultMowerParser());
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Parser argument must not be null.")
	public void testExceptionWhenParserIsNull() {
		new MowerFileReaderService(VALID_FILENAME, null);
	}

	@Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "File " + BAD_FILENAME
			+ " cannot be opened.")
	public void testExceptionWhenFileDoesNotExists() {
		new MowerFileReaderService(BAD_FILENAME, new DefaultMowerParser());
	}

}
