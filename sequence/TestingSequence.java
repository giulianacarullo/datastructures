package sequence;

import indexList.ArrayIndexList;

public class TestingSequence {
	public static void main(String[] args){
		testingArraySequence();
		System.out.println("________________________");
		testingNodeSequence();
	}
	
	
	private static void testingArraySequence() {
		System.out.println("Testing ArraySequence");
		ArraySequence<Integer> sequence = new ArraySequence<Integer>(10);
		operations(sequence);
		sequence = null;
		sequence = new ArraySequence<Integer>(10);
		System.out.println("________________________");
		//Testing indexList methods
		testingIndexListMethods(sequence);
		System.out.println("________________________");
		//Testing positionList methods
		sequence = null;
		sequence = new ArraySequence<Integer>(10);
		testingPositionListMethods(sequence);
		System.out.println("________________________");
		sequence = null;
		sequence = new ArraySequence<Integer>(10);
		testingDequeMethods(sequence);
	}


	private static void testingNodeSequence() {
		System.out.println("Testing NodeSequence");
		NodeSequence<Integer> sequence = new NodeSequence<Integer>();
		operations(sequence);
		sequence = null;
		sequence = new NodeSequence<Integer>();
		System.out.println("________________________");
		//Testing indexList methods
		testingIndexListMethods(sequence);
		System.out.println("________________________");
		//Testing positionList methods
		sequence = null;
		sequence = new NodeSequence<Integer>();
		testingPositionListMethods(sequence);
		System.out.println("________________________");
		//Testing deque methods
		sequence = null;
		sequence = new NodeSequence<Integer>();
		testingDequeMethods(sequence);
		
	}
	
	
	private static void operations(Sequence<Integer> sequence){
		sequence.addFirst(1);
		sequence.addLast(2);
		sequence.addLast(3);
		sequence.addLast(4);
		System.out.println(sequence.toString());
		System.out.println("Removing second element: ");
		sequence.remove(1);
		System.out.println(sequence.toString());
		System.out.println("Removing last");
		sequence.removeLast();
		System.out.println(sequence.toString());
		sequence.add(1,2);
		System.out.println(sequence.toString());
		sequence.add(2,3);
		System.out.println(sequence.toString());
		sequence.set(0, 10);
		System.out.println(sequence.toString());
		System.out.print(sequence.indexOf(sequence.next(sequence.first())));
		
	}


	private static void testingIndexListMethods(Sequence<Integer> sequence) {
			System.out.println("Testing IndexList Methods");
			System.out.println("isEmpty? "+ sequence.isEmpty() + " - size: "+sequence.size());
			sequence.add(0, 2);
			System.out.println("isEmpty? "+ sequence.isEmpty() + " - size: "+sequence.size());
			System.out.println("array: "+sequence.toString());
			sequence.add(0, 1);
			System.out.println("array: "+sequence.toString());
			sequence.add(2,3);
			System.out.println("array: "+sequence.toString());
			sequence.add(1,4);
			System.out.println("array: "+sequence.toString());
			System.out.println("Modifying array: second element sets to 5");
			sequence.set(1, 5);
			System.out.println("array: "+sequence.toString());
			System.out.println("Removing 5 from array");
			sequence.remove(1);
			System.out.println("array: "+sequence.toString());
			System.out.println();
			System.out.println("testing equals(must be true): "+sequence.equals(sequence));
			ArrayIndexList<Integer> a = new ArrayIndexList<Integer>(3);
			a.add(0, 2);
			a.add(1, 1);
			a.add(2, 3);
			System.out.println("testing equals(must be false): "+sequence.equals(a));
	}
	
	private static void testingPositionListMethods(Sequence<Integer> sequence) {
		System.out.println("Testing NodePositionList methods");
		sequence.addFirst(1);
		System.out.println(sequence.toString());
		sequence.addLast(4);
		System.out.println(sequence.toString());
		sequence.addAfter(sequence.first(), 2);
		System.out.println(sequence.toString());
		System.out.println("Last: "+sequence.last());
		sequence.addBefore(sequence.last(), 3);
		System.out.println(sequence.toString());
		System.out.println("First: "+ sequence.first().element());
		System.out.println("Last: "+sequence.last().element());
		System.out.print("Removing last element: ");
		sequence.remove(sequence.last());
		System.out.println(sequence.toString());
		System.out.println("Printing second element: ");
		System.out.println(sequence.next(sequence.first()).element());		
	}
	

	private static void testingDequeMethods(Sequence<Integer> sequence) {
		System.out.println("Testing Deque Methods");
		System.out.println("size: "+sequence.size());
		System.out.println("isEmpty?(must be true)" +sequence.isEmpty());
		sequence.addFirst(1);
		sequence.addLast(2);
		System.out.println(sequence.toString());
		System.out.println("First: "+sequence.getFirst());
		System.out.println("Last: "+sequence.getLast());
		System.out.println("Removing first "+sequence.removeFirst());
		System.out.println(sequence.toString());

		sequence.addFirst(1);
		sequence.addLast(3);
		System.out.println(sequence.toString());
		System.out.println("Removing last "+sequence.removeLast());
		System.out.println("Removing last "+sequence.removeLast());
		System.out.println(sequence.toString());
		System.out.println("size: "+sequence.size());
	}
}
