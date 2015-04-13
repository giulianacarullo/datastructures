package priorityQueue;

import entry.Entry;

public class TestingPriorityQueue {
	public static void main(String[] args){
		
		testingSortedListPriorityQueue();
		System.out.println("_______________________");
		testingUnsortedListPriorityQueue();
		System.out.println("_______________________");
		testingHeapAdaptablePriorityQueue();
		System.out.println("_______________________");
		testingHeapPriorityQueue();
		System.out.println("_______________________");
	}

	private static void testingSortedListPriorityQueue() {
		System.out.println("Testing SortedListPriorityQueue");
		SortedListPriorityQueue<Integer, String> pq = new SortedListPriorityQueue<Integer, String>();
		operations(pq);
		while(!pq.isEmpty())
			System.out.println("Min - "+pq.removeMin());
		
		
	}

	private static void testingUnsortedListPriorityQueue() {
		System.out.println("Testing UnsortedListPriorityQueue");
		UnsortedListPriorityQueue<Integer, String> pq = new UnsortedListPriorityQueue<Integer, String>();
		operations(pq);
		while(!pq.isEmpty())
			System.out.println("Min - "+pq.removeMin());
		
	}

	private static void testingHeapAdaptablePriorityQueue() {
		System.out.println("Testing HeapAdaptablePriorityQueue");
		HeapAdaptablePriorityQueue<Integer, String> pq = new HeapAdaptablePriorityQueue<Integer, String>();
		operations(pq);
		//Testing heapAdaptablePQ methods
		Entry<Integer, String> min = pq.min();
		System.out.println("Min - "+min);
		pq.replaceValue(min, "uhm");
		System.out.println("New min - "+pq.min());
		pq.replaceKey(min, 5);
		System.out.println("toString - "+pq.toString());
		System.out.println("Removing with remove "+pq.remove(min));
		
		while(!pq.isEmpty())
			System.out.println("Min - "+pq.removeMin());


		
	}

	private static void testingHeapPriorityQueue() {
		System.out.println("Testing HeapPriorityQueue");
		HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<Integer, String>();
		operations(pq);
		while(!pq.isEmpty())
			System.out.println("min - "+pq.removeMin());
	}

	
	
	private static void operations(PriorityQueue<Integer, String> pq){
		pq.insert(0, "cacca");
		pq.insert(2, "pupù");
		pq.insert(1, "caccapupù");
		pq.insert(3, "perdindirindina");
		System.out.println("Size - "+pq.size());
		System.out.println("Minimum removed- "+pq.removeMin());
		System.out.println("Minimum - "+pq.min());
		System.out.println("Size - "+pq.size());
		System.out.println("ToString - "+pq.toString());


		
	}

}
