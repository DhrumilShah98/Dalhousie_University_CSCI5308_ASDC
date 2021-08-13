import java.util.ArrayList;
import java.util.List;

/*
 * File name - IndexableList.java
 * Author - Dhrumil Amish Shah
 *
 * This class implements an indexable list, which is a list whose
 * elements can be efficiently accessed by their indices in the list.
 * i.e., the first element has index 0, the second has index 1, etc.
 *
 * This class uses two arrays to speed up insertion and deletion.
 * Inserting an element into an indexable list requires making space
 * for the new element by shuffling other elements. If implemented with
 * only one array then typically this is done by moving all elements
 * at the desired index or greater to the right one position in the array.
 * In this class, elements are moved to the left one position if
 * that requires less shuffling. Deletion is similar.
 *
 * IndexableList uses two arrays (leftArray and rightArray) to store
 * the elements. When either array fills up, a bigger array is allocated.
 * The element with index 0 can be at an arbitrary point in either list.
 * Parameters:
 *   E - Type of elements to add in this list.
 */
public class IndexableList<E> implements IndexedList<E> {

  private static final int ERROR_VALUE = -3;

  protected int leftNumElements;  // number of elements in leftArray

  protected int rightNumElements;  // number of elements in rightArray

  protected Object[] leftArray = new Object[0];  // left array

  protected Object[] rightArray = new Object[0];  // right array

  /*
   * Constructor to initialize empty lists.
   */
  public IndexableList() {
  }

  /*
   * Adds the elements from the left and right list in this left and right
   * array.
   * Parameters:
   *   leftList - List of elements to add in left array.
   *   rightList - List of elements to add in right array.
   */
  public IndexableList(List<E> leftList, List<E> rightList) {
    leftNumElements = leftList.size();
    rightNumElements = rightList.size();
    rightArray = new Object[rightNumElements];
    leftArray = new Object[leftNumElements];
    for (int i = 0; i < leftNumElements; i++) {
      leftArray[i] = leftList.get(leftNumElements - 1 - i);
    }
    for (int i = 0; i < rightNumElements; i++) {
      rightArray[i] = rightList.get(i);
    }
  }

  /*
   * Adds the elements from the left and right array in this left and right
   * array.
   * Parameters:
   *   lArray - List of elements to add in left array.
   *   rArray - List of elements to add in right array.
   */
  public IndexableList(E[] lArray, E[] rArray) {
    leftNumElements = lArray.length;
    rightNumElements = rArray.length;
    rightArray = new Object[rightNumElements];
    leftArray = new Object[leftNumElements];
    for (int i = 0; i < leftNumElements; i++) {
      leftArray[i] = lArray[leftNumElements - 1 - i];
    }
    for (int i = 0; i < rightNumElements; i++) {
      rightArray[i] = rArray[i];
    }
  }

  /*
   * Extend the left array by extend time.
   * Parameters:
   *   extend - Amount by which left array is to be extended.
   */
  private void extendLeftArray(int extend) {
    Object[] prevArray = leftArray;
    leftArray = new Object[leftArray.length + extend];
    for (int i = 0; i < leftNumElements; i++) {
      leftArray[i] = prevArray[i];
    }
  }

  /*
   * Extend the right array by extend time.
   * Parameters:
   *   extend - Amount by which right array is to be extended.
   */
  private void extendRightArray(int extend) {
    Object[] prevArray = rightArray;
    rightArray = new Object[prevArray.length + extend];
    for (int i = 0; i < rightNumElements; i++) {
      rightArray[i] = prevArray[i];
    }
  }

  /*
   * Returns the error value ERROR_VALUE.
   * Returns:
   *   ERROR_VALUE.
   */
  public int errorValue() {
    return ERROR_VALUE;
  }


  /*
   * Appends the specified element to the end of the right list.
   * Parameters:
   *   e - Element to be appended to this list.
   * Returns:
   *   true to indicate element is appended.
   */
  @Override
  public boolean append(E e) {
    add(size(), e);
    return true;
  }

  /*
   * Appends the specified element to the end of the left list.
   * Parameters:
   *   e - Element to be appended to this list.
   * Returns:
   *   true to indicate element is appended.
   */
  @Override
  public boolean prepend(E e) {
    add(0, e);
    return true;
  }

  /*
   * Inserts the specified element at the specified
   * position in this list.
   * Parameters:
   *  index - Index at which the specified element is
   *          to be inserted.
   *  element - Element to be inserted.
   */
  @Override
  public void add(int index, E element) {
    if (index < (size() + 1) / 2) {
      if (leftArray.length == leftNumElements) {
        extendLeftArray(1000);
      }
      leftNumElements++;
      for (int i = 0; i < index; i++) {
        set(i, get(i + 1));
      }
    } else {
      if (rightArray.length == rightNumElements) {
        extendRightArray(1000);
      }
      rightNumElements++;
      for (int i = size() - 1; i > index; i--) {
        set(i, get(i - 1));
      }
    }
    set(index, element);
  }

  /*
   * Checks if the left or right array contains the element o.
   * Parameters:
   *   o - Element to be checked.
   * Returns:
   *   true if the element o exists otherwise false.
   */
  @Override
  public boolean contains(Object o) {
    for (int i = 0; i < leftNumElements; i++) {
      if (leftArray[i].equals(o)) {
        return true;
      }
    }
    for (int i = 0; i < rightNumElements; i++) {
      if (rightArray[i].equals(o)) {
        return true;
      }
    }
    return false;
  }

  /*
   * Get the element at the index position.
   * Parameters:
   *  index - Index at which the specified element is
   *          to be found.
   * Returns:
   *   Element at the index position.
   */
  @Override
  public E get(int index) throws ArrayIndexOutOfBoundsException {
    if (index < leftNumElements) {
      return (E) leftArray[leftNumElements - 1 - index];
    } else {
      return (E) rightArray[index - leftNumElements];
    }
  }

  /*
   * Returns the index of the first occurrence of the
   * specified element in this list, or ERROR_VALUE if this list
   * does not contain the element.
   * Parameters:
   *   o - Element to search for.
   * Returns:
   *   The index of the first occurrence of the specified
   *   element in this list, or ERROR_VALUE if this list does not
   *   contain the element.
   */
  @Override
  public int indexOf(Object o) {
    for (int i = leftNumElements - 1; i >= 0; i--) {
      if (leftArray[i].equals(o)) {
        return leftNumElements - i - 1;
      }
    }
    for (int i = 0; i < rightNumElements; i++) {
      if (rightArray[i].equals(o)) {
        return leftNumElements + i;
      }
    }
    return ERROR_VALUE;
  }

  /*
   * Check if this list contains any elements.
   * Returns:
   *   true if this list contains no elements.
   */
  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  /*
   * Removes the element at the specified position in
   * this list.
   * Parameters:
   *   index - The index of the element to be removed.
   * Returns:
   *   The element previously at the specified position.
   */
  @Override
  public E remove(int index) {
    E removedItem = get(index);
    if (index < (size() + 1) / 2 && leftNumElements > 0) {
      for (int i = index; i > 0; i--) {
        set(i, get(i - 1));
      }
      set(0, null);
      leftNumElements--;
    } else {
      int size = size();
      for (int i = index; i < size - 1; i++) {
        set(i, get(i + 1));
      }
      set(size - 1, null);
      rightNumElements--;
    }
    return removedItem;
  }

  /*
   * Removes the first occurrence of the specified element
   * from this list, if it is present.
   * Parameters:
   *   o - Element to be removed from this list, if present.
   * Returns:
   *   true if this list contained the specified element.
   */
  @Override
  public boolean remove(Object o) {
    int removePosition = indexOf(o);
    if (removePosition > ERROR_VALUE) {
      remove(removePosition);
    }
    return removePosition > ERROR_VALUE;
  }

  /*
   * Replaces the element at the specified position in this
   * list with the specified element.
   * Parameters:
   *   index - Index of the element to replace.
   *   element - Element to be stored at the specified position.
   * Returns:
   *   the element previously at the specified position.
   */
  @Override
  public E set(int index, E element) throws ArrayIndexOutOfBoundsException {
    E prevElement = get(index);
    if (index < leftNumElements) {
      leftArray[leftNumElements - 1 - index] = element;
    } else {
      rightArray[index - leftNumElements] = element;
    }
    return prevElement;
  }

  /*
   * Gets the size of this list.
   * Returns:
   *   The size of this list.
   */
  @Override
  public int size() {
    return leftNumElements + rightNumElements;
  }

  /*
   * Returns a view of the portion of this list between
   * the specified fromIndex, inclusive, and toIndex, exclusive.
   * Parameters:
   *   fromIndex - Low endpoint (inclusive) of the subList.
   *   toIndex - High endpoint (exclusive) of the subList.
   * Returns:
   *   A view of the specified range within this list.
   */
  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    List<E> list = new ArrayList<>();
    for (int i = fromIndex; i < toIndex; i++) {
      list.add(get(i));
    }
    return list;
  }
}