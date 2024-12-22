# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer


| Characteristics: value of str   |
|---------------------------------|
| contains char other than ({[]}) |
| size is not even                |
| Empty string                    |
| Nested balanced string          |
| Composed balanced string        |
| Other cases                     |

The test implementation had a coverage of 82%, which means 14 of 17 lines in the method were covered.

The not covered branches are in case string's size is smaller than 2 or the case of private stack emptied too early.

It turns out that these two branches are already implemented by other cases or in an inpossible path, therefore it's
better to remove them.

The test coverage is now 100%.

The current test satisfies the Base choice coverage since those multiple booleans are just an enumeration of valid
characters as input. Each partition come from a mutation of simple criterion.

The PIT score was 67% as 12 of 18 mutations were valid.

We were able to discovery an issue that illegal characters were not excluded and fixed it.

We found that the implementation was wrong while it was just matching the stored symbol with eaten one, instead of
making a correspondence of left one to one.

After this issue corrected, we got 100% of coverage from both line coverage and PIT one.

To conclude, PIT test allows to detect issues that are more likely hidden in normal unit tests.