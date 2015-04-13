package tree;

import position.Position;

public class TestingTree {

	public static void main(String[] args){
		testingLinkedTree();
		System.out.println("___________________");

	}

	private static void testingLinkedTree() {
		LinkedTree<Integer> tree = new LinkedTree<Integer>();
		System.out.println("Tree must be empty - isEmpty?: " +tree.isEmpty());
		Position<Integer> root= tree.addRoot(1);
		tree.addChild(2, root);
		Position<Integer> child = tree.addChild(3, root);
		Position<Integer> ch = tree.addChild(4, child);
		tree.addChild(5, child);
		tree.addChild(6, child);
		System.out.println("Size: "+tree.size());
		System.out.println(tree.toString());
		System.out.println("Root is external?(false) - " +tree.isExternal(root));
		System.out.println("Leaf is external?(true) - " +tree.isExternal(ch));
		System.out.println("Child root: "+tree.children(root));
		System.out.println("Equals?(must be true) - "+tree.equals(tree));
		LinkedTree<Integer> t = new LinkedTree<Integer>();
		Position<Integer> r= t.addRoot(1);
		t.addChild(2, r);
		Position<Integer> c = t.addChild(3, r);
		t.addChild(4, c);
		t.addChild(5, c);
		t.addChild(6, c);
		System.out.println("Equals?(must be true) - "+tree.equals(t));
		System.out.println("Second tree before mod."+t.toString());
		t.replace(c, 4);
		System.out.println("Equals?(must be false) - "+tree.equals(t));
		System.out.println("Second tree after mod."+t.toString());
		System.out.println("Removed first child: "+tree.removeExternalChild(c));
		System.out.println("Removed second child: "+tree.removeExternalChild(c));
				
	}
	
}
