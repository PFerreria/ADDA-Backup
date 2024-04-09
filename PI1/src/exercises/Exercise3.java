package exercises;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import us.lsi.common.Files2;

public class Exercise3 {
	public static List<String> ex3_iterative(String fileA, String fileB) {
		List<String> ls = new ArrayList<>();
		Iterator<String> itA = Files2.linesFromFile(fileA).iterator();
		Iterator<String> itB = Files2.linesFromFile(fileB).iterator();
		while (itA.hasNext() || itB.hasNext()) {
			if (itA.hasNext()) {
				ls.add(itA.next());
				if (itA.hasNext()) {
					ls.add(itA.next());
				}

			}
			if (itB.hasNext()) {
				ls.add(itB.next());
				if (itB.hasNext()) {
					ls.add(itB.next());
				}
			}
		}
		return ls;
	}

	public record Tuple(List<String> ls, Iterator<String> itA, Iterator<String> itB) {
		public static Tuple of(List<String> ls, Iterator<String> itA, Iterator<String> itB) {
			return new Tuple(ls, itA, itB);
		}

		public Tuple condition() {
			if (itA.hasNext()) {
				ls.add(itA.next());
				if (itA.hasNext()) {
					ls.add(itA.next());
				}

			}
			if (itB.hasNext()) {
				ls.add(itB.next());
				if (itB.hasNext()) {
					ls.add(itB.next());
				}
			}
			return Tuple.of(ls, itA, itB);
		}
	}

	public static List<String> ex3_functional(String fileA, String fileB) {
		return Stream.iterate(Tuple.of(new ArrayList<>(), Files2.linesFromFile(fileA).iterator(),
						Files2.linesFromFile(fileB).iterator()), t -> t.condition())
				.dropWhile(t -> (t.itA()).hasNext() || (t.itB()).hasNext()).findFirst().get().ls();
	}

	public static List<String> ex3_final(String fileA, String fileB) {
		return ex3_aux_final(new ArrayList<>(), Files2.linesFromFile(fileA).iterator(),
				Files2.linesFromFile(fileB).iterator());
	}

	public static List<String> ex3_aux_final(List<String> ls, Iterator<String> itA, Iterator<String> itB) {
		if (itA.hasNext() || itB.hasNext()) {
			if (itA.hasNext()) {
				ls.add(itA.next());
				if (itA.hasNext()) {
					ls.add(itA.next());
				}

			}
			if (itB.hasNext()) {
				ls.add(itB.next());
				if (itB.hasNext()) {
					ls.add(itB.next());
				}
			}
			ex3_aux_final(ls, itA, itB);
		}
		return ls;
	}

}
