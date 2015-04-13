package stack;

public class TestingStack {
	public static void main(String[] args){
		testingArrayStack();
		System.out.println("___________________________");
		testingNodeStack();
		System.out.println("___________________________");
	}


	private static void testingArrayStack() {
		System.out.println("Testing ArrayStack");
		ArrayStack<Integer> stack = new ArrayStack<Integer>(6);
		operations(stack);
	}


	private static void testingNodeStack() {
		System.out.println("Testing NodeStack");
		NodeStack<Integer> stack = new NodeStack<Integer>();
		operations(stack);
		
	}
	private static void operations(Stack<Integer> stack){
		System.out.println("stack must be empty. Stack isEmpty: "+ stack.isEmpty());
		System.out.print("Pushing 10 - ");
		stack.push(10);
		System.out.println("Top: "+stack.top());
		System.out.print("Pushing 100 - ");
		stack.push(100);
		System.out.println("Top:"+stack.top());
		System.out.print("Pushing 200 - ");
		stack.push(200);
		System.out.println("Top:"+stack.top());
		System.out.print("stack is empty?: "+stack.isEmpty());
		System.out.println("Size before removal: "+stack.size());
		System.out.println("Pop: "+stack.pop());
		System.out.println("Size after removal: : "+stack.size());
		System.out.print("Pushing 300 - ");
		stack.push(300);
		System.out.println("Top: "+stack.top());
		System.out.print("Pushing 1000 - ");
		stack.push(1000);
		System.out.println("Top: "+stack.top());
		System.out.print("Pushing 10000 - ");
		stack.push(10000);
		System.out.println("Top: "+stack.top());
		System.out.print("Pushing 100000 - ");
		stack.push(100000);
		System.out.println("Top: "+stack.top());
		System.out.println("Size: "+stack.size());
		System.out.println("toString: "+stack.toString());
		
		Stack<Integer> s = stack;
		System.out.println("equals(must be true): "+stack.equals(s));
		ArrayStack<Integer> ns = new ArrayStack<Integer>(6);
		ns.push(10);
		ns.push(100);
		ns.push(300);
		ns.push(1000);
		ns.push(10000);
		ns.push(100000);
		System.out.println("equals: "+stack.equals(ns));
		stack = null;
	}

}
