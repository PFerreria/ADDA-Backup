package ex2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.String2;

public class DataBasket {
	private static Integer budget;
	private static List<Integer> prices;
	private static List<Integer> categories;
	private static List<Integer> ratings;

	public static void iniDatos(String fichero) {
		List<String> lines = Files2.linesFromFile(fichero);
		prices = new ArrayList<>();
		categories = new ArrayList<>();
		ratings = new ArrayList<>();
		for (String line : lines) {
			if (line.contains("=")) {
				budget = Integer.parseInt((line.split("=")[1]).trim());
			} else if (!line.contains("//")) {
				String[] fst = line.split(":");
				prices.add(Integer.parseInt(fst[1]));
				categories.add(Integer.parseInt(fst[2]));
				ratings.add(Integer.parseInt(fst[3]));
			}
		}
		toConsole();
	}

	public static List<Integer> getPrices() {
		return prices;
	}

	public static Integer getPrice(Integer i) {
		return prices.get(i);
	}

	public static List<Integer> getRatings() {
		return ratings;
	}

	public static Integer getRating(Integer i) {
		return ratings.get(i);
	}

	public static List<Integer> getCategories() {
		return categories;
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

	// Read Test
	public static void main(String[] args) {
		iniDatos("input/Ejercicio2DatosEntrada1.txt");
		iniDatos("input/Ejercicio2DatosEntrada2.txt");
		iniDatos("input/Ejercicio2DatosEntrada3.txt");
	}

	public static void toConsole() {
		String2.toConsole("Budget: %d\nPrices: %s\nCategories: %s\nRatings: %s", budget, prices, categories, ratings);
	}

}
