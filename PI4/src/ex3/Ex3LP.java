package ex3;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ex3LP {
	private static List<Integer> destinies;
	private static List<Integer> products;
	private static List<List<Integer>> costs;

	public static Integer getMinDemand(Integer i) {
		return destinies.get(i);
	}

	public static Integer getAvalUnits(Integer i) {
		return products.get(i);
	}

	public static Integer getStoringCost(Integer i, Integer j) {
		return costs.get(i).get(j);
	}

	public static Integer getN() {
		return products.size();
	}

	public static Integer getM() {
		return destinies.size();
	}

	public static void ex3_model(Integer i) throws IOException {
		DataProducts.iniDatos("input/Ejercicio3DatosEntrada" + i + ".txt");

		destinies = DataProducts.getDestinies();
		products = DataProducts.getProducts();
		costs = DataProducts.getCosts();

		String gurobifile = "gurobi_models/Ex3LP_" + i + ".lp";
		AuxGrammar.generate(Ex3LP.class, "lsi_models/ex3LP&GA.lsi", gurobifile);
		GurobiSolution solution = GurobiLp.gurobi(gurobifile);
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		ex3_model(1);
		ex3_model(2);
		ex3_model(3);
	}
}