import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/*
 * File name - Tests.java
 * Author - Dhrumil Amish Shah
 *
 * Test cases to test IndexableList and OrderedIndexableList.
 */
class Tests {

  @Test
  @DisplayName("testSOLIDPrinciple1Refactoring to test the ISP and DIP principles.")
  void testSOLIDPrinciple1Refactoring() {
    // Integer array list
    final List<Integer> integerArrayList = new ArrayList<>();
    integerArrayList.add(1);
    integerArrayList.add(2);
    integerArrayList.add(3);

    final OrderedIndexableList<Integer> orderedArrayList =
        new OrderedIndexableList<>(integerArrayList);

    Assertions.assertEquals(3, orderedArrayList.size(),
        "Ordered array list implementation is incorrect");

    // Integer linked list
    final List<Integer> integerLinkedList = new LinkedList<>();
    integerLinkedList.add(1);
    integerLinkedList.add(2);
    integerLinkedList.add(3);

    final OrderedIndexableList<Integer> orderedLinkedList =
        new OrderedIndexableList<>(integerLinkedList);

    Assertions.assertEquals(3, orderedLinkedList.size(),
        "Ordered linked list implementation is incorrect");

    // Integer array
    final Integer[] integerArray = new Integer[]{1, 2, 3};

    final OrderedIndexableList<Integer> orderedIntegerArr =
        new OrderedIndexableList<>(integerArray);

    Assertions.assertEquals(3, orderedIntegerArr.size(),
        "Ordered array implementation is incorrect");
  }

  @Test
  @DisplayName("testSOLIDPrinciple2Refactoring to test the ISP and DIP principles.")
  void testSOLIDPrinciple2Refactoring() {
    // Integer array list
    final List<Integer> integerLArrayList = new ArrayList<>();
    integerLArrayList.add(1);
    integerLArrayList.add(2);
    integerLArrayList.add(3);

    final List<Integer> integerRArrayList = new ArrayList<>();
    integerRArrayList.add(4);
    integerRArrayList.add(5);
    integerRArrayList.add(6);

    final IndexableList<Integer> indexableArrayList =
        new IndexableList<>(integerLArrayList, integerRArrayList);

    Assertions.assertEquals(6, indexableArrayList.size(),
        "Indexable array list implementation is incorrect");

    // Integer linked list
    final List<Integer> integerLLinkedList = new LinkedList<>();
    integerLLinkedList.add(1);
    integerLLinkedList.add(2);
    integerLLinkedList.add(3);

    final List<Integer> integerRLinkedList = new LinkedList<>();
    integerRLinkedList.add(4);
    integerRLinkedList.add(5);
    integerRLinkedList.add(6);

    final IndexableList<Integer> indexableLinkedList =
        new IndexableList<>(integerLLinkedList, integerRLinkedList);

    Assertions.assertEquals(6, indexableLinkedList.size(),
        "Indexable linked list implementation is incorrect");

    // Integer array
    final Integer[] integerLArray = new Integer[]{1, 2, 3};
    final Integer[] integerRArray = new Integer[]{4, 5, 6};

    final IndexableList<Integer> indexableIntegerArr =
        new IndexableList<>(integerLArray, integerRArray);

    Assertions.assertEquals(6, indexableIntegerArr.size(),
        "Indexable array implementation is incorrect");
  }

  @Test
  @DisplayName("testSOLIDPrinciple3Refactoring to test the ISP and DIP principles.")
  void testSOLIDPrinciple3Refactoring() {
    // Ordered indexable integer array list
    final List<Integer> integerArrayList = new ArrayList<>();
    integerArrayList.add(3);
    integerArrayList.add(1);
    integerArrayList.add(2);

    final OrderedIndexableList<Integer> orderedArrayList =
        new OrderedIndexableList<>(integerArrayList);

    final Scanner scanner1 =
        new IndexedListPrinter<>(orderedArrayList).toScanner();

    final String expectedString1 = "3 2 1 ";
    final StringBuilder actualString1 = new StringBuilder();

    while (scanner1.hasNext()) {
      actualString1.append(scanner1.next()).append(" ");
    }

    Assertions.assertEquals(expectedString1, actualString1.toString(),
        "Scanner result for ordered indexable list is incorrect");

    // Indexable integer array list
    final List<Integer> integerLArrayList = new ArrayList<>();
    integerLArrayList.add(1);
    integerLArrayList.add(2);
    integerLArrayList.add(3);

    final List<Integer> integerRArrayList = new ArrayList<>();
    integerRArrayList.add(4);
    integerRArrayList.add(5);
    integerRArrayList.add(6);

    final IndexableList<Integer> indexableArrayList =
        new IndexableList<>(integerLArrayList, integerRArrayList);

    final Scanner scanner2 =
        new IndexedListPrinter<>(indexableArrayList).toScanner();

    final String expectedString2 = "1 2 3 4 5 6 ";
    final StringBuilder actualString2 = new StringBuilder();

    while (scanner2.hasNext()) {
      actualString2.append(scanner2.next()).append(" ");
    }

    Assertions.assertEquals(expectedString2, actualString2.toString(),
        "Scanner result for indexable list is incorrect");
  }

  @Test
  public void testExceptions() {
    // Integer array
    final Integer[] integerLArray = new Integer[]{1, 2, 3};
    final Integer[] integerRArray = new Integer[]{4, 5, 6};

    // IndexableList test
    final IndexableList<Integer> indexableIntegerArr =
        new IndexableList<>(integerLArray, integerRArray);

    Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> indexableIntegerArr.get(-1));

    Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
        () -> indexableIntegerArr.set(-1, 10));

    // OrderedIndexableList test
    final OrderedIndexableList<Integer> orderedIndexableList =
        new OrderedIndexableList<>(integerLArray);

    Assertions.assertThrows(NullPointerException.class,
        () -> orderedIndexableList.add(null));
  }
}