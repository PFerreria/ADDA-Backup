package tests;

import java.util.List;
import java.util.stream.Collectors;

import ejs.Ex4;
import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class Test4 {
	private static List<BinaryTree<Integer>> parseBTree(String file) {
		return Files2.streamFromFile(file).map(line -> BinaryTree.parse(line, i -> Integer.valueOf(i)))
				.collect(Collectors.toList());

	}

	private static List<Tree<Integer>> parseTree(String file) {
		return Files2.streamFromFile(file).map(line -> Tree.parse(line, i -> Integer.valueOf(i)))
				.collect(Collectors.toList());
	}

	public static void main(String[] args) {
		List<BinaryTree<Integer>> lsB = parseBTree("files/PI2Ej4DatosEntradaBinary.txt");
		List<Tree<Integer>> lsT = parseTree("files/PI2Ej4DatosEntradaNary.txt");

		System.out.println("\nBINARY: \n");
		for (BinaryTree<Integer> tree : lsB) {
			System.out.println("Input tree: " + tree);
			System.out.println("Paths: ");
			List<List<Integer>> lsPaths = Ex4.ex4_btree(tree);
			for (List<Integer> path : lsPaths) {
				System.out.println(path.toString().replace("null", "_"));
			}
		}

		System.out.println("\nNARY: \n");
		for (Tree<Integer> tree : lsT) {
			System.out.println("Input tree: " + tree);
			System.out.println("Paths: ");
			List<List<Integer>> lsPaths = Ex4.ex4_ntree(tree);
			for (List<Integer> path : lsPaths) {
				System.out.println(path.toString().replace("null", "_"));
			}
		}
	}
}
