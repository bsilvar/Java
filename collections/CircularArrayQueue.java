public class CircularArrayQueue<T> implements QueueADT<T> {
	
	private final static int DEFAULT_CAPACITY = 100;
	private int front, rear, count;
	private T[] queue;
	
	public CircularArrayQueue() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int initialCapacity) {
		front = rear = count = 0;
		queue = (T[])(new Object[initialCapacity]);
	}

	public void enqueue(T element) {	
		if(size() == queue.length) {
			expandCapacity();
		}		
		queue[rear] = element;
		rear = (rear+1) % queue.length;
		count++;
	}

	public T dequeue() {
		if(isEmpty()) {
			throw new EmptyCollectionException("queue");
		}
		T element = queue[front];
		queue[front] = null;
		front = (front+1) % queue.length;		
		count--;	
		return element;
	}

	public T first() {
		return queue[front];
	}

	public boolean isEmpty() {
		return (count == 0);
	}

	public int size() {
		return count;
	}
	
	@SuppressWarnings("unchecked")
	private void expandCapacity() {
		T[] newArray = (T[])(new Object[queue.length*2]);
		for(int i = 0; i < count; i++) {
			newArray[i] = queue[front];
			front = (front+1) % queue.length;
		}
		front = 0;
		rear = count;
		queue = newArray;
	}

	@SuppressWarnings("unchecked")
	public String toString() {
		int temp = front;		
		T[] copy = (T[])(new Object[count]);
		for(int i = 0; i < count; i++) {
			copy[i] = queue[temp];
			temp = (temp+1) % queue.length;
		}
		String result = "\n------------------\n";
		for(int i = 0; i < copy.length; i++) {
			if(copy[i] != null) {
				result += " "+ copy[i] + " ";	
			}			
		}	
		result += "\n------------------";
		return result;
	}
	
	public void print() { // print toString
		System.out.println(toString());
	}

}
