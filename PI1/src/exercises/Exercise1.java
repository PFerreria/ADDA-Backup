package exercises;

import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise1 {
	/*
	 * Analyze the code shown below, where IntegerString is a class with an integer
	 * property a and another string property s (which you must implement as a
	 * record):
	 */
	public static String ex1(Integer varA, Integer varB) {
		UnaryOperator<IntegerString> nx = elem -> {
			return IntegerString.of(elem.a() + 3, elem.a() % 2 == 0 ? elem.a() + "*" : elem.a() + "!");
		};
		return Stream.iterate(IntegerString.of(varA, "A"), elem -> elem.a() < varB, nx)
				.filter(elem -> elem.a() % 10 != 0).map(elem -> elem.s()).collect(Collectors.joining("-"));
	}

	public record IntegerString(Integer a, String s) {
		public static IntegerString of(Integer a, String s) {
			return new IntegerString(a, s);
		}
	}

	public static String ex1_iterative(Integer varA, Integer varB) {
		String res = "";
		IntegerString elem = IntegerString.of(varA, "A");
		while (elem.a() < varB) {
			if (elem.a() % 10 != 0) {
				res += elem.s() != "A" ? "-" + elem.s() : elem.s();
			}
			if (elem.a() % 2 == 0) {
				elem = IntegerString.of(elem.a() + 3, elem.a() + "*");
			} else {
				elem = IntegerString.of(elem.a() + 3, elem.a() + "!");
			}
		}
		return res;
	}

	public static String ex1_final(Integer varA, Integer varB) {
		return ex1_aux_final("", IntegerString.of(varA, "A"), varA, varB);
	}

	public static String ex1_aux_final(String res, IntegerString elem, Integer varA, Integer varB) {
		if (elem.a() < varB) {
			if (elem.a() % 10 != 0) {
				res += elem.s() != "A" ? "-" + elem.s() : elem.s();
			}
			if (elem.a() % 2 == 0) {
				return ex1_aux_final(res, IntegerString.of(elem.a() + 3, elem.a() + "*"), varA, varB);
			} else {
				return ex1_aux_final(res, IntegerString.of(elem.a() + 3, elem.a() + "!"), varA, varB);
			}
		}
		return res;
	}
}
