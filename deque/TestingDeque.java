package deque;

public class TestingDeque {
	public static void main(String[] args){
		testingNodeDeque();
		System.out.println("___________________");

	}

	private static void testingNodeDeque() {
		System.out.println("Testing NodeQueue");
		NodeDeque<Integer> deque = new NodeDeque<Integer>();
		System.out.println("size: "+deque.size());
		System.out.println("isEmpty?(must be true)" +deque.isEmpty());
		deque.addFirst(1);
		deque.addLast(2);
		System.out.println("First: "+deque.getFirst());
		System.out.println("Last: "+deque.getLast());
		System.out.println("Removing first "+deque.removeFirst());
		deque.addFirst(1);
		deque.addLast(3);
		System.out.println("Removing last "+deque.removeLast());
		System.out.println("Removing last "+deque.removeLast());
		System.out.println("size: "+deque.size());
	}
}
