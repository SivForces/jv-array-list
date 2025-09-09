package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int size = 0;
    private int capacity = 10;
    private Object[] array = new Object[capacity];

    @Override
    public void add(T value) {
        if (size == array.length) {
            expand();
        }
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
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size == array.length) {
            expand();
        }
        while (array.length < size + list.size()) {
            expand();
        }
        for (int i = 0; i < list.size(); i++) {
            array[size] = list.get(i);
            size++;
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
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        boolean found = false;
        for (int i = 0; i < size - 1; i++) {
            if (Objects.equals(array[i], element)) {
                found = true;
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                break;
            }
        }
        if (found == false) {
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
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void expand() {
        if (array.length == capacity) {
            capacity += capacity / 2;

            Object[] newArray = new Object[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }

            array = newArray;
        }
    }

    public void checkSize(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of Array limits");
        }
    }
}
