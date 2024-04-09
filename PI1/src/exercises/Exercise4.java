package exercises;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.IntPair;

public class Exercise4 {
	public static String ex4_iterative(Integer a, Integer b) {
		String res = "";
		Map<IntPair, String> map = new HashMap<>();
		for (int i = 0; i <= a; i++) {
			for (int j = 0; j <= b; j++) {
				if (i <= 4) {
					res = Integer.toString(i) + "." + Integer.toString(j);
				} else if (j <= 4) {
					res = Integer.toString(j) + "-" + Integer.toString(i);
				} else {
					res = map.get(IntPair.of(i / 2, j - 2)) + "," + map.get(IntPair.of(i - 2, j / 2)) + ","
							+ map.get(IntPair.of(i - 1, j - 1));
				}
				map.put(IntPair.of(i, j), res);
			}
		}
		return res;
	}

	public static String ex4_nomemory(Integer a, Integer b) {
		String res;
		if (a <= 4) {
			res = Integer.toString(a) + "." + Integer.toString(b);
		} else if (b <= 4) {
			res = Integer.toString(b) + "-" + Integer.toString(a);
		} else {
			res = ex4_nomemory(a / 2, b - 2) + "," + ex4_nomemory(a - 2, b / 2) + "," + ex4_nomemory(a - 1, b - 1);
		}
		return res;
	}

	public static String ex4_memory(Integer a, Integer b) {
		String res;
		Map<IntPair, String> map = new HashMap<>();
		if (map.containsKey(IntPair.of(a, b))) {
			res = map.get(IntPair.of(a, b));
		} else {
			if (a <= 4) {
				res = Integer.toString(a) + "." + Integer.toString(b);
			} else if (b <= 4) {
				res = Integer.toString(b) + "-" + Integer.toString(a);
			} else {
				res = ex4_memory(a / 2, b - 2) + "," + ex4_memory(a - 2, b / 2) + "," + ex4_memory(a - 1, b - 1);
			}
			map.put(IntPair.of(a, b), res);
		}
		return res;
	}

}