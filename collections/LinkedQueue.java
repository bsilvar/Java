public class LinkedQueue<T> implements QueueADT<T> {

	private int count; // # of elements in the queue
	private LinearNode<T> head, tail;
	
	/* To make enqueue and dequeue operations O(1),
	 * we will enqueue at the tail and dequeue at the head */
	
	public LinkedQueue() {
		count = 0;
		head = tail = null;
	}

	public void enqueue(T element) {
		LinearNode<T> node = new LinearNode<T>(element);
		if(isEmpty()) {
			head = node;
		} else {
			tail.setNext(node);
		}
		tail = node;
		count++;
	}

	public T dequeue() {
		if(isEmpty()) {
			throw new EmptyCollectionException("queue");
		}
		T element = head.getElement();
		head = head.getNext();
		count--;		
		if(isEmpty()) {
			tail = null;
		}
		return element;
	}

	public T first() {
		return head.getElement();
	}

	public boolean isEmpty() {
		return (count == 0);
	}

	public int size() {
		return count;
	}
	
	public String toString() {
		String result = "\n------------------\n";
		LinearNode<T> curr = head;
		while(curr != null) {
			if(curr.getElement() != null) {
				result += " "+ curr.getElement() + " ";	
			}
			curr = curr.getNext();
		}	
		result += "\n------------------";
		return result;
	}
	
	public void print() { // print toString
		System.out.println(toString());
	}

}
