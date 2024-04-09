package filepackage;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class ProcessorPLI {
	public static List<Integer> durations;
	public static int n;
	public static int m;

	public static Integer getNTask() {
		return durations.size();
	}

	public static Integer getNProc() {
		return m;
	}

	public static Integer getDur(Integer i) {
		return durations.get(i);
	}

	public static void proc_model() throws IOException {
		durations = List.of(10, 5, 12, 15, 3, 1);
		m = 4;
		AuxGrammar.generate(ProcessorPLI.class, "data/ProcessorLSI.lsi", "ficheros/proc.lp");
		GurobiSolution solution = GurobiLp.gurobi("ficheros/proc.lp");
		Locale.setDefault(Locale.of("en", "US"));
		System.out.println(solution.toString((s, d) -> d > 0.));
	}

	public static void main(String[] args) throws IOException {
		proc_model();
	}

}