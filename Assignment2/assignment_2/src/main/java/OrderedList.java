/*
 * File name - OrderedList.java
 * Author - Dhrumil Amish Shah
 *
 * Interface OrderedList provides a contract to insert elements in order.
 * Inserted elements are ordered based on the logic provided.
 */
public interface OrderedList<E extends Comparable<E>> {

  /*
   * Inserts the specified element into the correct position in this list.
   * Parameters:
   *   e - element to be appended to this list
   * Returns:
   *   true to indicate element is added in this list.
   */
  boolean add(E e);
}