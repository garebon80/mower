package seat.code.mower.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.testng.annotations.Test;

import seat.code.mower.exception.MowerParseException;


/**
 * 
 * @author grebon
 *
 */
@Test
public class DefaultMowerParserTest {

	@Test(expectedExceptions = MowerParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 1.")
	public void testExceptionsWhenFieldCoordinatesAreMalformed() throws MowerParseException {
		String file = "-5 5\n1 2 N\nLMLMLMLMM";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new DefaultMowerParser().parse(reader);
	}

	@Test(expectedExceptions = MowerParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 2.")
	public void testExceptionsWhenMowerCoordinatesAreMalformed() throws MowerParseException {
		String file = "5 5\n-1 2 N\nLMLMLMLMM";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new DefaultMowerParser().parse(reader);
	}

	@Test(expectedExceptions = MowerParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 2.")
	public void testExceptionsWhenMowerOrientationIsMalformed() throws MowerParseException {
		String file = "5 5\n1 2 U\nLMLMLMLMM";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new DefaultMowerParser().parse(reader);
	}

	@Test(expectedExceptions = MowerParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 3.")
	public void testExceptionsWhenMowerCommandsAreMalformed() throws MowerParseException {
		String file = "5 5\n1 2 N\nLMLMLVLMM";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new DefaultMowerParser().parse(reader);
	}

	@Test(expectedExceptions = MowerParseException.class, expectedExceptionsMessageRegExp = "Cannot parse the reader content at line 5.")
	public void testExceptionsWhenMowerInformationArePartial() throws MowerParseException {
		String file = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new DefaultMowerParser().parse(reader);
	}

	public void testParseRegularFile() throws MowerParseException {
		String file = "5 5\n1 2 N\nLMLMLMLMM";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
		new DefaultMowerParser().parse(reader);
	}

}
