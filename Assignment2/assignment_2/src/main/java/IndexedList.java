import java.util.List;

/*
 * File name - IndexedList.java
 * Author - Dhrumil Amish Shah
 *
 * Interface IndexedList provides a contract to implement indexable list.
 */
public interface IndexedList<E> {

  /*
   * Appends the specified element to the end of the list.
   * Parameters:
   *   e - Element to be appended to the list.
   * Returns:
   *   true if element is appended otherwise false.
   */
  boolean append(E e);

  /*
   * Appends the specified element to the start of the list.
   * Parameters:
   *   e - Element to be appended to the list.
   * Returns:
   *   true if element is appended otherwise false.
   */
  boolean prepend(E e);

  /*
   * Inserts the specified element at the specified
   * position in the list.
   * Parameters:
   *  index - Index at which the specified element is
   *          to be inserted.
   *  element - Element to be inserted.
   */
  void add(int index, E element);

  /*
   * Checks if the element exists in the list.
   * Parameters:
   *   o - Element to be checked.
   * Returns:
   *   true if the element o exists otherwise false.
   */
  boolean contains(Object o);

  /*
   * Get the element at the index position.
   * Parameters:
   *  index - Index at which the specified element is
   *          to be found.
   * Returns:
   *   element at the index position.
   */
  E get(int index);

  /*
   * Returns the index of the first occurrence of the
   * specified element in the list.
   * Parameters:
   *   o - element to search for.
   * Returns:
   *   The index of the first occurrence of the specified
   *   element in the list.
   */
  int indexOf(Object o);

  /*
   * Check if the list contains any elements.
   * Returns:
   *   true if the list contains no elements.
   */
  boolean isEmpty();

  /*
   * Removes the element at the specified position in
   * the list.
   * Parameters:
   *   index - The index of the element to be removed.
   * Returns:
   *   The element previously at the specified position.
   */
  E remove(int index);

  /*
   * Removes the first occurrence of the specified element
   * from the list, if it is present.
   * Parameters:
   *   o - Element to be removed from the list, if present.
   * Returns:
   *   true if the list contained the specified element.
   */
  boolean remove(Object o);

  /*
   * Replaces the element at the specified position in the
   * list with the specified element.
   * Parameters:
   *   index - index of the element to replace.
   *   element - element to be stored at the specified position.
   * Returns:
   *   the element previously at the specified position.
   */
  E set(int index, E element);

  /*
   * Gets the size of the list.
   * Returns:
   *   The size of the list.
   */
  int size();

  /*
   * Returns a view of the portion of the list between
   * the specified fromIndex, inclusive, and toIndex, exclusive.
   * Parameters:
   *   fromIndex - Low endpoint (inclusive) of the subList.
   *   toIndex - High endpoint (exclusive) of the subList.
   * Returns:
   *   A view of the specified range within the list.
   */
  List<E> subList(int fromIndex, int toIndex);
}