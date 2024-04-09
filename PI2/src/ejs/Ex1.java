package ejs;

import java.math.BigInteger;

public class Ex1 {
	public static BigInteger ex1BI_recursive(Integer a) {
		BigInteger r = null;
		if (a < 6) {
			r = BigInteger.TEN;
		} else {
			r = BigInteger.valueOf(a).pow(3).multiply(ex1BI_recursive(a - 1));
		}
		return r;
	}

	public static BigInteger ex1BI_iterative(Integer a) {
		BigInteger r = BigInteger.TEN;
		while (a >= 6) {
			r = BigInteger.valueOf(a).pow(3).multiply(r);
			a--;
		}
		return r;
	}

	public static Double ex1D_recursive(Integer a) {
		Double r;
		if (a < 6) {
			r = 10.;
		} else {
			r = Math.pow(a, 3) * ex1D_recursive(a - 1);
		}
		return r;
	}

	public static Double ex1D_iterative(Integer a) {
		Double r = 10.;
		while (a >= 6) {
			r *= Math.pow(a, 3);
			a -= 1;
		}
		return r;
	}

}
