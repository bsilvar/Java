import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements ListADT<T>, Iterable<T> {
	
	protected int count;
	protected LinearNode<T> head, tail;
	protected int modCount;
	
	public LinkedList() {
		count = 0;
		head = tail = null;
		modCount = 0;
	}
	
	public void add(T element) {
		LinearNode<T> node = new LinearNode<T>(element);
		if(size() == 0) {
			head = tail = node;
		} else {
			tail.setNext(node);
			tail = node;
		}
		count++;
		modCount++;
	}
	
	public void addToFront(T element) {
		LinearNode<T> node = new LinearNode<T>(element);
		if(size() == 0) {
			head = tail = node;
		} else {
			node.setNext(head);
			head = node;
		}
		count++;
		modCount++;
	}
	
	public void addToRear(T element) {
		add(element);
	}
	
	public void addAfter(T element, T target) { // add element after target
		if(size() == 0) {
			throw new ElementNotFoundException("LinkedList");
		}
		LinearNode<T> current = head;
		boolean found = false;
		while(current != null && !found) {
			if(current.getElement().equals(target)) {
				found = true;
			} else {
				current = current.getNext();
			}
		}
		if(!found) {
			throw new ElementNotFoundException("LinkedList");
		}
		LinearNode<T> node = new LinearNode<T>(element);
		if(current.equals(tail)) {
			tail.setNext(node);
			tail = node;
		} else {
			node.setNext(current.getNext());
			current.setNext(node);
		}
		count++;
		modCount++;		
	}
	
	public T remove(T target) {
		if(isEmpty()) {
			throw new EmptyCollectionException("LinkedList");
		}
		LinearNode<T> current = head;
		LinearNode<T> previous = null;
		boolean found = false;
		while(current != null && !found) {
			if(current.getElement().equals(target)) {
				found = true;
			} else {
				previous = current;
				current = current.getNext();
			}
		}
		if(!found) {
			throw new ElementNotFoundException("LinkedList");
		}
		if(size() == 1) {
			head = tail = null;
		} else if(current.equals(head)) {
			head = head.getNext();
		} else if(current.equals(tail)) {
			tail = previous;
			tail.setNext(null);
		} else {
			previous.setNext(current.getNext());
		}
		count--;
		modCount++;	
		return current.getElement();
	}	
	
	public T removeFirst() {
		if(isEmpty()) {
			throw new EmptyCollectionException("LinkedList");
		}
		return remove(first());
	}
	
	public T removeLast() {
		if(isEmpty()) {
			throw new EmptyCollectionException("LinkedList");
		}
		return remove(last());
	}
	
	public T first() {
		return head.getElement();
	}
	
	public T last() {
		return tail.getElement();
	}
	
	public boolean contains(T target) {
		LinearNode<T> current = head;
		while(current != null) {
			if(target.equals(current.getElement())) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}
	
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public int size() {
		return count;
	}
	
	public String toString() {
		String result = "[ ";
		LinearNode<T> current = head;
		while(current != tail) {
			result += current.getElement().toString() + ", ";
			current = current.getNext();
		}
		result += current.getElement().toString() + " ]";
		return result;
	}
	
	public void print() { // print toString
		System.out.println(toString());
	}
	
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T> {
		
		private int iteratorModCount;
		private LinearNode<T> current;
		
		public LinkedListIterator() {
			iteratorModCount = modCount;
			current = head;
		}
		
		public boolean hasNext() {
			if(iteratorModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return (current != null);
		}

		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T result = current.getElement();
			current = current.getNext();
			return result;
		}	
		
	}

}
