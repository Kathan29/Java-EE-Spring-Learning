package collections;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueDemo {
	public static void main(String[] args) {
		dequeTest();
	}

	private static void dequeTest() {
		
		Deque<String> deque = new ArrayDeque<String>(); // here we can also write new LinkedList<>()
		
		//Implementing it as Queue
		deque.add("harry potter");
		deque.add("Java");
		deque.add("nit iit");
		
		//Printing the queue
		System.out.println("Printing Queue : FIFO");
		System.out.println(deque.remove()); // or we can also use removeFirst()
		System.out.println(deque.remove());
		System.out.println(deque.remove());
		
		//Implementing it as Stack
		deque.push("harry potter");
		deque.push("Java");
		deque.push("nit iit");
		
		//Printing the stack
		System.out.println("\nPrinting Stack : LIFO");
		System.out.println(deque.pop());
		System.out.println(deque.pop());
		System.out.println(deque.pop());		
		
	}
}
