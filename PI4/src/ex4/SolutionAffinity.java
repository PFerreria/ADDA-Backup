package ex4;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Pair;

public class SolutionAffinity {

	public static SolutionAffinity of_Perm(List<Integer> ls) {
		return new SolutionAffinity(ls);
	}

	Integer total;
	List<Pair<Integer, Integer>> solution;

	private SolutionAffinity(List<Integer> ls) {
		total = 0;
		// List<Integer> ls1 = List.of(8,3,2,4,5,0,7,1,6);
		solution = new ArrayList<>();
		for (int i = 0; i < ls.size() - 1; i += 2) {
			if(ls.get(i) == 0 || ls.get(i) == 8 || ls.get(i+1) == 0 || ls.get(i+1º) == 8)
				total += DataAffinity.getAffinity(ls.get(i + 1), ls.get(i));
			solution.add(Pair.of(ls.get(i + 1), ls.get(i)));
		}
	}

	@Override
	public String toString() {
		return String.format("Total Affinity = %d;\nPairs= %s", total, solution);
	}
}
