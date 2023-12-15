public class LinkedStack<T> implements StackADT<T> {
	
	private int count;			// # of nodes
	private LinearNode<T> top;  // top of stack (head/front of list)
	
	public LinkedStack() {
		count = 0;
		top = null;
	}

	public void push(T element) {
		LinearNode<T> node = new LinearNode<T>(element);
		node.setNext(top);
		top = node;
		count++;
	}

	public T pop() {
		if(isEmpty()) {
			throw new EmptyCollectionException("stack");
		}
		T element = top.getElement();
		top = top.getNext();
		count--;		
		return element;
	}

	public T peek() {
		return top.getElement();
	}

	public boolean isEmpty() {
		return (count == 0);
	}

	public int size() {
		return count;
	}	
	
	public String toString() {
		String result = "\n|   |\n";
		LinearNode<T> curr = top;
		while(curr != null) {
			if(curr.getElement() != null) {
				result += "| "+ curr.getElement() + " |\n";	
			}
			curr = curr.getNext();
		}	
		result += "|___|";
		return result;
	}
	
	public void print() { // print toString
		System.out.println(toString());
	}
	
}
