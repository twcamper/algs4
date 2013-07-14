package util;
public class Date
{
  private final int month;
  private final int day;
  private final int year;
  private static final String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

  public Date(int mm, int dd, int yyyy)
  {
    if (dateIsValid(mm, dd, yyyy)) {
      month = mm; day = dd; year = yyyy;
    } else
      throw new IllegalArgumentException(String.format("Invalid Date: month=%d, day=%d, year=%d", mm, dd, yyyy));
  }

  public int month() { return month; }
  public int day() { return day; }
  public int year() { return year; }

  // algorithm from: en.wikipedia.org/wiki/Calculating_the_day_of_the_week
  //
  // This does not handle Julian dates, only Gregorian ones, so
  // it only works back to Sep.14 1752.
  public String dayOfTheWeek()
  {
    // month values for calculation
    int[] monthFactors = {0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5};

    // Adjust Jan and Feb for leap year
    if (isLeapYear(year())) {
      monthFactors[0] = -1;
      monthFactors[1] = 2;
    }

    int gregorianCenturyFactor = 0;
    switch((year() / 100) % 4) {
      case 1:
        gregorianCenturyFactor = 4;
        break;
      case 2:
        gregorianCenturyFactor = 2;
        break;
      case 3:
        gregorianCenturyFactor = 0;
        break;
      case 0:
        gregorianCenturyFactor = 6;
        break;
    }

    int yy = year() % 100;

    int dayNumber = (day() + monthFactors[month() - 1] + yy + (yy/4) + gregorianCenturyFactor) % 7;
    return dayNames[dayNumber];
  }

  public String toString()
  {
    return String.format("%02d/%02d/%04d", month(), day(), year());
  }

  private static boolean isLeapYear(int yyyy)
  {
    if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0)
      return true;
    return false;
  }

  private static boolean dateIsValid(int mm, int dd, int yyyy)
  {
    if (yyyy < 0)
      return false;

    if (mm < 1 || mm > 12)
      return false;

    // Ends of Months
    int[] eom = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // adjust February End of Month if Leap year
    if (isLeapYear(yyyy))
      eom[2] = 29;

    if (dd < 1 || dd > eom[mm])
      return false;

    return true;
  }

  public boolean equals(Object other)
  {
    if (this == other) return true;
    if (other == null) return false;
    if (this.getClass() != other.getClass()) return false;

    Date that = (Date) other;
    if (this.day != that.day)                return false;
    if (this.month != that.month)            return false;
    if (this.year != that.year)              return false;

    return true;
  }
}
