package ex4;

import java.util.List;

import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.Pair;

public class GeneticsAffinityPerm implements SeqNormalData<SolutionAffinity> {

	public GeneticsAffinityPerm(String linea) {
		DataAffinity.iniDatos(linea);
	}

	@SuppressWarnings("exports")
	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

	@Override
	public Integer itemsNumber() {
		return DataAffinity.getN();
	}

	@Override
	public SolutionAffinity solucion(List<Integer> ls) {
		return SolutionAffinity.of_Perm(ls);
	}

	@Override
	public Double fitnessFunction(List<Integer> ls) {
		double goal = solucion(ls).total, penaltyAge = 0, penaltyLang = 0, penaltyNat = 0;
		for (int i = 0; i < solucion(ls).solution.size(); i++) {
			Pair<Integer, Integer> p = solucion(ls).solution.get(i);
			penaltyLang += !DataAffinity.getLanguages(p.first()).stream()
					.anyMatch(DataAffinity.getLanguages(p.second())::contains) ? 1 : 0;
			penaltyAge += AuxiliaryAg.distanceToLeZero(
					(double) Math.abs(DataAffinity.getAge(p.first()) - DataAffinity.getAge(p.second())) - 5);
			penaltyNat += DataAffinity.getNationality(p.first()).equals(DataAffinity.getNationality(p.second())) ? 1
					: 0;
		}
		return goal - 10000 * (penaltyAge + penaltyLang * 1000 + penaltyNat * 1000);
	}

}
