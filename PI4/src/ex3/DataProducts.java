package ex3;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.String2;

public class DataProducts {
	private static List<Integer> destinies;
	private static List<Integer> products;
	private static List<List<Integer>> costs;

	public static void iniDatos(String fichero) {
		List<String> lines = Files2.linesFromFile(fichero);
		destinies = new ArrayList<>();
		products = new ArrayList<>();
		costs = new ArrayList<>();
		for (String line : lines) {
			if (line.contains(":") && !line.contains("->")) {
				destinies.add(Integer.parseInt(line.replace(";", "").split("=")[1]));
			} else if (!line.contains("//")) {
				String[] fst = line.replace("(", "").replace(")", "").split(";");
				products.add(Integer.parseInt((fst[0].split("="))[1]));
				String[] inc = (fst[1].split("="))[1].split(",");
				List<Integer> acumCosts = new ArrayList<>();
				for (String num : inc) {
					acumCosts.add(Integer.parseInt(num.split(":")[1]));
				}
				costs.add(acumCosts);
			}
		}
		toConsole();
	}

	public static List<Integer> getDestinies() {
		return destinies;
	}

	public static Integer getMinDemand(Integer i) {
		return destinies.get(i);
	}

	public static List<Integer> getProducts() {
		return products;
	}

	public static Integer getAvalUnits(Integer i) {
		return products.get(i);
	}

	public static List<List<Integer>> getCosts() {
		return costs;
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

	// Read Test
	public static void main(String[] args) {
		iniDatos("input/Ejercicio3DatosEntrada1.txt");
		iniDatos("input/Ejercicio3DatosEntrada2.txt");
		iniDatos("input/Ejercicio3DatosEntrada3.txt");
	}

	public static void toConsole() {
		String2.toConsole("Destinies: %s\nProducts: %s\nCosts: %s", destinies, products, costs);
	}

}
