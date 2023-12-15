import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements ListADT<T>, Iterable<T> {
	
	private final static int DEFAULT_CAPACITY = 100;
	private final static int NOT_FOUND = -1;
	
	protected int rear;
	protected T[] list;
	protected int modCount;
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int initialCapacity) {
		rear = 0;
		list = (T[])(new Object[initialCapacity]);
		modCount = 0;
	}
	
	@SuppressWarnings("unchecked")
	public void add(T element) {
		if(!(element instanceof Comparable)) {
			throw new NonComparableElementException("OrderedList");
		}
		Comparable<T> comparableElement = (Comparable<T>) element;
		if(size() == list.length) {
			expandCapacity();
		}
		int i = 0;
		// find the index to insert
		while((i < rear) && (comparableElement.compareTo(list[i]) > 0)) {
			i++;
		}
		for(int j = rear; j > i; j--) { // shift existing elements right
			list[j] = list[j-1];
		}
		list[i] = element;
		rear++;
		modCount++;
	}
	
	public void addToFront(T element) {
		if(size() == list.length) {
			expandCapacity();
		}
		for(int i = rear; i > 0; i--) { // shift right
			list[i] = list[i-1];
		}
		list[0] = element;
		rear++;
		modCount++;
	}
	
	public void addToRear(T element) {
		if(size() == list.length) {
			expandCapacity();
		}
		list[rear] = element;
		rear++;
		modCount++;
	}
	
	public void addAfter(T element, T target) {
		if(size() == list.length) {
			expandCapacity();
		}
		int i = 0;
		// find the index to insert
		while(i < rear && !target.equals(list[i])) {
			i++;
		}
		if(i == rear) {
			throw new ElementNotFoundException("UnorderedList");
		}
		i++;
		for(int j = rear; j > i; j--) { // shift existing elements up one
			list[j] = list[j-1];
		}
		list[i] = element;
		rear++;
		modCount++;
	}
	
	public T remove(T element) {
		int index = find(element);
		if(index == NOT_FOUND) {
			throw new ElementNotFoundException("ArrayList");
		}
		T elementRemoved = list[index];
		rear--;
		for(int i = index; i < rear; i++) { // shift elements for gap
			list[i] = list[i+1];
		}
		list[rear] = null;
		modCount++;		
		return elementRemoved;	
	}
	
	public T removeFirst() {
		return remove(first());
	}
	
	public T removeLast() {
		return remove(last());
	}
	
	public T first() {
		if(isEmpty()) {
			throw new ElementNotFoundException("ArrayList");
		}
		return list[0];
	}
	
	public T last() {
		if(isEmpty()) {
			throw new ElementNotFoundException("ArrayList");
		}
		return list[rear-1];
	}
	
	public boolean contains(T target) {		
		return (find(target) != NOT_FOUND);
	}
	
	private int find(T target) {
		int i = 0;
		int result = NOT_FOUND; // -1
		if(!isEmpty()) {
			while(result == NOT_FOUND && i < rear) {
				if(target.equals(list[i])) {
					result = i;
				} else {
					i++;
				}
			}
		}
		return result;
	}
	
	private void expandCapacity() {
		list = Arrays.copyOf(list, list.length * 2);
	}
	
	public boolean isEmpty() {
		return (rear == 0);
	}
	
	public int size() {
		return rear;
	}
	
	public String toString() {
		String result = "[ ";
		for(int i = 0; i < rear-1; i++) {
			if(list[i] != null) {
				result += list[i] + ", "; 	
			}			
		}		
		result += list[rear-1] + " ]";
		return result;
	}
	
	public void print() { // print toString
		System.out.println(toString());
	}
	
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<T> {
		
		int iteratorModCount;
		int current;
		
		public ArrayListIterator() {
			iteratorModCount = modCount;
			current = 0;
		}
		
		public boolean hasNext() {
			if(iteratorModCount != modCount) {
				throw new ConcurrentModificationException();
			}
			return (current < rear);
		}

		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			current++;
			return list[current-1];
		}	
		
	}
	
}
