package ex1;

import java.util.List;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GeneticsFieldsRange implements ValuesInRangeData<Integer, SolutionFields> {

	public GeneticsFieldsRange(String file) {
		DataFields.iniDatos(file);
	}

	@SuppressWarnings("exports")
	@Override
	public ChromosomeType type() {
		return ChromosomeType.Range;
	}

	@Override
	public Integer size() {
		return DataFields.getN();
	}

	@Override
	public Integer min(Integer i) {
		return -2;
	}

	@Override
	public Integer max(Integer i) {
		return DataFields.getM();
	}

	@Override
	public SolutionFields solucion(List<Integer> ls) {
		return SolutionFields.of_Range(ls);
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = solucion(ls).total, penaltySize = 0, penaltyIncomp = 0;
		for (int j = 0; j < DataFields.getM(); j++) {
			Double acumBadM = 0.;
			for (int i = 0; i < ls.size(); i++) {
				if (ls.get(i) >= 0 && ls.get(i) == j) {
					acumBadM += DataFields.getCropSize(i);
				}
				for (int k = 0; k < ls.size(); k++) {
					if (ls.get(i) == ls.get(k)) {
						penaltyIncomp += DataFields.isIncompatible(i, k);
					}
				}
			}
			if (DataFields.getFieldSize(j) <= acumBadM) {
				penaltySize += AuxiliaryAg.distanceToGeZero((double) DataFields.getFieldSize(j) - acumBadM);
			}

		}

		return goal - 1000 * (penaltySize + penaltyIncomp);
	}

}
