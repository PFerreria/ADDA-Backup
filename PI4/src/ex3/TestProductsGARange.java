package ex3;

import java.util.List;
import java.util.Locale;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestProductsGARange {

	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		for (Integer i : List.of(1, 2, 3)) {
			if (i == 1) {
				AlgoritmoAG.ELITISM_RATE = 0.10;
				AlgoritmoAG.CROSSOVER_RATE = 0.95;
				AlgoritmoAG.MUTATION_RATE = 0.8;
				AlgoritmoAG.POPULATION_SIZE = 1000;

				StoppingConditionFactory.NUM_GENERATIONS = 1000;
				StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;

				GeneticsProducts p = new GeneticsProducts("input/Ejercicio3DatosEntrada" + i + ".txt");

				AlgoritmoAG<List<Integer>, SolutionProducts> ap = AlgoritmoAG.of(p);
				ap.ejecuta();

				System.out.println("================================");
				System.out.println(ap.bestSolution());
				System.out.println("================================");

			} else { // Require more computation power (bit extreme)
				AlgoritmoAG.ELITISM_RATE = 0.010;
				AlgoritmoAG.CROSSOVER_RATE = 0.95;
				AlgoritmoAG.MUTATION_RATE = 0.8;
				AlgoritmoAG.POPULATION_SIZE = 1000;

				StoppingConditionFactory.NUM_GENERATIONS = 50000;
				StoppingConditionFactory.stoppingConditionType = StoppingConditionFactory.StoppingConditionType.GenerationCount;

				GeneticsProducts p = new GeneticsProducts("input/Ejercicio3DatosEntrada" + i + ".txt");

				AlgoritmoAG<List<Integer>, SolutionProducts> ap = AlgoritmoAG.of(p);
				ap.ejecuta();

				System.out.println("================================");
				System.out.println(ap.bestSolution());
				System.out.println("================================");
			}
		}
	}
}
