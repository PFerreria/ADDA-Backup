package tests;

import java.util.List;
import java.util.stream.Collectors;

import ejs.Ex3;
import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class Test3 {
	private static List<String> parseBTree(String file) {
		return Files2.streamFromFile(file).collect(Collectors.toList());

	}

	private static List<String> parseTree(String file) {
		return Files2.streamFromFile(file).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		List<String> lsB = parseBTree("files/PI2Ej3DatosEntradaBinary.txt");
		List<String> lsT = parseTree("files/PI2Ej3DatosEntradaNary.txt");

		System.out.println("\nBINARY: \n");
		for (String l : lsB) {
			BinaryTree<Character> tree = BinaryTree.parse(l, c -> c.charAt(0));
			System.out.println("Input tree: " + tree);
			System.out.println("Balanced?: " + Ex3.ex3_btree(tree));
		}

		System.out.println("\nNARY: \n");
		for (String l : lsT) {
			Tree<Character> tree = Tree.parse(l, c -> c.charAt(0));
			System.out.println("Input tree: " + tree);
			System.out.println("Balanced?: " + Ex3.ex3_ntree(tree));
		}
	}
}
