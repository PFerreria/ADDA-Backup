package ex2;

import java.util.List;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GeneticsBasketBinary implements ValuesInRangeData<Integer, SolutionBasket> {

	public GeneticsBasketBinary(String linea) {
		DataBasket.iniDatos(linea);
	}

	@SuppressWarnings("exports")
	@Override
	public ChromosomeType type() {
		return ChromosomeType.Binary;
	}

	@Override
	public Integer size() {
		return DataBasket.getN();
	}

	@Override
	public Integer min(Integer i) {
		return 0;
	}

	@Override
	public Integer max(Integer i) {
		return 1;
	}

	@Override
	public SolutionBasket solucion(List<Integer> ls) {
		return SolutionBasket.of_Binary(ls);
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = solucion(ls).totPrice, acumRating = 0, total = 0, penaltyCat = 0, penaltyRat = 0,
				penaltyBudget = 0;
		for (int j = 0; j < DataBasket.getM(); j++) {
			double acumCat = 0, acumCatP = 0;
			for (int i = 0; i < ls.size(); i++) {
				if (ls.get(i) == 1) {
					total++;
					acumRating += DataBasket.getRating(i);
					acumCat += DataBasket.hasCategory(i, j);
					if (DataBasket.hasCategory(i, j) == 1) {
						acumCatP += DataBasket.getPrice(i);
					}
				}
			}
			if (DataBasket.getBudget() <= acumCatP) {
				penaltyBudget += AuxiliaryAg.distanceToGeZero((double) DataBasket.getBudget() - acumCatP);
			}
			if (acumCat <= 1) {
				penaltyCat += AuxiliaryAg.distanceToGeZero((double) acumCat - 1);
			}

		}
		if (acumRating <= total * 3) {
			penaltyRat = AuxiliaryAg.distanceToGeZero((double) acumRating - total * 3);
		}
		return -goal - 1000 * (penaltyBudget + penaltyRat + penaltyCat);
	}

}
