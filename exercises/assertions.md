# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. still need to be done
2. assertEquals Verifies that two values are logically equal but not necessarily the same instance while assertSame Verifies that two objects refer to the exact same instance
   + Scenarios Where They Produce the Same Result :
   int a = 5;
   int b = 5;

   assertEquals(a, b);  // Passes
   assertSame(a, b); // Passes

   + Scenarios Where They  Produce Different Result :
     Integer num1 = new Integer(100);
     int num2 = 100;

     assertEquals(num1, num2);  // Passes
     assertSame(num1, num2);    // Fails: num1 is an object, num2 is a primitive

3. we can use fail to ensure that certain preconditions are met before proceeding with the test. If the preconditions are not satisfied, we can explicitly fail the test.
   + Example :
     @Test
     public void testPreconditions() {
     if (System.getenv("ENV_VAR") == null) {
     fail("Test cannot proceed without ENV_VAR being set");
     }

   // Proceed with the test if precondition is met
   }

4. The Advantages of using assertThrows : 
 + Precise Scope : assertThrows tests only the specific code block expected to throw the exception, avoiding false positives.
 + Detailed Assertions : It allows checking exception details like messages or custom fields for more accurate validation.
 + Improved Readability : The test code clearly shows what exception is expected and why, making tests easier to understand.
