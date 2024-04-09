package exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;

import utils.Ride;
import utils.TripToRide;

public class Ex2 {
	public static GraphPath<Ride, TripToRide> ex2a(Graph<Ride, TripToRide> g, Ride origin, Ride target) {
		// initialise algorithm on the graph
		var<Ride, TripToRide> alg = new DijkstraShortestPath<Ride, TripToRide>(g);
		// what is asked
		return alg.getPath(origin, target);
	}

	public static GraphPath<Ride, TripToRide> ex2b(Graph<Ride, TripToRide> g) {
		return new HeldKarpTSP<Ride, TripToRide>().getTour(g);
	}

	public static List<Ride> ex2c(Graph<Ride, TripToRide> g, Integer timeForRide) {
		Ride mostPopRide = g.vertexSet().stream().max(Comparator.comparing(v -> v.popularity())).get();
		Double timeLeft = timeForRide - (mostPopRide.duration() + mostPopRide.waitTime()) / 60.;
		return ex2cAux(g, timeLeft, mostPopRide, new ArrayList<>());
	}

	private static List<Ride> ex2cAux(Graph<Ride, TripToRide> g, Double timeLeft, Ride mostPopRide,
			List<Ride> visited) {
		if (timeLeft <= 0 || mostPopRide == null) {
			return visited;
		} else {
			visited.add(mostPopRide);
			Ride mostPopNeigh = getMostPopNeigh(g, mostPopRide, visited);
			Double t = g.getEdge(mostPopRide, mostPopNeigh) != null
					? (mostPopNeigh.duration() + mostPopNeigh.waitTime()
							+ g.getEdge(mostPopRide, mostPopNeigh).avgTime()) / 60.
					: 0.;
			if (timeLeft - t >= 0) {
				timeLeft -= t;
				return ex2cAux(g, timeLeft, mostPopNeigh, visited);
			} else {
				return visited;
			}
		}
	}

	private static Ride getMostPopNeigh(Graph<Ride, TripToRide> g, Ride ride, List<Ride> visited) {
		Set<Ride> s = new HashSet<>();
		for (TripToRide rel : g.edgesOf(ride)) {
			s.add(g.getEdgeSource(rel));
			s.add(g.getEdgeTarget(rel));
		}
		s.removeAll(visited);
		return s.stream().max(Comparator.comparing(r -> r.popularity())).orElse(null);
	}
}
