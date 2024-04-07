import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListListIterator<E> implements Iterator<E> {
    public elementList<E> next;
    public elementList<E> previous;
    public List<E> list;

    public int nextIndex;
    public int previousIndex;
    public elementList<E> lastReturned = null;

    public ListListIterator(List<E> list){
        previous = null;
        next = list.head;
        nextIndex = 0;
        this.list = list;
    }
    public ListListIterator(List<E> list, int index) {
        if (index < 0 || index > list.size) throw new IndexOutOfBoundsException();
        previous = null;
        next = list.head;
        for (int i = 0; i < index; i++) {
            previous = next;
            next = next.next;
        }
        nextIndex = index;
        if(index != 0) previousIndex = index - 1;
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    public boolean hasPrevious() {
        return previous != null;
    }

    @Override
    public E next() {
        if(!hasNext()) throw new NoSuchElementException();
        lastReturned = next;
        if(previous == null) {
            previousIndex = 0;
        } else previousIndex++;
        previous = next;
        next = next.next;
        nextIndex++;
        return lastReturned.data;
    }

    public E previous() {
        if(!hasPrevious()) throw new NoSuchElementException();
        lastReturned = previous;
        elementList<E> current = list.head;
        if(previousIndex == 0) {
            previous = null;
            next = list.head;
            nextIndex = 0;
        }
        else {
            for (int i = 0; i < previousIndex - 1; i++) current = current.next;
            previous = current;
            next = current.next;
            previousIndex--;
            nextIndex--;
        }
        return lastReturned.data;
    }

    public int nextIndex(){
        return nextIndex;
    }

    public int previousIndex(){
        return previousIndex;
    }

    @Override
    public void remove() {
        if(lastReturned == null) return;
        elementList<E> current = list.head;
        elementList<E> prev = null;
        nextIndex = 0;

        while(current != lastReturned){
            prev = current;
            current = current.next;
            previousIndex = nextIndex;
            nextIndex++;
        }
        if(prev != null) previous = prev;
        next = current.next;
        list.remove(nextIndex());
    }

    public void add(E element){
        elementList<E> current = next;
        while(current.next != null){
            current = current.next;

        }
        current.next = new elementList<E>();
        current.next.data = element;
        list.size++;
    }

    public void set(E element){
        if(lastReturned != null) lastReturned.data = element;
    }
}
