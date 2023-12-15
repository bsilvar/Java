import java.util.Iterator;

public class Test {

	public static void main(String[] args) {	
		ArrayStack<Integer> s1 = new ArrayStack<Integer>(5);	
		s1.push(1);
		s1.push(2);
		s1.push(3);
		s1.print();	
		s1.pop();
		s1.pop();
		s1.pop();
		s1.print();
				
		LinkedStack<Character> s2 = new LinkedStack<Character>();
		s2.push('A');
		s2.push('B');
		s2.push('C');
		s2.print();
		
		LinkedQueue<Integer> q1 = new LinkedQueue<Integer>();
		q1.enqueue(1);
		q1.enqueue(2);
		q1.enqueue(3);
		q1.print();
		q1.dequeue();
		q1.print();
		
		CircularArrayQueue<Character> q2 = new CircularArrayQueue<Character>(5);
		q2.enqueue('A');
		q2.enqueue('B');
		q2.enqueue('C');
		q2.print();
		q2.dequeue();
		q2.dequeue();
		q2.dequeue();
		q2.print();
		
		ArrayList<Integer> l1 = new ArrayList<Integer>(5);
		l1.add(2);
		l1.addAfter(3,2);
		l1.print();
		l1.addToFront(1);
		l1.addAfter(4,3);
		l1.print();
		l1.removeFirst();
		l1.removeLast();
		l1.print();
		Iterator<Integer> it = l1.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		LinkedList<Character> l2 = new LinkedList<Character>();
		l2.add('A');
		l2.addAfter('B','A');
		l2.print();
		l2.addToFront('H');
		l2.addAfter('J', l2.first());
		l2.print();		
		Iterator<Character> ite = l2.iterator();
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}

		DoubleLinkedList<Integer> l3 = new DoubleLinkedList<Integer>();
		l3.add(1);
		l3.addAfter(2,1);
		l3.addToFront(0);
		l3.print();
		l3.remove(0);
		l3.print();
		l3.removeFirst();
		l3.removeLast();
		l3.print();
	}

}
