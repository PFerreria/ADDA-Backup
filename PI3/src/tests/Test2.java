package tests;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import exercises.Ex2;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import utils.Ride;
import utils.TripToRide;

public class Test2 {
	public static void main(String[] args) {
		for (int i : List.of(1, 2, 3)) {
			System.out.println("Using file" + i + ":\n");
			test(i);
		}
	}

	public static void test(int i) {
		Graph<Ride, TripToRide> gA = graphReader("files/ejercicio2_" + i + ".txt");
		Graph<Ride, TripToRide> gB = graphReaderB("files/ejercicio2_" + i + ".txt");
		Graph<Ride, TripToRide> gC = graphReader("files/ejercicio2_" + i + ".txt");
		String fileOutputA = "v" + i + "_ex2_apA";
		String fileOutputB = "v" + i + "_ex2_apB";
		String fileOutputC = "v" + i + "_ex2_apC";
		Ride rideO = Ride.valueOf("Coches de choque", 10, 9.5, 7);
		Ride rideT = Ride.valueOf("Raton Vacilon", 25, 7.5, 7);
		Integer hours = 5;
		if (i == 2) {
			rideT = Ride.valueOf("Patitos", 10, 5.5, 3);
			hours = 2;
		} else if (i == 3) {
			rideO = Ride.valueOf("Casa del Terror", 20, 7.0, 10);
			rideT = Ride.valueOf("Pim pam pum", 15, 6.0, 4);
			hours = 3;
		}
		testEx2a(gA, fileOutputA, rideO, rideT);
		testEx2b(gB, fileOutputB);
		testEx2c(gC, fileOutputC, hours);
	}

	public static Graph<Ride, TripToRide> graphReader(String file) {
		return GraphsReader.newGraph(file, // file
				Ride::ofFormat, // formating Ride from record
				TripToRide::ofFormat, // formating TripToRide from record
				() -> Graphs2.simpleWeightedGraph(), // empty graph that gives type
				TripToRide::distance);
	}

	public static Graph<Ride, TripToRide> graphReaderB(String file) {
		return GraphsReader.newGraph(file, // file
				Ride::ofFormat, // formating Ride from record
				TripToRide::ofFormat, // formating TripToRide from record
				() -> Graphs2.simpleWeightedGraph(), // empty graph that gives type
				TripToRide::avgTime);
	}
	
	private static void testEx2a(Graph<Ride, TripToRide> g, String file, Ride origin, Ride target) {
		GraphPath<Ride, TripToRide> shortestPath = Ex2.ex2a(g, origin, target);
		GraphColors.toDot(g, // graph
				"results/" + file + ".gv", // resulting file route
				v -> v.name(), // tag vertex
				e -> e.toString(), // tag edges
				v -> GraphColors.colorIf(Color.magenta, shortestPath.getVertexList().contains(v)),
				e -> GraphColors.colorIf(Color.magenta, shortestPath.getEdgeList().contains(e)));
		System.out.println("Origin ride: " + origin.name());
		System.out.println("Target ride: " + target.name());
		System.out.println("Shortest path: " + shortestPath.getVertexList().stream().map(Ride::name).toList());
		System.out.println("Generating " + file + " completed\n");
	}

	private static void testEx2b(Graph<Ride, TripToRide> g, String file) {
		GraphPath<Ride, TripToRide> TSP = Ex2.ex2b(g);
		GraphColors.toDot(g, // graph
				"results/" + file + ".gv", // resulting file route
				v -> v.name(), // tag vertex
				e -> e.toString(), // tag edges
				v -> GraphColors.colorIf(Color.magenta, Color.blue, TSP.getVertexList().get(0) == v),
				e -> GraphColors.colorIf(Color.blue, TSP.getEdgeList().contains(e)));
		System.out.println("Shortest path going through all rides: " + TSP.getVertexList().stream().map(Ride::name).toList());
		System.out.println("Generating " + file + " completed\n");
	}

	private static void testEx2c(Graph<Ride, TripToRide> g, String file, Integer timeForRide) {
		List<Ride> tripWTime = Ex2.ex2c(g, timeForRide);
		GraphColors.toDot(g, // graph
				"results/" + file + ".gv", // resulting file route
				v -> v.name(), // tag vertex
				e -> e.toString(), // tag edges
				v -> {
					if (tripWTime.get(0) == v) {
						return GraphColors.color(Color.magenta);
					} else {
						return GraphColors.colorIf(Color.blue, tripWTime.contains(v));
					}
				}, e -> {
					Boolean condition = false;
					for(int i=0; i<tripWTime.size()-1; i++) {
						if(g.getEdge(tripWTime.get(i), tripWTime.get(i+1)).equals(e)) {
							condition = true;
							break;
						};
					}
					return GraphColors.colorIf(Color.blue, condition);
				});
		System.out.println("Rides taken in " + timeForRide + " hours: " + tripWTime.stream().map(Ride::name).toList());
		System.out.println("Generating " + file + " completed\n");
	}

}
