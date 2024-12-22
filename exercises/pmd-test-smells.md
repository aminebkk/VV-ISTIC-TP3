# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

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

The PMD scan result could be found in this repo.

