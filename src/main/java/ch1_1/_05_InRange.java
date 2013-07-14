public class _05_InRange {

  static boolean inRange(double n) {
    if (n > 0.0 && n < 1.0)
      return true;

    return false;
  }
  public static void main(String[] args)
  {
    if (args.length == 2) {
      double x = Double.parseDouble(args[0]);
      double y = Double.parseDouble(args[1]);

      if (inRange(x) && inRange(y))
        StdOut.println("true");
      else
        StdOut.println("false");
    } else {
      System.err.println("Two doubles are required");
    }
  }
}
