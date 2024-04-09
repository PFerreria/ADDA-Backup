package exercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.traverse.TopologicalOrderIterator;

import us.lsi.common.Pair;
import utils.Task;
import utils.TaskRelation;

public class Ex3 {

	public static List<Task> ex3a(Graph<Task, TaskRelation> g) {
		var<Task, TaskRelation> alg = new TopologicalOrderIterator<Task, TaskRelation>(g);
		List<Task> tasks = new ArrayList<>();
		alg.forEachRemaining(v -> tasks.add(v));
		return tasks;
	}

	public static List<Task> ex3b(Graph<Task, TaskRelation> g, Task task) {
		return previousTasks(g, task, new ArrayList<>());
	}

	private static List<Task> previousTasks(Graph<Task, TaskRelation> g, Task firstTask, List<Task> prevTasks) {
		List<Task> prev = Graphs.predecessorListOf(g, firstTask);
		for (Task task : prev) {
			if (!prevTasks.contains(task)) {
				previousTasks(g, task, prevTasks);
				prevTasks.add(task);
			}
		}
		return prevTasks;

	}

	public static List<Task> ex3c(Graph<Task, TaskRelation> g) {
		List<Pair<Task, Integer>> taskPlusNDepTasksLs = new ArrayList<>();
		for (Task t : g.vertexSet()) {
			Pair<Task, Integer> taskPlusNDepTasks = Pair.of(t, nDependentTasks(g, t, new HashSet<>()));
			taskPlusNDepTasksLs.add(taskPlusNDepTasks);
		}
		Integer maxDepTask = taskPlusNDepTasksLs.stream().max(Comparator.comparing(o -> o.second())).get().second();
		return taskPlusNDepTasksLs.stream().filter(o -> o.second() == maxDepTask).map(o -> o.first()).toList();
	}

	private static Integer nDependentTasks(Graph<Task, TaskRelation> g, Task task, Set<Task> taskSet) {
		if (!g.outgoingEdgesOf(task).isEmpty()) {
			for (TaskRelation t : g.outgoingEdgesOf(task)) {
				taskSet.add(g.getEdgeTarget(t));
				nDependentTasks(g, g.getEdgeTarget(t), taskSet);
			}
		}
		return taskSet.size();
	}
}
