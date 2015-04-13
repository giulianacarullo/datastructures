package partition;

import set.OrderedListSet;
import set.Set;

public class TestingPartition {
	public static void main(String[] args){
		ListPartition<Integer> partition = new ListPartition<Integer>();
		OrderedListSet<Integer> set = (OrderedListSet<Integer>) partition.makeSet(2);
		OrderedListSet<Integer> set1 = (OrderedListSet<Integer>) partition.makeSet(4);
		System.out.println(partition);
		OrderedListSet<Integer> find = (OrderedListSet<Integer>) partition.find(2);
		System.out.println("Find result - "+find);
		
		Set<Integer> setU = partition.union(set, set1);
		System.out.println("Union result - "+setU);
		System.out.println(partition);
		System.out.println(partition.find(2));
		System.out.println(partition.size());
	}
}
