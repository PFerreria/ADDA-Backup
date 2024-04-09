package tests;

import exercises.Exercise3;

public class Test3 {
	public static void main(String[] args) {
		testExercise3();
	}

	private static void testExercise3() {
		String file1A = "files/PI1Ej3DatosEntrada1A.txt";
		String file1B = "files/PI1Ej3DatosEntrada1B.txt";
		String file2A = "files/PI1Ej3DatosEntrada2A.txt";
		String file2B = "files/PI1Ej3DatosEntrada2B.txt";
		String file3A = "files/PI1Ej3DatosEntrada3A.txt";
		String file3B = "files/PI1Ej3DatosEntrada3B.txt";

		System.out.println("TEST 1");
		System.out.println("1) Iterative:  " + Exercise3.ex3_iterative(file1A, file1B));
		System.out.println("2) Final:  " + Exercise3.ex3_final(file1A, file1B));
		System.out.println("3) Functional:  " + Exercise3.ex3_functional(file1A, file1B));
		System.out.println("--------------------------------------------------");
		;
		System.out.println("TEST 2");
		System.out.println("1) Iterative:  " + Exercise3.ex3_iterative(file2A, file2B));
		System.out.println("2) Final:  " + Exercise3.ex3_final(file2A, file2B));
		System.out.println("3) Functional:  " + Exercise3.ex3_functional(file2A, file2B));
		System.out.println("--------------------------------------------------");
		;
		System.out.println("TEST 3");
		System.out.println("1) Iterative:  " + Exercise3.ex3_iterative(file3A, file3B));
		System.out.println("2) Final:  " + Exercise3.ex3_final(file3A, file3B));
		System.out.println("3) Functional:  " + Exercise3.ex3_functional(file3A, file3B));
		System.out.println("--------------------------------------------------");
		;

	}
}
