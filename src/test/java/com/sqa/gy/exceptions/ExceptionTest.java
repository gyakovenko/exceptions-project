package com.sqa.gy.exceptions;

import org.testng.*;
import org.testng.annotations.*;

import com.sqa.gy.helpers.*;

public class ExceptionTest {

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { 0, 100 }, new Object[] { -50, 25 }, };
	}

	// Right now our execute() throws div by 3 before div by 7 so if its also
	// div by 3, it will NOT be able to catch the by7 exception. Once by3 is
	// thrown execute will stop
	@Test(dataProvider = "dp")
	public void testDivisibleBy7Exception(int min, int max) throws InvalidNumberException {
		System.out.println("\nTesting DivisibleBy7Exception");
		boolean correctExceptionCaught = false;
		try {
			execute(min, max);
		} catch (DivisibleBy7Exception e) {
			System.out.println("Exception: Number is divisible by 7");
			correctExceptionCaught = true;
		} catch (InvalidNumberException e) {
			System.out.println("Exception: Something other than \"Divisible by7.\"");
		}
		Assert.assertEquals(correctExceptionCaught, true);
	}

	@Test(dataProvider = "dp")
	public void testInvalidNumberException(int min, int max) {
		System.out.println("\nTesting InvalidNumberException");
		boolean correctExceptionCaught = false;
		try {
			execute(min, max);
		} catch (InvalidNumberException e) {
			System.out.println("Exception: Number is not valid");
			correctExceptionCaught = true;
		} catch (Exception e) {
			System.out.println("Exception: Something else went wrong");
		}
		Assert.assertEquals(correctExceptionCaught, true);
	}

	@Test(dataProvider = "dp")
	public void testNumberOutOfRangeException(int min, int max) {
		System.out.println("\nTesting NumberOutOfRangeException");
		boolean correctExceptionCaught = false;
		try {
			execute(min, max);
		} catch (NumberOutOfRangeException e) {
			System.out.println("Exception: Number is not in range");
			correctExceptionCaught = true;
		} catch (Exception e) {
			System.out.println("Exception: Something else other than range");
		}
		Assert.assertEquals(correctExceptionCaught, true);
	}

	private void execute(int minNumber, int maxNumber)
			throws UnderMinException, OverMaxException, DivisibleBy3Exception, DivisibleBy7Exception {
		System.out.println("Min is: " + minNumber + "\tMax is: " + maxNumber);
		int userNumber = AppBasics.requestIntFromUser("Please provide a number:");
		if (userNumber < minNumber) {
			throw new UnderMinException();
		}
		if (userNumber > maxNumber) {
			throw new OverMaxException();
		}
		if (userNumber % 3 == 0) {
			throw new DivisibleBy3Exception();
		}
		if (userNumber % 7 == 0) {
			throw new DivisibleBy7Exception();
		}
	}
}
