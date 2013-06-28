public class _11_Date
{
  private final int month;
  private final int day;
  private final int year;

  public _11_Date(int mm, int dd, int yyyy)
  {
    if (dateIsValid(mm, dd, yyyy)) {
      month = mm; day = dd; year = yyyy;
    } else
      throw new RuntimeException(String.format("Invalid Date: month=%d, day=%d, year=%d\n", mm, dd, yyyy));
  }

  public int month() { return month; }
  public int day() { return day; }
  public int year() { return year; }

  public String toString()
  {
    return String.format("%02d/%02d/%04d", month(), day(), year());
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
    if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0)
      eom[2] = 29;

    if (dd < 1 || dd > eom[mm])
      return false;

    return true;
  }
  private static void usageError()
  {
    System.err.println("Usage: mm dd yyyy");
    System.exit(1);
  }
  public static void main(String[] args)
  {
    if (args.length != 3)
      usageError();

    int mm   = Integer.parseInt(args[0]);
    int dd   = Integer.parseInt(args[1]);
    int yyyy = Integer.parseInt(args[2]);
    _11_Date d = new _11_Date(mm, dd, yyyy);

    StdOut.println(d);
  }
}
