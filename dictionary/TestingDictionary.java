package dictionary;

import entry.Entry;

public class TestingDictionary {

	public static void main(String[] args){
		testingLogFile();
		System.out.println("_______________________");
		testingOrderedSearchTable();
		System.out.println("_______________________");
		testingLinearProbingHashTable();
		System.out.println("_______________________");
		testingChainingHashTable();
		System.out.println("_______________________");
		testingBinarySearchTree();
	}

	private static void testingBinarySearchTree() {
		System.out.println("Testing BST");
		BinarySearchTree<Integer, Integer> dic = new BinarySearchTree<Integer, Integer>();
		operations(dic);
		System.out.println("keys: "+dic.keys());
		System.out.println("values: "+dic.values());
		Entry<Integer, Integer> removed = dic.remove(dic.find(150));
		System.out.println("removed: " +removed);
		System.out.println("entries: " +dic.entries());
		Entry<Integer, Integer> removed1 = dic.remove(dic.find(100));	
		System.out.println("removed: " +removed1);
		System.out.println("entries: " +dic.entries());
		Entry<Integer, Integer> removed2 = dic.remove(dic.find(200));	
		System.out.println("removed: " +removed2);
		System.out.println("entries: " +dic.entries());
		System.out.println("isEmpty?(must be true) "+dic.isEmpty());

	}


	private static void testingChainingHashTable() {
		System.out.println("Testing Chaining Hash Table");
		ChainingHashTable<Integer, Integer> dic = new ChainingHashTable<Integer, Integer>();
		operations(dic);
		
	}

	private static void testingLinearProbingHashTable() {
		System.out.println("Testing LinearProbing Hash Table");
		LinearProbingHashTable<Integer, Integer> dic = new LinearProbingHashTable<Integer, Integer>();
		operations(dic);
		
	}

	private static void testingOrderedSearchTable() {
		System.out.println("Testing OrderedSearchTable");
		OrderedSearchTable<Integer, Integer> dic = new OrderedSearchTable<Integer, Integer>();
		operations(dic);
		
	}

	private static void testingLogFile() {
		System.out.println("Testing LogFile");
		LogFile<Integer, Integer> dic = new LogFile<Integer, Integer>();
		operations(dic);
		
	}
	
	private static void operations(Dictionary<Integer, Integer> dic) {
		
		System.out.println("IsEmpty?(true) -"+dic.isEmpty());
		for(int i=100;i<=200;i=i+50) {
		     dic.insert(i, i).getKey();
		     System.out.println(dic.find(i));
		}
		//dic.insert(100,1000);
		
		System.out.println("IsEmpty?(false) -"+dic.isEmpty()+"size - "+dic.size());
		System.out.println("toString: "+dic);
		System.out.println("all entry with 100 as key - "+dic.findAll(100));
		System.out.println("all entry with 200 as key - "+dic.findAll(200));
		System.out.println("all entry with 500 as key - "+dic.findAll(500));
		System.out.println("entries: "+dic.entries());
		

	}
}
