package fr.istic.vv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    void testBalancedString() {
        assertTrue(StringUtils.isBalanced("{[()]()}"));
        assertTrue(StringUtils.isBalanced("(({{[[]]}}))"));
        assertTrue(StringUtils.isBalanced(""));
        assertTrue(StringUtils.isBalanced("<div>[<p>(text)</p>]</div>"));
        assertTrue(StringUtils.isBalanced("abc123_45+{x-y}[a*(b+c)]"));
    }

    @Test
    void testUnbalancedString() {
        assertFalse(StringUtils.isBalanced("{[()](}"));
        assertFalse(StringUtils.isBalanced("(({{[[]]}})"));
        assertFalse(StringUtils.isBalanced(")("));
        assertFalse(StringUtils.isBalanced("<div>[<p>(text]</p>]</div>"));
        assertFalse(StringUtils.isBalanced("abc123_45+{x-y}[a*(b+c)"));
    }

    @Test
    void testEmptyString() {
        assertTrue(StringUtils.isBalanced(""));
    }

    @Test
    void testStringWithNoSymbols() {
        assertTrue(StringUtils.isBalanced("abc123"));
    }

    @Test
    void testBaseChoiceCoverage() {
        // Condition 1 - True
        assertTrue(StringUtils.isMatching('(', ')'));

        // Condition 1 - False
        assertFalse(StringUtils.isMatching('(', ']'));

        // Condition 2 - True
        assertTrue(StringUtils.isMatching('[', ']'));

        // Condition 2 - False
        assertFalse(StringUtils.isMatching('[', '}'));

        // Condition 3 - True
        assertTrue(StringUtils.isMatching('{', '}'));

        // Condition 3 - False
        assertFalse(StringUtils.isMatching('{', ']'));
    }

}