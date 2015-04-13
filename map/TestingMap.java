package map;

import java.util.Iterator;

public class TestingMap {
	public static void main(String[] args){
		testingListMap();
		System.out.println("_______________________");
		testingHashTableMap();
	}

	private static void testingHashTableMap() {
		System.out.println("Testing HashTableMap");
		HashTableMap<Integer,String> map = new HashTableMap<Integer, String>();
		operations(map);
		
	}

	private static void testingListMap() {
		System.out.println("Testing ListMap");
		ListMap<Integer, String> map = new ListMap<Integer, String>();
		operations(map);
		
	}
	
	private static void operations(Map<Integer, String> map) {
		map.put(0, "cacca");
		map.put(1, "plim plim");
		map.put(2, "caccapupù");
		System.out.println(map.get(0));
		System.out.println(map.get(1));
		System.out.println(map.get(2));
		System.out.println(map.toString());
		map.remove(1);
		System.out.println(map.toString());
		Iterator<Integer> it = map.keys().iterator();
		System.out.print("Keys: ");
		while(it.hasNext())
			System.out.print(it.next()+"  ");
		System.out.println();
		Iterator<String> itV = map.values().iterator();
		System.out.print("Values: ");
		while(itV.hasNext())
			System.out.print(itV.next()+"  ");
		System.out.println();
		
	}

}
