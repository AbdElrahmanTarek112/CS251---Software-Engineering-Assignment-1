package bankCommons;

public class Date {
    private int year;
    private int month;
    private int day;

    private int[] daysInMonth = {31, 28, 31, 30, 31,30, 31, 31, 30, 31, 30, 31};
    private int validateMonth(int month) {
        if (month >= 1 || month <= 12) {
            return month;
        }  else {
            throw  new IllegalArgumentException("Invalid month");
        }
    }

    private int validateDay(int month, int day) {
        boolean leap = false;
        if (year % 4 == 0) {
            //leap
            leap = true;
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    leap = true;
                } else {
                    leap = false;
                }
            }
        }

        if (month == 2) {
            if (leap) {
                daysInMonth[1] = 29;
            }
        }

        if (day >= 1 && day <= daysInMonth[month-1]) {
            daysInMonth[1] = 28;
            return day;
        } else {
            daysInMonth[1] = 28;
            throw new IllegalArgumentException("Invalid day");
        }

    }
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = validateMonth(month);
        this.day = validateDay(month, day);
    }

    public int getYear() {return this.year;}
    public int getMonth() {return this.month;}
    public int getDay() {return this.day;}
}
