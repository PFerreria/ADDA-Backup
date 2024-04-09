package ejemplo1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import us.lsi.graphs.virtual.VirtualVertex;

public record MultiConjuntoVertex(Integer index, Integer acumValue)
		implements VirtualVertex<MultiConjuntoVertex, MultiConjuntoEdge, Integer> {

	public static MultiConjuntoVertex of(Integer index, Integer acumValue) {
		return new MultiConjuntoVertex(index, acumValue);
	}

	public static MultiConjuntoVertex initial(Integer initValue) {
		return new MultiConjuntoVertex(0, initValue);
	}

	public static MultiConjuntoVertex last() {
		return new MultiConjuntoVertex(DatosMulticonjunto.getNumElementos(), 0);
	}

	public static Predicate<MultiConjuntoVertex> goal() {
		return v -> v.index() == DatosMulticonjunto.getNumElementos();
	}

	public static Predicate<MultiConjuntoVertex> goalHasSolution() {
		return v -> v.acumValue() == 0;
	}

	public List<Integer> actions() {
		List<Integer> r;
		if (this.index() == DatosMulticonjunto.getNumElementos())
			r = new ArrayList<>();
		else if (this.index() == DatosMulticonjunto.getNumElementos() - 1) {
			if ((DatosMulticonjunto.getSuma() - this.acumValue) % DatosMulticonjunto.getElemento(this.index) == 0)
				r = List.of();
			else
				r = new ArrayList<>();
		} else {
			Integer rem = (DatosMulticonjunto.getSuma() - this.acumValue) / DatosMulticonjunto.getElemento(this.index);
			r = IntStream.range(0, rem + 1).boxed().collect(Collectors.toList());
		}
		return r;
	}

	@Override
	public MultiConjuntoVertex neighbor(Integer a) {
		MultiConjuntoVertex r;
		if (this.acumValue() == DatosMulticonjunto.getNumElementos())
			r = MultiConjuntoVertex.last();
		else
			r = MultiConjuntoVertex.of(this.index() + 1,
					this.acumValue() + a * DatosMulticonjunto.getElemento(this.index()));
		return r;
	}

	public MultiConjuntoEdge edge(Integer a) {
		return MultiConjuntoEdge.of(this, this.neighbor(a), a);
	}
}
