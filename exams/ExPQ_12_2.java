package exams;

public class ExPQ_12_2 {

	public static void main(String[] args){
		BSTPriorityQueue<Integer, String> pq = new BSTPriorityQueue<Integer, String>();
		pq.insert(0, "cacca");
		pq.insert(2, "pupù");
		pq.insert(1, "caccapupù");
		pq.insert(3, "perdindirindina");
		System.out.println("Size - "+pq.size());
		System.out.println("Minimum - "+pq.min());
		System.out.println("Minimum removed- "+pq.removeMin());
		System.out.println("Minimum - "+pq.min());
		System.out.println("Size - "+pq.size());
		System.out.println("ToString - "+pq.toString());
	}
}
