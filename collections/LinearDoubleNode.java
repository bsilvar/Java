public class LinearDoubleNode<T> {

    private LinearDoubleNode<T> next; // reference to next node (pointer)
    private LinearDoubleNode<T> previous; // reference to previous node (pointer)
    private T element;          // data stored inside node

    public LinearDoubleNode() {
        next = previous = null;
        element = null;
    }

    public LinearDoubleNode(T element) {
        next = previous = null;
        this.element = element;
    }

    public LinearDoubleNode<T> getNext() {
        return next;
    }

    public LinearDoubleNode<T> getPrevious() {
        return previous;
    }

    public LinearDoubleNode<T> setNext(LinearDoubleNode<T> node) {
        return next = node;
    }

    public LinearDoubleNode<T> setPrevious(LinearDoubleNode<T> node) {
        return previous = node;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

}
