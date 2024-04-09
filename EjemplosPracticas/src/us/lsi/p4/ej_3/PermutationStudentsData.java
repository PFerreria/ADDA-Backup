package us.lsi.p4.ej_3;

import java.util.List;
import java.util.stream.IntStream;

import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class PermutationStudentsData implements SeqNormalData<StudentSolution>{

	@Override
	public ChromosomeType type() {
		return ChromosomeType.Permutation;
	}

	@Override
	public Integer itemsNumber() {
		return DatosAlumnos.getNumGrupos();
	}
	
	@Override
	public Integer maxMultiplicity(int index) {
		return DatosAlumnos.getTamGrupo();
	}
	
	@Override
	public StudentSolution solucion(List<Integer> value) {
		return StudentSolution.createOfPermutation(value);
	}
	
	@Override
	public Double fitnessFunction(List<Integer> value) {
		double of;
		double affPenalty;
		double K;
		StudentSolution s = solucion(value);
		of = s.totalAffinity;
		affPenalty = IntStream.range(0, value.size()).mapToDouble(st -> DatosAlumnos.getAfinidad(st, value.get(st))).filter(aff -> aff == 0).count();
		//K = 1000.;
		K = 10 * DatosAlumnos.getSumTotalAffinity();
		return of - K*affPenalty;
	}
	
}
