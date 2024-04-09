package ex1;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ex1LP {
	private static List<Integer> crops;
	private static List<Integer> fields;
	private static Set<Set<Integer>> incomp;

	public static Integer getN() {
		return crops.size();
	}

	public static Integer getM() {
		return fields.size();
	}

	public static Integer getFieldSize(Integer i) {
		return fields.get(i);
	}

	public static Integer getCropSize(Integer i) {
		return crops.get(i);
	}

	public static Integer isIncompatible(Integer i, Integer j) {
		if (i == j)
			return 0;
		Set<Integer> input = Set.of(i, j);
		return incomp.contains(input) ? 1 : 0;
	}

	public static void ex1_model(Integer i) throws IOException {
		DataFields.iniDatos("input/Ejercicio1DatosEntrada" + i + ".txt");

		crops = DataFields.getCrops();
		fields = DataFields.getFields();
		incomp = DataFields.getIncomp();

		String gurobifile = "gurobi_models/Ex1LP_" + i + ".lp";
		AuxGrammar.generate(Ex1LP.class, "lsi_models/ex1LP.lsi", gurobifile);
		GurobiSolution solution = GurobiLp.gurobi(gurobifile);
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		ex1_model(1);
		ex1_model(2);
		ex1_model(3);
	}
}
