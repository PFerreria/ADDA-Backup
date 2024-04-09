package ex2;

import java.util.ArrayList;
import java.util.List;

public class SolutionBasket {

	public static SolutionBasket of_Binary(List<Integer> ls) {
		return new SolutionBasket(ls);
	}

	Integer totPrice;
	List<Integer> solution;

	private SolutionBasket(List<Integer> ls) {
		totPrice = 0;
		solution = new ArrayList<>();
		for (int i = 0; i < ls.size(); i++) {
			if (ls.get(i) == 1) {
				totPrice += DataBasket.getPrice(i);
				solution.add(i);
			}
		}
	}

	@Override
	public String toString() {
		return String.format("Total price of the basket = %d; Selected products = %s", totPrice, solution);
	}
}
