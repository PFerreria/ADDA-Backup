package ex1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolutionFields {

	public static SolutionFields of_Range(List<Integer> ls) {
		return new SolutionFields(ls);
	}

	Integer total;
	Map<Integer, Integer> solution;

	private SolutionFields(List<Integer> ls) {
		solution = new HashMap<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) >= 0) {
				solution.put(i, ls.get(i));
			}
		}
		total = solution.size();
	}

	@Override
	public String toString() {
		String res = String.format("Total varieties = %d;", total);
		for (Integer i : solution.keySet()) {
			res += String.format("\nV%d: Field %d", i, solution.get(i));
		}
		return res;
	}
}
