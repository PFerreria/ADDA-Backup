package ejs;

import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Ex4 {

	public static List<List<Integer>> ex4_btree(BinaryTree<Integer> tree) {
		return ex4_btree(tree, new ArrayList<List<Integer>>(), new ArrayList<Integer>());
	}

	public static List<List<Integer>> ex4_btree(BinaryTree<Integer> tree, List<List<Integer>> r, List<Integer> ls) {
		switch (tree) {
		case BEmpty<Integer> t -> {
			ls.add(null);
			if (isDivisible(ls)) {
				r.add(ls);
			}
		}
		case BLeaf<Integer> t -> {
			ls.add(t.label());
			if (isDivisible(ls)) {
				r.add(ls);
			}
		}
		case BTree<Integer> t -> {
			ls.add(t.label());
			ex4_btree(t.left(), r, new ArrayList<Integer>(ls));
			ex4_btree(t.right(), r, new ArrayList<Integer>(ls));
		}
		}
		;
		return r;
	}

	public static List<List<Integer>> ex4_ntree(Tree<Integer> tree) {
		return ex4_ntree(tree, new ArrayList<List<Integer>>(), new ArrayList<Integer>());
	}

	public static List<List<Integer>> ex4_ntree(Tree<Integer> tree, List<List<Integer>> r, List<Integer> ls) {
		switch (tree) {
		case TEmpty<Integer> t -> {
			ls.add(null);
			if (isDivisible(ls)) {
				r.add(ls);
			}
		}
		case TLeaf<Integer> t -> {
			ls.add(t.label());
			if (isDivisible(ls)) {
				r.add(ls);
			}
		}
		case TNary<Integer> t -> {
			ls.add(t.label());
			for (Tree<Integer> child : t.children()) {
				ex4_ntree(child, r, new ArrayList<Integer>(ls));
			}
		}
		}
		;
		return r;
	}

	public static Boolean isDivisible(List<Integer> ls) {
		Integer tot = 0;
		for (Integer i : ls) {
			if (i != null) {
				tot += i;
			}
		}
		return tot % (ls.size() - 1) == 0;
	}

}
