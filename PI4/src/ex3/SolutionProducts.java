package ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionProducts {

	public static SolutionProducts of_Range(List<Integer> ls) {
		return new SolutionProducts(ls);
	}

	Integer totCost;
	Map<Integer, List<Integer>> solution;

	private SolutionProducts(List<Integer> ls) {
		totCost = 0;
		solution = new HashMap<>();
		for (int i = 0; i < ls.size(); i++) {
			if (!solution.containsKey(i / DataProducts.getM())) {
				List<Integer> sols = new ArrayList<>();
				sols.add(ls.get(i));
				solution.put(i / DataProducts.getM(), sols);
			} else {
				List<Integer> sols = solution.get(i / DataProducts.getM());
				sols.add(ls.get(i));
				solution.put(i / DataProducts.getM(), sols);
			}
			totCost += ls.get(i) * DataProducts.getStoringCost(i / DataProducts.getM(), i % DataProducts.getM());
		}
	}

	@Override
	public String toString() {
		String res = String.format("Final cost = %d", totCost);
		for (Integer i : solution.keySet()) {
			res += String.format("\nP%d: Units sent: %s", i, solution.get(i));
		}
		return res;

	}
}
