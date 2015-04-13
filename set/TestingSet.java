package set;

public class TestingSet {

	public static void main(String[] args){
		System.out.println("Testing OrderedListSet");
		OrderedListSet<Integer> set = new OrderedListSet<Integer>();
		set.fastInsert(1);
		set.fastInsert(2);
		set.fastInsert(4);
		System.out.println("Set - "+set);
		set.remove();
		System.out.println("Set - "+set);
		set.fastInsert(6);
		set.fastInsert(7);
		System.out.println("Set - "+set);
		System.out.println("Set size - "+ set.size());
		
		OrderedListSet<Integer> set2 = new OrderedListSet<Integer>();
		set2.fastInsert(2);
		set2.fastInsert(4);
		set2.fastInsert(6);
		set2.fastInsert(7);
		System.out.println("Operations between "+set + "and"+set2);
		Set<Integer> x = set.intersect(set2);
		System.out.println("Intersection - "+x);
		Set<Integer> y = set.union(set2);
		System.out.println("Union - "+y);
		Set<Integer> z = set.subtract(set2);
		System.out.println("Subtract - "+z);
		
		
		set.fastInsert(2);
		set.fastInsert(4);
		set.fastInsert(6);
		set.fastInsert(7);
		OrderedListSet<Integer> set3 = new OrderedListSet<Integer>();
		set3.fastInsert(2);
		set3.fastInsert(5);
		set3.fastInsert(8);
		System.out.println("Operations between "+set + "and"+set3);
		Set<Integer> xx = set.intersect(set3);
		System.out.println("Intersection - "+xx);
		set.fastInsert(4);
		set.fastInsert(6);
		set.fastInsert(7);
		Set<Integer> yy = set.union(set3);
		System.out.println("Union - "+yy);
		set = new OrderedListSet<Integer>();
		set.fastInsert(2);
		set.fastInsert(4);
		set.fastInsert(6);
		set.fastInsert(7);
		Set<Integer> zz = set.subtract(set3);
		System.out.println("Subtract - "+zz);
	}
	
}
