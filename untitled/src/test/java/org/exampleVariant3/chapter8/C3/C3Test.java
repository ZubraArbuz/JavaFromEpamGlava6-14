package org.exampleVariant3.chapter8.C3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class C3Test {

    @Test
    public void testRemoveMaxSubstring() {
        String input = "diodparent";
        String expected = "parent";
        assertEquals(expected, C3.removeMaxSubstring(input));
    }

    @Test
    public void testRemoveMaxSubstring_withNoRepeat() {
        String input = "abcdef";
        String expected = "abcdef";
        assertEquals(expected, C3.removeMaxSubstring(input));
    }

    @Test
    public void testRemoveMaxSubstring_withEmptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, C3.removeMaxSubstring(input));
    }

    @Test
    public void testRemoveMaxSubstring_withMultipleRepeats() {
        String input = "abcabcabc";
        String expected = "bc";
        assertEquals(expected, C3.removeMaxSubstring(input));
    }

    @Test
    public void testRemoveMaxSubstring_noRemoval() {
        String input = "aabbbcc";
        String expected = "aacc";
        assertEquals(expected, C3.removeMaxSubstring(input));
    }
}
