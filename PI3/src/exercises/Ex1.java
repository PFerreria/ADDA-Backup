package exercises;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;

import us.lsi.graphs.views.SubGraphView;
import utils.Relation;
import utils.User;

public class Ex1 {
	public static Graph<User, Relation> ex1a(Graph<User, Relation> g, Predicate<User> pv, Predicate<Relation> pa) {
		Graph<User, Relation> view = SubGraphView.of(g, pv, pa);
		return view;
	}

	public static List<Set<User>> ex1b(Graph<User, Relation> g) {
		// initialise algorithm on the graph
		var alg = new ConnectivityInspector<User, Relation>(g);
		// what is asked
		return alg.connectedSets();
	}

	public static Set<User> ex1c(Graph<User, Relation> g) {
		var alg = new GreedyVCImpl<>(Graphs.undirectedGraph(g));
		Set<User> vCover = alg.getVertexCover();
		return vCover;
	}

	public static Set<User> ex1d(Graph<User, Relation> g) {
		Comparator<User> avgIntInd = Comparator.comparing(
				v -> g.incomingEdgesOf(v).stream().mapToDouble(o -> o.interactIndex()).average().getAsDouble());
		return g.vertexSet().stream()
				.filter(v -> (g.incomingEdgesOf(v).size() >= 5 && v.hobbies().size() > 3 && v.actIndex() > 4))
				.sorted(avgIntInd.reversed()).limit(2).collect(Collectors.toSet());
	}
}
