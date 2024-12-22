package fr.istic.vv;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testValidDate() {
        assertDoesNotThrow(() -> new Date(1, 1, 2022));
        assertDoesNotThrow(() -> new Date(31, 12, -2022));
        assertDoesNotThrow(() -> new Date(29, 2, 0));
    }

    @Test
    void testInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 2022));
        assertThrows(IllegalArgumentException.class, () -> new Date(29, 2, 2023));
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 0, 0));
    }

    @Test
    void testBCCIsValidDate() {
        assertFalse(Date.isValidDate(-1, 1, 1));
        assertFalse(Date.isValidDate(1, -1, 1));
        assertFalse(Date.isValidDate(1, 13, 1));
    }

    @Test
    void testBCCLeapYear() {
        assertTrue(Date.isLeapYear(2020));
        assertFalse(Date.isLeapYear(2100));
        assertTrue(Date.isLeapYear(2000));
        assertTrue(Date.isLeapYear(2024));
        assertFalse(Date.isLeapYear(2021));
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(2020));
        assertFalse(Date.isLeapYear(2021));
    }

    @Test
    void testNextDate() {
        Date date = new Date(31, 12, 2022);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(1, nextDate.getMonth());
        assertEquals(2023, nextDate.getYear());
    }

    @Test
    void testNextDateFebruary()
    {
        Date date = new Date(28, 2, 2023);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(3, nextDate.getMonth());
        assertEquals(2023, nextDate.getYear());
    }

    @Test
    void testPreviousDate() {
        Date date = new Date(1, 1, 2022);
        Date prevDate = date.previousDate();
        assertEquals(31, prevDate.getDay());
        assertEquals(12, prevDate.getMonth());
        assertEquals(2021, prevDate.getYear());
    }

    @Test
    void testPreviousDateFebruary()
    {
        Date date = new Date(1, 3, 2020);
        Date previousDate = date.previousDate();
        assertEquals(29, previousDate.getDay());
        assertEquals(2, previousDate.getMonth());
        assertEquals(2020, previousDate.getYear());
    }

    @Test
    void testCompareTo() {
        Date date1 = new Date(6, 6, 2022);
        assertThrows(NullPointerException.class, () -> date1.compareTo(null));

        Date date2 = new Date(1, 1, 2023);
        assertTrue(date1.compareTo(date2) < 0);

        Date date3 = new Date(31, 1, 2022);
        assertTrue(date1.compareTo(date3) > 0);

        Date date4 = new Date(10, 6, 2022);
        assertTrue(date1.compareTo(date4) < 0);

        Date date5 = new Date(6, 6, 2022);
        assertEquals(0, date1.compareTo(date5));
    }

    @Test
    void testPitNextDate() {
        Date date = new Date(29, 11, 2021);
        Date nextDate = date.nextDate();
        assertEquals(30, nextDate.getDay());
        assertEquals(11, nextDate.getMonth());
        assertEquals(2021, nextDate.getYear());

        nextDate = nextDate.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(12, nextDate.getMonth());
        assertEquals(2021, nextDate.getYear());
    }

    @Test
    void testPitPreviousDate() {
        Date date = new Date(2, 2, 2021);
        Date previousDate = date.previousDate();
        assertEquals(1, previousDate.getDay());
        assertEquals(2, previousDate.getMonth());
        assertEquals(2021, previousDate.getYear());

        previousDate = previousDate.previousDate();
        assertEquals(31, previousDate.getDay());
        assertEquals(1, previousDate.getMonth());
        assertEquals(2021, previousDate.getYear());
    }

    @Test
    void testPitCompareTo() {
        Date date = new Date(1, 6, 2021);
        assertTrue(date.compareTo(new Date(1, 7, 2021)) < 0);
    }

}
