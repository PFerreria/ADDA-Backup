package ex1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.String2;

public class DataFields {
	private static List<Integer> crops;
	private static List<Integer> fields;
	private static Set<Set<Integer>> incomp;

	public static void iniDatos(String fichero) {
		List<String> lines = Files2.linesFromFile(fichero);
		crops = new ArrayList<>();
		fields = new ArrayList<>();
		incomp = new HashSet<>();
		for (String line : lines) {
			if (line.contains(":")) {
				fields.add(Integer.parseInt(line.replace(";", "").split("=")[1]));
			} else if (line.contains("->")) {
				String[] fst = line.replace("V", "").split(";");
				crops.add(Integer.parseInt((fst[0].split("="))[1]));
				String[] inc = (fst[1].split("="))[1].split(",");
				for (String num : inc) {
					Set<Integer> s = new HashSet<>(List.of(crops.size() - 1, Integer.parseInt(num)));
					incomp.add(s);
				}
			}
		}
		toConsole();
	}

	public static List<Integer> getCrops() {
		return crops;
	}

	public static List<Integer> getFields() {
		return fields;
	}

	public static Set<Set<Integer>> getIncomp() {
		return incomp;
	}

	public static Integer getN() {
		return crops.size();
	}

	public static Integer getM() {
		return fields.size();
	}

	public static Integer getFieldSize(Integer i) {
		return fields.get(i);
	}

	public static Integer getCropSize(Integer i) {
		return crops.get(i);
	}

	public static Integer isIncompatible(Integer i, Integer j) {
		if (i == j)
			return 0;
		Set<Integer> input = Set.of(i, j);
		return incomp.contains(input) ? 1 : 0;
	}

	// Read Test
	public static void main(String[] args) {
		iniDatos("input/Ejercicio1DatosEntrada1.txt");
		iniDatos("input/Ejercicio1DatosEntrada2.txt");
		iniDatos("input/Ejercicio1DatosEntrada3.txt");
	}

	public static void toConsole() {
		String2.toConsole("Fields: %s\nCrops: %s\nIncompatibilities: %s", fields, crops, incomp);
	}

}
