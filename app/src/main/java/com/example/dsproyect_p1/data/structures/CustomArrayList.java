import java.util.*;

public class CustomArrayList<E> implements List<E> {
  private static final int DEFAULT_CAPACITY = 10;
  private Object[] elements;
  private int size = 0;

  // Constructor with default capacity
  public CustomArrayList() {
    elements = new Object[DEFAULT_CAPACITY];
  }

  // Constructor with custom capacity
  public CustomArrayList(int capacity) {
    if (capacity >= 0) {
      elements = new Object[capacity];
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  // -------- Implementing List Interface Methods -------- //

  // Iterator implementation
  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      int cursor = 0; // Index of next element to return

      @Override
      public boolean hasNext() {
        return cursor < size;
      }

      @Override
      @SuppressWarnings("unchecked")
      public E next() {
        if (cursor >= size) {
          throw new NoSuchElementException();
        }
        return (E) elements[cursor++];
      }
    };
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elements, size);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] a) {
    if (a.length < size) {
      // Make a new array of a's runtime type, but my contents:
      return (T[]) Arrays.copyOf(elements, size, a.getClass());
    }
    System.arraycopy(elements, 0, a, 0, size);
    if (a.length > size) a[size] = null;
    return a;
  }

  @Override
  public boolean add(E e) {
    ensureCapacity(size + 1); // Ensure capacity
    elements[size++] = e;
    return true;
  }

  @Override
  public boolean remove(Object o) {
    int index = indexOf(o);
    if (index >= 0) {
      remove(index); // Remove element at index
      return true;
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    for (Object e : c) if (!contains(e)) return false;
    return true;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    Object[] a = c.toArray();
    int numNew = a.length;
    ensureCapacity(size + numNew); // Ensure capacity
    System.arraycopy(a, 0, elements, size, numNew);
    size += numNew;
    return numNew != 0;
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    checkIndexForAdd(index);
    Object[] a = c.toArray();
    int numNew = a.length;
    ensureCapacity(size + numNew); // Ensure capacity

    int numMoved = size - index;
    if (numMoved > 0) System.arraycopy(elements, index, elements, index + numNew, numMoved);

    System.arraycopy(a, 0, elements, index, numNew);
    size += numNew;
    return numNew != 0;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    Objects.requireNonNull(c);
    boolean modified = false;
    for (Iterator<?> it = iterator(); it.hasNext(); ) {
      if (c.contains(it.next())) {
        it.remove();
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    Objects.requireNonNull(c);
    boolean modified = false;
    for (Iterator<?> it = iterator(); it.hasNext(); ) {
      if (!c.contains(it.next())) {
        it.remove();
        modified = true;
      }
    }
    return modified;
  }

  @Override
  public void clear() {
    // Help GC
    for (int i = 0; i < size; i++) elements[i] = null;
    size = 0;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E get(int index) {
    checkIndex(index);
    return (E) elements[index];
  }

  @Override
  @SuppressWarnings("unchecked")
  public E set(int index, E element) {
    checkIndex(index);
    E oldValue = (E) elements[index];
    elements[index] = element;
    return oldValue;
  }

  @Override
  public void add(int index, E element) {
    checkIndexForAdd(index);
    ensureCapacity(size + 1); // Ensure capacity
    System.arraycopy(elements, index, elements, index + 1, size - index);
    elements[index] = element;
    size++;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    checkIndex(index);
    E oldValue = (E) elements[index];

    int numMoved = size - index - 1;
    if (numMoved > 0) System.arraycopy(elements, index + 1, elements, index, numMoved);

    elements[--size] = null;
    return oldValue;
  }

  @Override
  public int indexOf(Object o) {
    if (o == null) {
      for (int i = 0; i < size; i++) if (elements[i] == null) return i;
    } else {
      for (int i = 0; i < size; i++) if (o.equals(elements[i])) return i;
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object o) {
    if (o == null) {
      for (int i = size - 1; i >= 0; i--) if (elements[i] == null) return i;
    } else {
      for (int i = size - 1; i >= 0; i--) if (o.equals(elements[i])) return i;
    }
    return -1;
  }

  // ListIterator implementation
  @Override
  public ListIterator<E> listIterator() {
    return listIterator(0);
  }

  @Override
  @SuppressWarnings("unchecked")
  public ListIterator<E> listIterator(int index) {
    checkIndexForAdd(index);
    return new ListIterator<E>() {
      int cursor = index; // index of next element to return
      int lastRet = -1; // index of last element returned; -1 if no such

      @Override
      public boolean hasNext() {
        return cursor != size;
      }

      @Override
      public E next() {
        if (cursor >= size) throw new NoSuchElementException();
        lastRet = cursor++;
        return (E) elements[lastRet];
      }

      @Override
      public boolean hasPrevious() {
        return cursor != 0;
      }

      @Override
      public E previous() {
        if (cursor <= 0) throw new NoSuchElementException();
        lastRet = --cursor;
        return (E) elements[lastRet];
      }

      @Override
      public int nextIndex() {
        return cursor;
      }

      @Override
      public int previousIndex() {
        return cursor - 1;
      }

      @Override
      public void remove() {
        if (lastRet < 0) throw new IllegalStateException();
        CustomArrayList.this.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
      }

      @Override
      public void set(E e) {
        if (lastRet < 0) throw new IllegalStateException();
        CustomArrayList.this.set(lastRet, e);
      }

      @Override
      public void add(E e) {
        CustomArrayList.this.add(cursor++, e);
        lastRet = -1;
      }
    };
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    checkIndexForAdd(fromIndex);
    checkIndexForAdd(toIndex);
    if (fromIndex > toIndex) throw new IllegalArgumentException("fromIndex > toIndex");
    return new AbstractList<E>() {
      @Override
      public E get(int index) {
        return CustomArrayList.this.get(fromIndex + index);
      }

      @Override
      public int size() {
        return toIndex - fromIndex;
      }
    };
  }

  // Ensure the capacity of the internal array
  private void ensureCapacity(int minCapacity) {
    if (minCapacity > elements.length) {
      int newCapacity = Math.max(elements.length * 2, minCapacity);
      elements = Arrays.copyOf(elements, newCapacity);
    }
  }

  // Check if index is within bounds for get and remove operations
  private void checkIndex(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }

  // Check if index is within bounds for add operation
  private void checkIndexForAdd(int index) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
  }
}
