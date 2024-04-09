 package tests;

import java.util.List;

import exercises.Exercise1;
import us.lsi.common.Pair;
import us.lsi.streams.Stream2;

public class Test1 {
	public static void main(String[] args) {
		testExercise1();
	}

	private static void testExercise1() {
		String file = "files/PI1Ej1DatosEntrada.txt";
		List<Pair<Integer, Integer>> ls = Stream2.file(file).map(
				line -> Pair.parse(line, ",", f_elem -> Integer.parseInt(f_elem), s_elem -> Integer.parseInt(s_elem)))
				.toList();

		ls.forEach(e -> {
			System.out.println("TEST " + (ls.indexOf(e)+1));
			System.out.println("1) Functional:  " + Exercise1.ex1(e.first(), e.second()));
			System.out.println("2) Iterative:  " + Exercise1.ex1_iterative(e.first(), e.second()));
			System.out.println("3) Final:  " + Exercise1.ex1_final(e.first(), e.second()));
			System.out.println("--------------------------------------------------");
		});

	}
}
