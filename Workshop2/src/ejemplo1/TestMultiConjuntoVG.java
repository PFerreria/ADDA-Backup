package ejemplo1;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;

import org.jgrapht.GraphPath;
import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.alg.monedas.DatosMonedas;
import us.lsi.alg.monedas.MonedaEdge;
import us.lsi.alg.monedas.MultiConjuntoVertex;
import us.lsi.alg.monedas.MonedasHeuristica;
import us.lsi.alg.monedas.SolucionMonedas;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.alg.BT;
import us.lsi.graphs.alg.GreedyOnGraph;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.EGraph.Type;
import us.lsi.path.EGraphPath.PathType;

public class TestMultiConjuntoVG {
	public static void main(String[] args) {
		Locale.setDefault(Locale.of("en", "US"));
		DatosMonedas.datosIniciales("ficheros/monedas/monedas2.txt");
		Integer valorInicial = 400;

		MultiConjuntoVertex e1 = MultiConjuntoVertex.first(valorInicial);
		
		EGraph<MultiConjuntoVertex, MultiConjuntoEdge> graph = EGraph.virtual(e1,MultiConjuntoVertex.goal(),PathType.Sum,Type.Max)
				.goalHasSolution(MultiConjuntoVertex.goalHasSolution())
				.greedyEdge(MultiConjuntoVertex::aristaVoraz)
				.heuristic(MonedasHeuristica::heuristic)
				.build();

		GreedyOnGraph<MultiConjuntoVertex, MultiConjuntoEdge> rr = GreedyOnGraph.of(graph);

		GraphPath<MultiConjuntoVertex, MultiConjuntoEdge> path1 = rr.path();

		BT<MultiConjuntoVertex, MultiConjuntoEdge, SolucionMonedas> ms1;

		if (rr.isSolution(path1)) {
			System.out.println("Hay solucion voraz 1"+path1.getWeight());
			ms1 = BT.of(graph,SolucionMonedas::of,path1.getWeight(),path1,true);
		} else {
			ms1 = BT.of(graph,SolucionMonedas::of,null,null,true);
		}
		
		ms1.search();

		Optional<GraphPath<MultiConjuntoVertex, MonedaEdge>> ps = ms1.optimalPath();

		if (ps.isPresent()) {
			SolucionMonedas s = SolucionMonedas.of(ps.get());
			System.out.println("2 = " + s.toString());
			SimpleDirectedGraph<MultiConjuntoVertex, MonedaEdge> g = ms1.outGraph();
			GraphColors.toDot(g, "ficheros/MonedasBTGraph1.gv",
					v -> String.format("(%d,%d)", v.index(), v.valorRestante()), e -> e.action().toString(),
					v -> GraphColors.colorIf(Color.red, MultiConjuntoVertex.goal().test(v)),
					e -> GraphColors.color(Color.black));
		} else
			System.out.println("2 = No hay solucion");

		Collections.sort(DatosMonedas.monedas, Comparator.comparing(m -> m.pesoUnitario()));

		MultiConjuntoVertex e3 = MultiConjuntoVertex.first(valorInicial);

		graph = EGraph.virtual(e3,MultiConjuntoVertex.goal(),PathType.Sum,Type.Min)
				.goalHasSolution(MultiConjuntoVertex.goalHasSolution())
			//	.greedyEdge(MultiConjuntoVertex::aristaVoraz)
			//	.heuristic(MultiConjuntoVertex::heuristic)
				.build();

		rr = GreedyOnGraph.of(graph, MultiConjuntoVertex::aristaVoraz);

		GraphPath<MultiConjuntoVertex, MultiConjuntoEdge> path2 = rr.path();

		if (rr.isSolution(path1)) {
			System.out.println("Hay solucion voraz 1"+path1.getWeight());
			ms1 = BT.of(graph,SolucionMulticonjunto::of,path2.getWeight(),path2,true);
		} else {
			ms1 = BT.of(graph,SolucionMulticonjunto::of,null,null,true);
		}
		
		Optional<GraphPath<MultiConjuntoVertex, MultiConjuntoEdge>> gp = ms1.search();

		if (gp.isPresent()) {
			System.out.println("4 = " + SolucionMonedas.of(gp.get()).toString());
		} else
			System.out.println("4 = No hay solucion");

	}
}
