import java.util.Arrays;

public class ArrayStack<T> implements StackADT<T> {
	
	private final static int DEFAULT_CAPACITY = 100;
	private int top; // next available spot & total elements currently in stack
	private T[] stack;
	
	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int initialCapacity) {
		top = 0;
		stack = (T[])(new Object[initialCapacity]);
	}

	public void push(T element) {
		if(size() == stack.length) {
			expandCapacity();
		}
		stack[top] = element;
		top++;		
	}

	public T pop() {
		if(isEmpty()) {
			throw new EmptyCollectionException("stack");
		}		
		top--;
		T result = stack[top];
		stack[top] = null;		
		return result;
	}

	public T peek() {
		if(isEmpty()) {
			throw new EmptyCollectionException("stack");
		}
		return stack[top-1];
	}

	public boolean isEmpty() {		
		return (size() == 0);
	}

	public int size() {
		return top;
	}
	
	public String toString() {
		String result = "\n|   |\n";
		for(int i = stack.length-1; i >= 0 ; i--) {
			if(stack[i] != null) {
				result += "| "+ stack[i] + " |\n"; 	
			}			
		}		
		result += "|___|";
		return result;
	}
	
	public void print() { // print toString
		System.out.println(toString());
	}
	
	private void expandCapacity() {
		stack = Arrays.copyOf(stack, stack.length * 2);
	}

}
