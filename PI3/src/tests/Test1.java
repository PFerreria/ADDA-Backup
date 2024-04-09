package tests;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;

import exercises.Ex1;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import utils.Relation;
import utils.User;

public class Test1 {
	public static void main(String[] args) {
		for (int i : List.of(1, 2, 3)) {
			System.out.println("Using file" + i + ":\n");
			test(i);
		}
	}

	public static void test(int i) {
		Graph<User, Relation> g = graphReader("files/ejercicio1_" + i + ".txt");
		String fileOutputA = "v" + i + "_ex1_apA";
		String fileOutputB = "v" + i + "_ex1_apB";
		String fileOutputC = "v" + i + "_ex1_apC";
		String fileOutputD = "v" + i + "_ex1_apD";
		testEx1a(g, fileOutputA);
		testEx1b(g, fileOutputB);
		testEx1c(g, fileOutputC);
		testEx1d(g, fileOutputD);
	}

	public static Graph<User, Relation> graphReader(String file) {
		return GraphsReader.newGraph(file, // file
				User::ofFormat, // formating user from record
				Relation::ofFormat, // formating relation from record
				() -> Graphs2.simpleDirectedGraph()); // empty graph that gives type
	}

	private static void testEx1a(Graph<User, Relation> g, String file) {
		Predicate<User> pv = v -> g.outgoingEdgesOf(v).size() > 3
				&& g.outgoingEdgesOf(v).stream().mapToDouble(a -> a.interactIndex()).average().getAsDouble() > 2.5;
		Predicate<Relation> pe = e -> true;
		Graph<User, Relation> view = Ex1.ex1a(g, pv, pe);
		GraphColors.toDot(g, // graph
				"results/" + file + ".gv", // resulting file route
				v -> v.name(), // tag vertex
				e -> e.interactIndex().toString(), // tag edges
				v -> GraphColors.colorIf(Color.blue, view.containsVertex(v)),
				e -> GraphColors.colorIf(Color.blue, view.containsEdge(e)));
		System.out.println("Users following more than 3 users and with a interaction index bigger than 2.5:"
				+ view.vertexSet().stream().map(User::name).toList());
		System.out.println("Generating " + file + " completed\n");
	}

	private static void testEx1b(Graph<User, Relation> g, String file) {
		List<Set<User>> userGroups = Ex1.ex1b(g);
		System.out.println("There are " + userGroups.size() + " connected groups: ");
		for (Set<User> s : userGroups) {
			System.out.println("Connected component " + (userGroups.indexOf(s) + 1) + ": " + s);
		}
		GraphColors.toDot(g, // graph
				"results/" + file + ".gv", // resulting file route
				v -> v.name(), // tag vertex
				e -> e.interactIndex().toString(), // tag edges
				v -> GraphColors.color(assignColor(v, userGroups)),
				e -> GraphColors.color(assignColor(g.getEdgeTarget(e), userGroups)));
		System.out.println("Generating " + file + " completed\n");

	}

	private static Integer assignColor(User v, List<Set<User>> userGroups) {
		Integer index = null;
		for (int i = 0; i < userGroups.size(); i++) {
			if (userGroups.get(i).contains(v)) {
				index = i;
				break;
			}
		}
		return index;
	}

	private static void testEx1c(Graph<User, Relation> g, String file) {
		Set<User> asked = Ex1.ex1c(g);
		GraphColors.toDot(g, // graph
				"results/" + file + ".gv", // resulting file route
				v -> v.name(), // tag vertex
				e -> e.interactIndex().toString(), // tag edges
				v -> GraphColors.colorIf(Color.red, asked.contains(v)), e -> GraphColors.color(Color.blank));
		System.out.println("Selected users: " + asked.stream().map(User::name).toList());
		System.out.println("Generating " + file + " completed\n");
	}

	private static void testEx1d(Graph<User, Relation> g, String file) {
		Set<User> highIntAvg = Ex1.ex1d(g);
		GraphColors.toDot(g, // graph
				"results/" + file + ".gv", // resulting file route
				v -> v.name(), // tag vertex
				e -> e.interactIndex().toString(), // tag edges
				v -> GraphColors.colorIf(Color.red, highIntAvg.contains(v)), e -> GraphColors.color(Color.blank));
		System.out.println("Users highest interaction: " + highIntAvg.stream().map(User::name).toList());
		System.out.println("Generating " + file + " completed\n");
	}
}
