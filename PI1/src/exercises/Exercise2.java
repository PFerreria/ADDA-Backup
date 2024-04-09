package exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Exercise2 {
	public static List<Integer> ex2_iterative(Integer a, Integer b) {
		List<Integer> ls = new ArrayList<>();
		while (!(a < 2 || b < 2)) {
			if (a > b) {
				ls.add(0, a);
				a = a % b;
				b = b - 1;
			} else {
				ls.add(0, b);
				a = a - 2;
				b = b / 2;
			}
		}
		ls.add(0, a * b);
		return ls;
	}

	public static List<Integer> ex2_nonfinal(Integer a, Integer b) {
		List<Integer> ls;
		if (a < 2 || b < 2) {
			ls = new ArrayList<>();
			ls.add(a * b);
		} else if (a > b) {
			ls = ex2_nonfinal(a % b, b - 1);
			ls.add(a);
		} else {
			ls = ex2_nonfinal(a - 2, b / 2);
			ls.add(b);
		}
		return ls;
	}

	public static List<Integer> ex2_final(Integer a, Integer b) {
		return ex2_aux_final(new ArrayList<>(), a, b);
	}

	public static List<Integer> ex2_aux_final(List<Integer> ls, Integer a, Integer b) {
		if (a < 2 || b < 2) {
			ls.add(a * b);
		} else if (a > b) {
			ls = ex2_aux_final(ls, a % b, b - 1);
			ls.add(a);
		} else {
			ls = ex2_aux_final(ls, a - 2, b / 2);
			ls.add(b);
		}
		return ls;
	}

	public record Tuple(List<Integer> ls, Integer a, Integer b) {
		public static Tuple of(List<Integer> ls, Integer a, Integer b) {
			return new Tuple(ls, a, b);
		}

		public Tuple next() {
			if (a > b) {
				ls.add(0, a);
			} else {
				ls.add(0, b);
			}
				return a > b ? Tuple.of(ls, a % b, b - 1) : Tuple.of(ls, a - 2, b / 2);
			}
		}

	public static List<Integer> ex2_functional(Integer a, Integer b) {
		Tuple tup = Stream.iterate(Tuple.of(new ArrayList<>(), a, b), t -> t.next()).filter(t -> t.a() < 2 || t.b() < 2)
				.findFirst().get();
		tup.ls().add(0, tup.a() * tup.b());
		return tup.ls();
	}
}
