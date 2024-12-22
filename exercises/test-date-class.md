# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer


`isValidDate(day, month, year)`

| Characteristics | day     | month                | year          |
|-----------------|---------|----------------------|---------------|
|                 |         | <=0                  | < 0           |
|                 | 1 .. 31 | in [1,3,5,7,8,10,12] | >= 0          |
|                 | 1 .. 30 | in [4,6,9,11]        | >= 0          |
|                 | 1 .. 29 | == 2                 | is leap year  |
|                 | 1 .. 28 | == 2                 | not leap year |
|                 |         | > 12                 | any year      |

`isLeapYear(year)`

| Characteristics | year                         |
|-----------------|------------------------------|
|                 | y < 0                        |
|                 | y % 4 == 0                   |
|                 | y % 100 == 0 && y % 400 == 0 |
|                 | y % 100 == 0 && y % 400 != 0 |
|                 | else                         |

`nextDate()`

| Characteristics | Date                                                |
|-----------------|-----------------------------------------------------|
|                 | not a valid date                                    |
|                 | Date.day < max of day in Date.month                 |
|                 | Date.day == max of day in Date.month and month < 12 |
|                 | Date.day == 31 and month == 12                      |

`previousDate()`



| Characteristics | Date                         |
|-----------------|------------------------------|
|                 | not a valid date             |
|                 | Date.day == 1 and month == 1 |
|                 | Date.day == 1 and month > 1  |
|                 | Date.day > 1                 |

`compareTo(Date)`

| Characteristics | Date1                     | Date2            |
|-----------------|---------------------------|------------------|
|                 | not a valid date          | not a valid date |
|                 | not a valid date          |                  |
|                 |                           | not a valid date |
|                 | valid date prior to date2 | valid date       |
|                 | valid date equal to date2 | valid date       |
|                 | valid date after date2    | valid date       |

Several common properties: whether a date is valid, and often day ranges are from 1 to 31 (or 30, 29, 28) and month range is always 1 to 12.

We obtained 93% coverage rate with 41/44 lines for our Date class. Uncovered statements are mainly in private helper method for isLessThan function. We added more tests to cover each branch for year, month and day.

We have a mutation coverage of 94% (60/64). Remained mutants are mainly changed boundaries for `if (year < 0)` and `if (month <= 0 || month > 12)` and `return this.day < other.day`. As these checking points are quite trivial, we are not going to populate more tests.

