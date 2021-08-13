import java.util.Scanner;

/*
 * File name - IndexedListPrinter.java
 * Author - Dhrumil Amish Shah
 *
 * This class contains implementation logic to print the elements
 * stored in indexable list.
 */
public class IndexedListPrinter<E> {

  private final IndexableList<E> indexableList;

  /*
   * Constructor to initialize this indexed list printer.
   * Parameters:
   *   indexableList - Indexable list.
   */
  public IndexedListPrinter(IndexableList<E> indexableList) {
    this.indexableList = indexableList;
  }

  /*
   * Returns a Scanner containing the elements represented
   * by their toString() method.
   * Returns:
   *   - Scanner containing the elements of this list.
   */
  public Scanner toScanner() {
    String elementsString = "";
    int sz = indexableList.size();
    for (int i = 0; i < sz; i++) {
      elementsString += indexableList.get(i).toString() + " ";
    }
    return new Scanner(elementsString);
  }
}