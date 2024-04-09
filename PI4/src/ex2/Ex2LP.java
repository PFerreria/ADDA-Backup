package ex2;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Ex2LP {
	private static Integer budget;
	private static List<Integer> prices;
	private static List<Integer> categories;
	private static List<Integer> ratings;

	public static Integer getPrice(Integer i) {
		return prices.get(i);
	}

	public static Integer getRating(Integer i) {
		return ratings.get(i);
	}

	public static Integer getCategories(Integer i) {
		return categories.get(i);
	}

	public static Integer getBudget() {
		return budget;
	}

	public static Integer hasCategory(Integer i, Integer j) {
		return categories.get(i) == j ? 1 : 0;
	}

	public static Integer getN() {
		return prices.size();
	}

	public static Integer getM() {
		Set<Integer> s = new HashSet<>(categories);
		return s.size();
	}

	public static void ex2_model(Integer i) throws IOException {
		DataBasket.iniDatos("input/Ejercicio2DatosEntrada" + i + ".txt");

		budget = DataBasket.getBudget();
		ratings = DataBasket.getRatings();
		prices = DataBasket.getPrices();
		categories = DataBasket.getCategories();

		String gurobifile = "gurobi_models/Ex2LP_" + i + ".lp";
		AuxGrammar.generate(Ex2LP.class, "lsi_models/ex2LP&GA.lsi", gurobifile);
		GurobiSolution solution = GurobiLp.gurobi(gurobifile);
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		ex2_model(1);
		ex2_model(2);
		ex2_model(3);
	}
}
