import java.util.Iterator;

class List<E> implements Iterable<E> {
    elementList<E> head;
    int size;

    public List() {
        this.head = new elementList<>();
        this.size = 0;
    }

    int size() {
        return this.size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    E get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        elementList<E> current = head;
        for(int i=0; i < index; i++)
            current = current.next;

        return current.data;
    }

    E set(int index, E element) {
        if(element == null)
            throw new NullPointerException();

        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        elementList<E> current = head;
        for(int i=0; i < index; i++)
            current = current.next;

        E to_return = current.data;
        current.data = element;

        return to_return;
    }

    boolean contains(Object o) {
        elementList<E> current = head;
        for(int i=0; i < size; i++) {
            if(o == null ? current.data == null : o.equals(current.data))
                return true;
            current = current.next;
        }
        return false;
    }

    public boolean equals(Object o) {
        if(this != o) {
            if (o == null || getClass() != o.getClass()) return false;
            List<E> other = (List<E>) o;
            if (other.size != size) return false;

            elementList<E> current = head;
            elementList<E> otherCurrent = other.head;
            for (int i = 0; i < size; i++) {
                if (!(otherCurrent.data == null ? current.data == null : otherCurrent.data.equals(current.data))) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean add(E element) {
        if(element == null)
            throw new NullPointerException();

        if(this.isEmpty()) {
            head.data = element;
            size++;
            return true;
        }

        elementList<E> current = head;
        while(current.next != null) {
            current = current.next;
        }

        current.next = new elementList();
        current.next.data = element;
        size++;
        return true;
    }

    void add(int index, E element) {
        if(element == null)
            throw new NullPointerException();

        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        elementList<E> current = head;
        for(int i = 0; i < index; i++)
            current = current.next;

        E previous = current.data;
        current.data = element;
        while(current.next != null) {
            current = current.next;
            E temp = previous;
            previous = current.data;
            current.data = temp;
        }
        current.next = new elementList<E>();
        current.next.data = previous;
        size++;
    }

    E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        elementList<E> current = head;
        elementList<E> previous = null;

        for (int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }
        E to_return = current.data;
        current.data = null;

        if(previous == null)
            head = head.next;
        else
            previous.next = current.next;
        size--;
        return to_return;
    }

    boolean remove(Object o) {
        elementList<E> current = head;
        elementList<E> previous = null;

        for(int i=0; i < size; i++) {
            if(o == null ? current.data == null : o.equals(current.data)) {
                current.data = null;
                if(previous == null) head = head.next;
                else previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    void clear() {
        int s = size;
        for(int i = 0; i < s; i++)
            this.remove(0);
    }

    public int indexOf(Object o) {
        if(!this.contains(o)) return -1;

        elementList<E> current = head;
        for(int i=0; i<size; i++){
            if(o == null ? current.data == null : o.equals(current.data))
                return i;
            current = current.next;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if(!this.contains(o)) return -1;

        elementList<E> current = head;
        int index = -1;
        for(int i=0; i<size; i++){
            if(o == null ? current.data == null : o.equals(current.data))
                index = i;
            current = current.next;
        }
        return index;
    }

    public int hashCode() {
        int hashCode = 1;
        elementList<E> current = head;
        while(current != null) {
            hashCode = 31 * hashCode + (current.data == null ? 0 : current.data.hashCode());
            current = current.next;
        }
        return hashCode;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>(this);
    }

    public ListListIterator<E> listIterator(){
        return new ListListIterator<E>(this);
    }

    public ListListIterator<E> listIterator(int index){
        return new ListListIterator<E>(this, index);
    }
}