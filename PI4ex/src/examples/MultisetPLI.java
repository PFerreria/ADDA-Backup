package examples;

import java.io.IOException;
import java.util.List;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class MultisetPLI {
	public static List<Integer> numbers;
	public static int reqSum;

	static void init(List<Integer> numbers, Integer reqSum) {
		MultisetPLI.numbers = numbers;
		MultisetPLI.reqSum = reqSum;
	}

	static void init(String file) {
		return;
	}

	public static Integer getRequiredSum() {
		return reqSum;
	}

	public static Integer getM() {
		return numbers.size();
	}

	public static Integer e(Integer i) {
		return numbers.get(i);
	}

	public static void multiset_model() throws IOException {
		// 1) Populate the data
		MultisetPLI.init(List.of(1, 6, 9), 24);
		// 2) Convert LSI to PL (Gurobi)
		String gurobiFile = "gurobi_models/multiset.lp";
		AuxGrammar.generate(MultisetPLI.class, "lsi_models/multiset.lsi", gurobiFile);
		// 3) Solve PL
		GurobiSolution sol = GurobiLp.gurobi(gurobiFile);
		// 4) Show solution
		System.out.println(sol.toString((k, v) -> v > 0.));
	}

	public static void main(String[] args) throws IOException {
		multiset_model();
	}

}