package binaryTree;

import position.Position;

public class TestingBinaryTree {
	public static void main(String[] args){
		testingLinkedBinaryTree();
		System.out.println("______________________");
		testingArrayListCompleteBinaryTree();
		System.out.println("______________________");
	}

	private static void testingLinkedBinaryTree() {
		System.out.println("Testing LinkedBinaryTree");
		LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<Integer>();
		tree.addRoot(1);
		Position<Integer> x = tree.insertLeft(tree.root(), 2);
		tree.insertRight(tree.root(), 3);
		System.out.println("parent"+tree.parent(x));
		int i = 4;
		System.out.println(tree.toString());
		for(Position<Integer> pos :tree.children(tree.root())){
				tree.insertLeft(pos,i);
				i++;
				tree.insertRight(pos,i);
				i++;
		}
		System.out.println(tree.toString());
		for(Position<Integer> pos :tree.positions()) {
			System.out.print(pos.element());
			System.out.print(" Internal? "+tree.isInternal(pos) +" - ");
			System.out.println("External? "+tree.isExternal(pos));
		}
		System.out.println("Size: " +tree.size());
		
	}

	private static void testingArrayListCompleteBinaryTree() {
		System.out.println("Testing ArrayListCompleteBinaryTree");
		ArrayListCompleteBinaryTree<Integer> tree = new ArrayListCompleteBinaryTree<Integer>();
		
		for(int i = 0; i<6; i++){
			tree.add(i);
		}
		System.out.println("Preorder - "+tree.toString());

		for(Position<Integer> pos :tree.positions()) {
			System.out.print(pos.element());
			System.out.print(" Internal? "+tree.isInternal(pos)+" - ");
			System.out.println("External? "+tree.isExternal(pos));
		}
		System.out.println("Size: " +tree.size());
		
		System.out.println("Left child of the root: "+ tree.left(tree.root()));
		System.out.println(tree.right(tree.left(tree.root())));
		System.out.println("Root's children - "+tree.children(tree.root()));
		
	}

}
