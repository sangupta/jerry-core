package com.sangupta.jerry.util;

import static java.lang.Math.abs;
import static java.math.RoundingMode.HALF_EVEN;
import static java.math.RoundingMode.HALF_UP;

import java.math.RoundingMode;

public class NumberUtils {

	/**
	 * Returns the {@code int} value that is equal to {@code value}, if
	 * possible.
	 *
	 * @param value
	 *            any value in the range of the {@code int} type
	 *
	 * @return the {@code int} value that equals {@code value}
	 *
	 * @throws IllegalArgumentException
	 *             if {@code value} is greater than {@link Integer#MAX_VALUE} or
	 *             less than {@link Integer#MIN_VALUE}
	 */
	public static int checkedCast(long value) {
		int result = (int) value;
		if (result != value) {
			// don't use checkArgument here, to avoid boxing
			throw new IllegalArgumentException("Out of range: " + value);
		}
		
		return result;
	}


	/**
	 * Returns the result of dividing {@code p} by {@code q}, rounding using the
	 * specified {@code RoundingMode}.
	 *
	 *
	 * @param numerator
	 *            the value to be divided
	 *
	 * @param denominator
	 *            the value with which to divide
	 *
	 * @param mode
	 *            the {@link RoundingMode} to use
	 *
	 * @return the division result thus rounded
	 *
	 * @throws ArithmeticException
	 *             if {@code q == 0}, or if {@code mode == UNNECESSARY} and
	 *             {@code a} is not an integer multiple of {@code b}
	 *
	 */
	@SuppressWarnings("fallthrough")
	public static long divide(long numerator, long denominator, RoundingMode mode) {
		if(mode == null) {
			throw new IllegalArgumentException("Rounding mode cannot be null");
		}

		long div = numerator / denominator; // throws if q == 0
		long rem = numerator - denominator * div; // equals p % q

		if (rem == 0) {
			return div;
		}

		/*
		 * Normal Java division rounds towards 0, consistently with
		 * RoundingMode.DOWN. We just have to deal with the cases where rounding
		 * towards 0 is wrong, which typically depends on the sign of p / q.
		 *
		 * signum is 1 if p and q are both nonnegative or both negative, and -1
		 * otherwise.
		 */
		int signum = 1 | (int) ((numerator ^ denominator) >> (Long.SIZE - 1));
		boolean increment;
		switch (mode) {
			case UNNECESSARY:
				if (!(rem == 0)) {
					throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
				}
				// fall through

			case DOWN:
				increment = false;
				break;
			
			case UP:
				increment = true;
				break;
			
			case CEILING:
				increment = signum > 0;
				break;
			
			case FLOOR:
				increment = signum < 0;
				break;
			
			case HALF_EVEN:
			case HALF_DOWN:
			case HALF_UP:
				long absRem = abs(rem);
				long cmpRemToHalfDivisor = absRem - (abs(denominator) - absRem);
				// subtracting two nonnegative longs can't overflow
				// cmpRemToHalfDivisor has the same sign as compare(abs(rem), abs(q)
				// / 2).
				if (cmpRemToHalfDivisor == 0) { // exactly on the half mark
					increment = (mode == HALF_UP | (mode == HALF_EVEN & (div & 1) != 0));
				} else {
					increment = cmpRemToHalfDivisor > 0; // closer to the UP value
				}
				break;
			
			default:
				throw new AssertionError();
		}
		
		return increment ? div + signum : div;
	}

}
