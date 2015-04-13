package queue;


public class TestingQueue {
	public static void main(String[] args){
		testingArrayQueue();
		System.out.println("___________________");
		testingNodeQueue();
		System.out.println("___________________");
	}

	private static void testingNodeQueue() {
		System.out.println("Testing NodeQueue");
		NodeQueue<Integer> queue = new NodeQueue<Integer>();
		operations(queue);

	}

	private static void testingArrayQueue() {
		System.out.println("Testing ArrayQueue");
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(4);
		operations(queue);
		
	}
	
	private static void operations(Queue<Integer> queue){
		System.out.println("Queue must be empty. Queue isEmpty: "+ queue.isEmpty());
		System.out.print("Enqueue 10 - ");
		queue.enqueue(10);
		System.out.println("Front: "+queue.front());
		System.out.print("Enqueue 100 - ");
		queue.enqueue(100);
		System.out.println("front:"+queue.front());
		System.out.print("Pushing 200 - ");
		queue.enqueue(200);
		System.out.println("front:"+queue.front());
		System.out.print("queue is empty?: "+queue.isEmpty());
		System.out.println("Size before removal: "+queue.size());
		System.out.println("Dequeue: "+queue.dequeue());
		System.out.println("Size after removal: : "+queue.size());
		System.out.print("Enqueue 300 - ");
		queue.enqueue(300);
		System.out.println("Front: "+queue.front());
		System.out.print("Enqueue 1000 - ");
		queue.enqueue(1000);
		System.out.println("Front: "+queue.front());
		System.out.print("Enqueue 10000 - ");
		queue.enqueue(10000);
		System.out.println("Front: "+queue.front());
		System.out.print("Enqueue 100000 - ");
		queue.enqueue(100000);
		System.out.println("Front: "+queue.front());
		System.out.println("Size: "+queue.size());
		System.out.println("toString: "+queue.toString());
		
		Queue<Integer> s = queue;
		System.out.println("equals(must be true): "+queue.equals(s));
		ArrayQueue<Integer> ns = new ArrayQueue<Integer>(6);
		ns.enqueue(10);
		ns.enqueue(100);
		ns.enqueue(300);
		ns.enqueue(1000);
		ns.enqueue(10000);
		ns.enqueue(100000);
		System.out.println("equals: "+queue.equals(ns));
		queue = null;
	}
}
