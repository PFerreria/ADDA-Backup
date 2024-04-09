package ejemplo1;

import us.lsi.graphs.virtual.SimpleEdgeAction;

public record MultiConjuntoEdge(MultiConjuntoVertex source, MultiConjuntoVertex target, Integer action, Double weight)
		implements SimpleEdgeAction<MultiConjuntoVertex, Integer> {

	public static MultiConjuntoEdge of(MultiConjuntoVertex c1, MultiConjuntoVertex c2, Integer action) {
		Double w = (double) (action * DatosMulticonjunto.getElemento(c1.index()));
		return new MultiConjuntoEdge(c1, c2, action, w);
	}

}