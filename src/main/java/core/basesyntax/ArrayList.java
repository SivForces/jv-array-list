package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float GROWTH_FACTOR = 1.5f;
    private int size = 0;
    private Object[] array = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        expand();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Array limits");
        }
        if (size == array.length) {
            expand();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() != 0) {
            while (array.length < size + list.size()) {
                expand();
            }
            for (int i = 0; i < list.size(); i++) {
                array[size] = list.get(i);
                size++;
            }
        }
    }

    @Override
    public T get(int index) {
        checkSize(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkSize(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkSize(index);
        final T element = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if ((array[i] == null ? element == null : array[i].equals(element))) {
                found = true;
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                break;
            }
        }
        if (!found) {
            throw new NoSuchElementException("There is no such element");
        }
        array[size - 1] = null;
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void expand() {
        if (size == array.length) {
            int newCapacity = (int)(array.length * GROWTH_FACTOR);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    public void checkSize(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Array limits");
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    public boolean contains(T value) {
        boolean isContaining = false;
        if (value == null) {
            throw new NullPointerException("Cannot search null element");
        }
        for (int i = 0; i < size; i++) {
            if (value == null ? array[i] == null : value.equals(array[i])) {
                isContaining = true;
                return isContaining;
            }
        }
        return isContaining;
    }
}
