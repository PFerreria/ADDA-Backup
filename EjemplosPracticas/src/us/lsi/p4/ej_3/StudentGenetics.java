package us.lsi.p4.ej_3;

import java.util.List;

import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;

public class StudentGenetics {
	public static void main(String[] args) {
	
		AlgoritmoAG.ELITISM_RATE  = 0.30;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 50;
	
		StoppingConditionFactory.NUM_GENERATIONS = 5000;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 0.;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.GenerationCount;
		
		DatosAlumnos.iniDatos("files/Ejemplo3DatosEntrada1.txt");
		PermutationStudentsData p = new PermutationStudentsData();
		AlgoritmoAG<List<Integer>, StudentSolution> ag = AlgoritmoAG.of(p);
	}
}
