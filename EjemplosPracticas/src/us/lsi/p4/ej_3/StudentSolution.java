package us.lsi.p4.ej_3;

import java.util.List;
import java.util.stream.IntStream;

public class StudentSolution {
	List<Integer> groupOfStudent;
	double totalAffinity;
	
	public StudentOfSolution(List<Integer> groupOfStudent, double totalAffinity) {
		super();
		this.groupOfStudent = groupOfStudent;
		this.totalAffinity = totalAffinity;
	}
	
	public static StudentSolution createOfPermutation(List<Integer> ch) {
		double affinity = IntStream.range(0, ch.size()).mapToDouble(i -> DatosAlumnos.getAfinidad(i, ch.get(i))).sum();
		return new StudentSolution(ch, affinity);
	}
}
