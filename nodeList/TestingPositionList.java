package nodeList;

public class TestingPositionList {
	public static void main(String[] args){
		testingNodePositionList();
		System.out.println("___________________");
	}

	private static void testingNodePositionList() {
		System.out.println("Testing NodePositionList");
		NodePositionList<Integer> positionList = new NodePositionList<Integer>();
		positionList.addFirst(1);
		System.out.println(positionList.toString());
		positionList.addLast(4);
		System.out.println(positionList.toString());
		positionList.addAfter(positionList.first(), 2);
		System.out.println(positionList.toString());
		positionList.addBefore(positionList.last(), 3);
		System.out.println(positionList.toString());
		System.out.println("First: "+ positionList.first().element());
		System.out.println("Last: "+positionList.last().element());
		System.out.print("Removing last element: ");
		positionList.remove(positionList.last());
		System.out.println(positionList.toString());
		System.out.println("Printing second element: ");
		System.out.println(positionList.next(positionList.first()).element());
	}

}
