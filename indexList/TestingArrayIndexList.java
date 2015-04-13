package indexList;

public class TestingArrayIndexList {
	public static void main(String[] args){
		testingArrayIndexList();
		System.out.println("_________________________");
	}
	

	private static void testingArrayIndexList() {
		System.out.println("Testing ArrayIndexList");
		ArrayIndexList<Integer> array = new ArrayIndexList<Integer>(3);
		System.out.println("isEmpty? "+ array.isEmpty() + " - size: "+array.size());
		array.add(0, 2);
		System.out.println("isEmpty? "+ array.isEmpty() + " - size: "+array.size());
		System.out.println("array: "+array.toString());
		array.add(0, 1);
		System.out.println("array: "+array.toString());
		array.add(2,3);
		System.out.println("array: "+array.toString());
		array.add(1,4);
		System.out.println("array: "+array.toString());
		System.out.println("Modifying array: second element sets to 5");
		array.set(1, 5);
		System.out.println("array: "+array.toString());
		System.out.println("Removing 5 from array");
		array.remove(1);
		System.out.println("array: "+array.toString());
		System.out.println();
		System.out.println("testing equals(must be true): "+array.equals(array));
		ArrayIndexList<Integer> a = new ArrayIndexList<Integer>(3);
		a.add(0, 2);
		a.add(1, 1);
		a.add(2, 3);
		System.out.println("testing equals(must be false): "+array.equals(a));

	}
}
