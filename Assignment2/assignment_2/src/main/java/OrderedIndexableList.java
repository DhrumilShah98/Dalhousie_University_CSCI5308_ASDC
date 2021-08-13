import java.util.List;

/*
 * File name - OrderedIndexableList.java
 * Author - Dhrumil Amish Shah
 *
 * Class OrderedIndexableList extends IndexableList and implements OrderedList.
 * This class is responsible for ordering elements in this list.
 * i.e., all elements are ordered based on the logic provided.
 * Parameters:
 *   E - Type of elements to add in this list.
 */
public class OrderedIndexableList<E extends Comparable<E>>
    extends IndexableList<E> implements OrderedList<E> {

  /*
   * Constructor to initialize this empty list.
   */
  public OrderedIndexableList() {
  }

  /*
   * Add the elements from the list in this list.
   * Parameters:
   *   list - List of elements to add.
   */
  public OrderedIndexableList(List<E> list) {
    for (E e : list) {
      add(e);
    }
  }

  /*
   * Add the elements from the array in this list.
   * Parameters:
   *   array - Array of elements to add.
   */
  public OrderedIndexableList(E[] array) {
    for (E e : array) {
      add(e);
    }
  }

  /*
   * Inserts the specified element into the correct position in this list.
   * Parameters:
   *   e - element to be appended to this list
   * Returns:
   *   true to indicate element is added in this list.
   */
  @Override
  public boolean add(E e) throws NullPointerException {
    int insertPosition;
    for (insertPosition = 0; insertPosition < size(); insertPosition++) {
      if (e.compareTo(get(insertPosition)) > 0) {
        break;
      }
    }
    add(insertPosition, e);
    return true;
  }
}