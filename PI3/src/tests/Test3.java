package tests;

import java.util.List;

import org.jgrapht.Graph;

import exercises.Ex3;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import utils.Task;
import utils.TaskRelation;

public class Test3 {
	public static void main(String[] args) {
		for (int i : List.of(1, 2, 3)) {
			System.out.println("Using file" + i + ":\n");
			test(i);
		}
	}

	public static void test(int i) {
		Graph<Task, TaskRelation> g = graphReader("files/ejercicio3_" + i + ".txt");
		String fileOutputB = "v" + i + "_ex3_apB";
		String fileOutputC = "v" + i + "_ex3_apC";
		Task task = Task.valueOf("Tarea8");
		;
		if (i == 1) {
			task = Task.valueOf("Tarea5");
		}
		testEx3a(g);
		testEx3b(g, fileOutputB, task);
		testEx3c(g, fileOutputC);
	}

	public static Graph<Task, TaskRelation> graphReader(String file) {
		return GraphsReader.newGraph(file, // file
				Task::ofFormat, // formating Task from record
				TaskRelation::ofFormat, // formating TaskRelation from record
				() -> Graphs2.simpleDirectedGraph()); // empty graph that gives type
	}

	private static void testEx3a(Graph<Task, TaskRelation> g) {
		System.out.println("Ordered tasks: " + Ex3.ex3a(g).stream().map(Task::name).toList() + "\n");
	}

	private static void testEx3b(Graph<Task, TaskRelation> g, String file, Task task) {
		List<Task> prevTasks = Ex3.ex3b(g, task);
		GraphColors.toDot(g, // graph
				"results/" + file + ".gv", // resulting file route
				v -> v.name(), // tag vertex
				e -> e.toString(), // tag edges
				v -> {
					if (v.equals(task)) {
						return GraphColors.color(Color.magenta);
					} else {
						return GraphColors.colorIf(Color.blue, prevTasks.contains(v));
					}
				}, e -> GraphColors.color(Color.blank));
		System.out
				.println("Tasks to be done before " + task.name() + ": " + prevTasks.stream().map(Task::name).toList());
		System.out.println("Generating " + file + " completed\n");
	}

	private static void testEx3c(Graph<Task, TaskRelation> g, String file) {
		List<Task> mostDependant = Ex3.ex3c(g);
		GraphColors.toDot(g, // graph
				"results/" + file + ".gv", // resulting file route
				v -> v.name(), // tag vertex
				e -> e.toString(), // tag edges
				v -> GraphColors.colorIf(Color.magenta, mostDependant.contains(v)),
				e -> GraphColors.color(Color.blank));
		System.out.println("Most dependant task: " + mostDependant.stream().map(Task::name).toList());
		System.out.println("Generating " + file + " completed\n");
	}
}
