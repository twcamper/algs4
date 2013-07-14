import util.*;
public class _11_Date
{
  private static void printDate(String name, util.Date d)
  {
    StdOut.printf("%s: %s %s\n", name,  d.dayOfTheWeek(), d);
  }
  private static void printComparison(util.Date D1, util.Date D2)
  {
    StdOut.printf("%s == %s: %s\n", D1, D2, D1.equals(D2));
  }
  public static void main(String[] args)
  {
    if (args.length != 3)
      Usage.error("mm dd yyyy");

    int mm   = Integer.parseInt(args[0]);
    int dd   = Integer.parseInt(args[1]);
    int yyyy = Integer.parseInt(args[2]);
    util.Date d1 = new util.Date(mm, dd, yyyy);
    util.Date d2 = new util.Date(02, 23, 1999);

    printDate("d1", d1);
    printDate("d2", d2);
    printComparison(d1, d1);
    printComparison(d1, d2);

  }
}
