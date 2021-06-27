public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }


    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int index = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            temp[i] = items[index];
            index = plusOne(index);
        }
        items = temp;
        nextFirst = capacity - 1; 
        nextLast = size;

    }


    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }


    private int plusOne(int index) {
        return (index + 1) % items.length;
    }


    private boolean isWaste() {
        return items.length >= 16 && size < (items.length / 4);
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (isWaste()) {
            resize(items.length / 2);
        }
        nextFirst = plusOne(nextFirst);
        T t = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return t;
    }

    @Override
    public T removeLast() {
        if (isWaste()) {
            resize(items.length / 2);
        }
        nextLast = minusOne(nextLast);
        T t = items[nextLast];
        items[nextLast] = null;
        if (size != 0) {
            size -= 1;
        }
        return t;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int start = plusOne(nextFirst);
        return items[(start + index) % items.length];
    }

}
