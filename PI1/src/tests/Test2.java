package tests;

import java.util.List;

import exercises.Exercise2;
import us.lsi.common.Pair;
import us.lsi.streams.Stream2;

public class Test2 {
	public static void main(String[] args) {
		testExercise2();
	}

	private static void testExercise2() {
		String file = "files/PI1Ej2DatosEntrada.txt";
		List<Pair<Integer, Integer>> ls = Stream2.file(file).map(
				line -> Pair.parse(line, ",", f_elem -> Integer.parseInt(f_elem), s_elem -> Integer.parseInt(s_elem)))
				.toList();

		ls.forEach(e -> {
			System.out.println("TEST " + (ls.indexOf(e)+1));
			System.out.println("1) Functional:  " + Exercise2.ex2_functional(e.first(), e.second()));
			System.out.println("2) Iterative:  " + Exercise2.ex2_iterative(e.first(), e.second()));
			System.out.println("3) Final:  " + Exercise2.ex2_final(e.first(), e.second()));
			System.out.println("4) Non final:  " + Exercise2.ex2_nonfinal(e.first(), e.second()));
			System.out.println("--------------------------------------------------");
		});

	}
}
