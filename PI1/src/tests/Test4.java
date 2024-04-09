package tests;

import java.util.List;

import exercises.Exercise4;
import us.lsi.common.Pair;
import us.lsi.streams.Stream2;

public class Test4 {
	public static void main(String[] args) {
		testExercise4();
	}

	private static void testExercise4() {
		String file = "files/PI1Ej4DatosEntrada.txt";
		List<Pair<Integer, Integer>> ls = Stream2.file(file).map(
				line -> Pair.parse(line, ",", f_elem -> Integer.parseInt(f_elem), s_elem -> Integer.parseInt(s_elem)))
				.toList();

		ls.forEach(e -> {
			System.out.println("TEST " + (ls.indexOf(e)+1));
			System.out.println("1) Iterative:  " + Exercise4.ex4_iterative(e.first(), e.second()));
			System.out.println("2) Recursive without memory:  " + Exercise4.ex4_nomemory(e.first(), e.second()));
			System.out.println("3) Recursive with memory:  " + Exercise4.ex4_memory(e.first(), e.second()));
			System.out.println("--------------------------------------------------");
		});

	}
}
