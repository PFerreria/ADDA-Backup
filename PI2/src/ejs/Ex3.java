package ejs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ex3 {
	public static Boolean ex3_btree(BinaryTree<Character> tree) {
		return ex3_aux_btree(tree).first();
	}

	public static Pair<Boolean, Integer> ex3_aux_btree(BinaryTree<Character> tree) {
		return switch (tree) {
		case BEmpty<Character> t -> Pair.of(true, 0);
		case BLeaf<Character> t -> Pair.of(true, 0);
		case BTree<Character> t -> {
			Pair<Boolean, Integer> leftNode = ex3_aux_btree(t.left());
			Pair<Boolean, Integer> rightNode = ex3_aux_btree(t.right());
			yield Pair.of(
					leftNode.first() && rightNode.first() && (Math.abs(leftNode.second() - rightNode.second()) <= 1),
					Math.max(leftNode.second(), rightNode.second()) + 1);
		}
		};
	}

	public static Boolean ex3_ntree(Tree<Character> tree) {
		return ex3_aux_ntree(tree).first();
	}

	public static Pair<Boolean, Integer> ex3_aux_ntree(Tree<Character> tree) {
		return switch (tree) {
		case TEmpty<Character> t -> Pair.of(true, 0);
		case TLeaf<Character> t -> Pair.of(true, 0);
		case TNary<Character> t -> {
			boolean res = true;
			List<Integer> hLs = new ArrayList<Integer>();
			for (Tree<Character> child : t.children()) {
				Pair<Boolean, Integer> node = ex3_aux_ntree(child);
				res = res && node.first();
				hLs.add(node.second());
			}
			yield Pair.of(res && (Math.abs(Collections.max(hLs) - Collections.min(hLs)) <= 1),
					Collections.max(hLs) + 1);
		}
		};
	}
}
