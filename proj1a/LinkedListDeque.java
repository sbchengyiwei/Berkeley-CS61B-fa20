public class LinkedListDeque<T> {

    private class TNode {
        private T item;
        private TNode prev;
        private TNode next;


        private TNode(T x, TNode p, TNode n) {
            item = x;
            prev = p;
            next = n;
        }
    }


    private TNode sentinel;
    private int size;


    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    public void addFirst(T item) {
        sentinel.next = new TNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }


    public void addLast(T item) {
        sentinel.prev = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }


    public T removeFirst() {
        T t = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        if (!isEmpty()) {
            size -= 1;
        }
        return t;

    }


    public T removeLast() {
        T t = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        if (!isEmpty()) {
            size -= 1;
        }
        return t;
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public void printDeque() {
        TNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }


    public T get(int index) {
        TNode g = sentinel.next;
        for (int i = 0; i < index; i++) {
            g = g.next;
        }
        return g.item;
    }


    private T getRecursiveHelper(int index, TNode n) {

        if (index == 0) {
            return n.item;
        }
        return getRecursiveHelper(index - 1, n.next);
    }


    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

}
