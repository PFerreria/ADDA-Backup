package ex3;

import java.util.List;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GeneticsProducts implements ValuesInRangeData<Integer, SolutionProducts> {

	public GeneticsProducts(String line) {
		DataProducts.iniDatos(line);
	}

	@SuppressWarnings("exports")
	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Integer size() {
		return DataProducts.getN() * DataProducts.getM();
	}

	@Override
	public Integer min(Integer i) {
		return -1;
	}

	@Override
	public Integer max(Integer i) {
		return DataProducts.getAvalUnits(i / DataProducts.getM());
	}

	@Override
	public SolutionProducts solucion(List<Integer> ls) {
		return SolutionProducts.of_Range(ls);
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = solucion(ls).totCost, penaltyAval = 0, penaltyDem = 0;

		for (int j = 0; j < DataProducts.getM(); j++) {
			double acumD = 0;
			for (int i = 0; i < DataProducts.getN(); i++) {
				acumD += solucion(ls).solution.get(i).get(j);
			}
			penaltyDem += AuxiliaryAg.distanceToLeZero((double) DataProducts.getMinDemand(j) - acumD);
		}

		for (int i = 0; i < DataProducts.getN(); i++) {
			Integer acumU = solucion(ls).solution.get(i).stream().mapToInt(Integer::intValue).sum();
			penaltyAval += AuxiliaryAg.distanceToLeZero((double) acumU - DataProducts.getAvalUnits(i));
		}

		return -goal - 1000 * (penaltyAval + penaltyDem);
	}
}
