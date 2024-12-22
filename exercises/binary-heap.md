# Implementing and testing a binary heap

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

Design and implement a test suite for this `BinaryHeap` class.
Feel free to add any extra method you may need.

Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-heap](../code/tp3-heap) to complete this exercise.

## Answer


We have used the rule JUnitTestContainsTooManyAsserts to test the Commons Collections
library. Around a thousand tests were found with this issue.

An example of test with issue could be like this one:

```xml
<violation beginline="1350" endline="1369" begincolumn="12" endcolumn="5" rule="JUnitTestContainsTooManyAsserts"
           ruleset="Best Practices" package="org.apache.commons.collections4.list"
           class="CursorableLinkedListTest" method="testSubListRemove"
           externalInfoUrl="https://pmd.github.io/pmd-6.55.0/pmd_rules_java_bestpractices.html#junittestcontainstoomanyasserts"
           priority="3">
    Unit tests should not contain more than 1 assert(s).
</violation>
```

which corresponds to this test:

```java
@Test
    @SuppressWarnings("unchecked")
    public void testSubListRemove() {
        list.add((E) "A");
        list.add((E) "B");
        list.add((E) "C");
        list.add((E) "D");
        list.add((E) "E");

        final List<E> sublist = list.subList(1, 4);
        assertEquals("[B, C, D]", sublist.toString());
        assertEquals("[A, B, C, D, E]", list.toString());
        sublist.remove("C");
        assertEquals("[B, D]", sublist.toString());
        assertEquals("[A, B, D, E]", list.toString());
        sublist.remove(1);
        assertEquals("[B]", sublist.toString());
        assertEquals("[A, B, E]", list.toString());
        sublist.clear();
        assertEquals("[]", sublist.toString());
        assertEquals("[A, E]", list.toString());
    }
```

If one assertion per test is too rigid, this test case represents clearly a kind of smell.

One possible improvement could be split it to two test cases:

```java
@Test
@SuppressWarnings("unchecked")
public void testSubListRemoveInSublists() {
    list.add((E) "A");
    list.add((E) "B");
    list.add((E) "C");
    list.add((E) "D");
    list.add((E) "E");

    final List<E> sublist = list.subList(1, 4);
    assertEquals("[A, B, C, D, E]", list.toString());
    sublist.remove("C");
    assertEquals("[A, B, D, E]", list.toString());
    sublist.remove(1);
    assertEquals("[A, B, E]", list.toString());
    sublist.clear();
    assertEquals("[A, E]", list.toString());
}

@Test
@SuppressWarnings("unchecked")
public void testSubListRemoveInLists() {
    list.add((E) "A");
    list.add((E) "B");
    list.add((E) "C");
    list.add((E) "D");
    list.add((E) "E");
    final List<E> sublist = list.subList(1, 4);
    assertEquals("[B, C, D]", sublist.toString());
    sublist.remove("C");
    assertEquals("[B, D]", sublist.toString());
    sublist.remove(1);
    assertEquals("[B]", sublist.toString());
    sublist.clear();
    assertEquals("[]", sublist.toString());
}
```

You can find the pmd result in this repository