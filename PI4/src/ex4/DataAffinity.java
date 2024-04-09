package ex4;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.String2;

public class DataAffinity {
	private static List<Integer> ages;
	private static List<String> nats;
	private static List<List<String>> langs;
	private static List<List<Integer>> affs;

	public static void iniDatos(String fichero) {
		List<String> lines = Files2.linesFromFile(fichero);
		ages = new ArrayList<>();
		nats = new ArrayList<>();
		langs = new ArrayList<>();
		affs = new ArrayList<>();
		for (String line : lines) {
			if (line.contains("->")) {
				String[] split = line.replace("(", "").replace(")", "").split(";");
				ages.add(Integer.parseInt(split[0].split("=")[1]));
				nats.add(split[2].split("=")[1]);
				String[] lang = split[1].split("=")[1].split(",");
				List<String> lan = new ArrayList<>();
				for (String l : lang) {
					lan.add(l.trim());
				}
				langs.add(lan);
				String[] affin = split[3].split("=")[1].split(",");
				List<Integer> af = new ArrayList<>();
				for (int i = 0; i < affin.length; i++) {
					if (lines.indexOf(line) - 1 == i) {
						af.add(-1);
						af.add(Integer.parseInt(affin[i].split(":")[1]));
					} else
						af.add(Integer.parseInt(affin[i].split(":")[1]));
					if (lines.indexOf(line) == lines.size() - 1 && i == lines.size() - 3)
						af.add(-1);
				}
				affs.add(af);
			}
		}
		toConsole();
	}

	public static List<Integer> getAges() {
		return ages;
	}

	public static List<String> getNationalities() {
		return nats;
	}

	public static List<List<String>> getLanguages() {
		return langs;
	}

	public static List<List<Integer>> getAffinities() {
		return affs;
	}

	public static Integer getAge(Integer i) {
		return ages.get(i);
	}

	public static String getNationality(Integer i) {
		return nats.get(i);
	}

	public static List<String> getLanguages(Integer i) {
		return langs.get(i);
	}

	public static Integer getAffinity(Integer i, Integer j) {
		return affs.get(i).get(j);
	}

	public static Integer getN() {
		return ages.size();
	}

	// Read Test
	public static void main(String[] args) {
		iniDatos("input/Ejercicio4DatosEntrada1.txt");
		iniDatos("input/Ejercicio4DatosEntrada2.txt");
		iniDatos("input/Ejercicio4DatosEntrada3.txt");
	}

	public static void toConsole() {
		String2.toConsole("Ages: %s\nNationalities: %s\nLanguages: %s\nAffinities: %s", ages, nats, langs, affs);
	}

}
