package fr.istic.vv;

class Date implements Comparable<Date>
{
    private final int day;
    private final int month;
    private final int year;

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    public Date(int day, int month, int year)
    {
        if (!isValidDate(day, month, year))
            throw new IllegalArgumentException("Invalid date");

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year)
    {
        if (day < 1 || month < 1 || month > 12)
            return false;

        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapYear(year))
            daysInMonth[2] = 29;

        return day <= daysInMonth[month];
    }

    public static boolean isLeapYear(int year)
    {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public Date nextDate()
    {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;

        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (nextDay > daysInMonth[month]) {
            nextDay = 1;
            nextMonth++;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
        }
        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate()
    {
        int prevDay = day - 1;
        int prevMonth = month;
        int prevYear = year;

        if (prevDay < 1) {
            prevMonth--;
            if (prevMonth < 1) {
                prevMonth = 12;
                prevYear--;
            }
            int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (isLeapYear(year))
                daysInMonth[2] = 29;

            prevDay = daysInMonth[prevMonth];
        }
        return new Date(prevDay, prevMonth, prevYear);
    }

    public int compareTo(Date other)
    {
        if (other == null) {
            throw new NullPointerException("Comparison with null date");
        }
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

}