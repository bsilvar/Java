import java.util.NoSuchElementException;

public class DoubleLinkedList<T> {

    private int count;
    private LinearDoubleNode<T> head;
    private LinearDoubleNode<T> tail;

    public DoubleLinkedList() {
        count = 0;
        head = tail = null;
    }

    public void addToFront(T element) {
        LinearDoubleNode<T> node = new LinearDoubleNode<T>(element);
        if(size() == 0) {
            head = tail = node;
        } else {
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        }
        count++;
    }

    public void addToRear(T element) {
        LinearDoubleNode<T> node = new LinearDoubleNode<T>(element);
        if(size() == 0) {
            head = tail = node;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
        count++;
    }

    public void add(T element) {
        addToRear(element);
    }

    public void addAfter(T element, T target) {
        if(size() == 0) {
            throw new NoSuchElementException();
        }
        LinearDoubleNode<T> curr = head;
        boolean found = false;
        while(curr != null && !found) {
            if(target.equals(curr.getElement())) {
                found = true;
            } else {
                curr = curr.getNext();
            }
        }
        if(!found) {
            throw new NoSuchElementException();
        }
        LinearDoubleNode<T> node = new LinearDoubleNode<T>(element);
        if(curr == tail) {
            curr.setNext(node);
            node.setPrevious(curr);
            tail = node;
        } else {
            node.setPrevious(curr);
            node.setNext(curr.getNext());
            LinearDoubleNode<T> temp = curr.getNext();
            temp.setPrevious(node);
            curr.setNext(node);
        }
        count++;
    }

    public T remove(T element) {
        if(isEmpty()) {
            throw new EmptyCollectionException("DoubleLinkedList");
        }
        LinearDoubleNode<T> curr = head;
        boolean found = false;
        while(curr != null && !found) {
            if(element.equals(curr.getElement())) {
                found = true;
            } else {
                curr = curr.getNext();
            }
        }
        if(!found) {
            throw new NoSuchElementException();
        }
        if(size() == 1) {
            head = tail = null;
        } else {
            if(curr == head) {
                head = head.getNext();
                head.setPrevious(null);
            } else if(curr == tail) {
                tail = tail.getPrevious();
                tail.setNext(null);
            } else {
                LinearDoubleNode<T> prev = curr.getPrevious();
                prev.setNext(curr.getNext());
                LinearDoubleNode<T> next = curr.getNext();
                next.setPrevious(prev);
            }
        }
        count--;
        return curr.getElement();
    }

    public T removeFirst() {
        return remove(first());
    }

    public T removeLast() {
        return remove(last());
    }

    public T first() {
        return head.getElement();
    }

    public T last() {
        return tail.getElement();
    }

    public boolean contains(T target) {
        LinearDoubleNode<T> curr = head;
        while(curr != null) {
            if(target.equals(curr.getElement())) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public int size() {
        return count;
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("[");

        LinearDoubleNode<T> current = head;
        while (current != null)
        {
            str.append(current.getElement());

            if (current.getNext() != null)
            {
                str.append(", ");
            }

            current = current.getNext();
        }

        str.append("]");
        return str.toString();
    }

    public void print() {
        System.out.println(toString());
    }

}
