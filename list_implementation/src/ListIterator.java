import java.util.NoSuchElementException;

public class ListIterator<E> implements java.util.Iterator<E> {
    public elementList<E> current;

    public ListIterator(List<E> list) {
        this.current = list.head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        if(!hasNext()) throw new NoSuchElementException();
        E to_return = current.data;
        current = current.next;
        return to_return;
    }

    @Override
    public void remove() {
        java.util.Iterator.super.remove();
    }
}
